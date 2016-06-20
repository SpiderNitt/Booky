package spider.project.booky;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import spider.project.booky.seller.FinalBookInfo;
import spider.project.booky.seller.SellerInfo;

public class MainActivity extends AppCompatActivity {
    private JSONObject userObj = null;
    private String urlString = "<YOUR URL>";
    //TODO: Change this activity to register new user, and have a link to login if already a user

    @BindView(R.id.showP) CheckBox showP = null;
    @BindView(R.id.password) EditText password = null;
    @BindView(R.id.webmail) EditText webmail = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if(showP != null) {
            showP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!isChecked)
                        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    else
                        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            });
        }
    }

    public void login(View v) throws JSONException {


        if (webmail.getText().toString().trim().length() == 0)
            Toast.makeText(this, "webmail field is empty !", Toast.LENGTH_SHORT).show();
        else {
            if (password.getText().toString().trim().length() == 0)
                Toast.makeText(this, "Password field is empty !", Toast.LENGTH_SHORT).show();
            else {
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
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
                else
                    Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*
    INTENTS FOR TESTING________________________________

    public void check(View v){
        Intent checkIntent = new Intent(this,FinalBookInfo.class);
        startActivity(checkIntent);
    }
    public void seller(View v){
        Intent sellerIntent = new Intent(this, SellerInfo.class);
        startActivity(sellerIntent);
    }

    */

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
                Toast.makeText(getApplicationContext(), "Error during login", Toast.LENGTH_SHORT).show();
        }
    }
}
