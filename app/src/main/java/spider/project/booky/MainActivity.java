package spider.project.booky;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private JSONObject userObj = null;
    private String urlString = "<YOUR URL>";
    CheckBox showP = null;
    EditText password = null;
    EditText webmail = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showP = (CheckBox) findViewById(R.id.showP);
        webmail = (EditText) findViewById(R.id.webmail);
        password = (EditText) findViewById(R.id.password);
        showP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked)
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                else
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });
    }

    public void login(View v) throws JSONException {
        if (webmail.getText().toString().trim().length() == 0)
            Toast.makeText(this, "webmail field is empty !", Toast.LENGTH_SHORT).show();
        else {
            if (password.getText().toString().trim().length() == 0)
                Toast.makeText(this, "Password field is empty !", Toast.LENGTH_SHORT).show();
            else {
                String user = webmail.getText().toString();
                user += "@nitt.edu";
                String pass = password.getText().toString();

                /*---------------------------------------------
                The following JSON object is POSTed to the server :
                 ------------------------------------------------*/
                userObj = new JSONObject();
                userObj.put("webmail", user);
                userObj.put("password", pass);
                new JSONPost().execute();
            }
        }
    }

    private class JSONPost extends AsyncTask<Void, Void, JSONObject> {
        private ProgressBar spinner;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinner = (ProgressBar) findViewById(R.id.progressBar1);
            assert spinner != null;
            spinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            HttpURLConnection connection = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            JSONObject obj = null;
            try {
                URL url = new URL(urlString);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(20000);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setFixedLengthStreamingMode(String.valueOf(userObj).getBytes().length);
                outputStream = connection.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                bw.write(String.valueOf(userObj));
                bw.flush();
                bw.close();

                inputStream = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer sb = new StringBuffer();
                String line = "";
                while ((line = br.readLine()) != null)
                    sb.append(line);
                obj = new JSONObject(sb.toString());
                br.close();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (outputStream != null) {
                        outputStream.flush();
                        outputStream.close();
                    }
                    if(inputStream != null)
                        inputStream.close();
                    if (connection != null)
                        connection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return obj;
        }

        @Override
        protected void onPostExecute(JSONObject object) {
            spinner.setVisibility(View.INVISIBLE);
            if (object == null)
                Toast.makeText(getApplicationContext(), "JSONObject is null", Toast.LENGTH_SHORT).show();
        }
    }
}
