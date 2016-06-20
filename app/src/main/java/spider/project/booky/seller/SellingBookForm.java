package spider.project.booky.seller;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import spider.project.booky.R;

public class SellingBookForm extends AppCompatActivity implements LocationListener {

    EditText name, phone, bname, aname, edi, pric, sub, num;
    String nam, phon, bookname, authorname, edition, price, subject, numberofbooks;
    Float rating;
    RatingBar r;
    double latn, lonn;
    LocationManager locationManager;
    LocationListener locationListener;
    boolean gps_enabled, network_enabled;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellingbook_form);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phonenumber);
        bname = (EditText) findViewById(R.id.bookname);
        aname = (EditText) findViewById(R.id.bookauthor);
        edi = (EditText) findViewById(R.id.edition);
        pric = (EditText) findViewById(R.id.price);
        sub = (EditText) findViewById(R.id.subjec);
        num = (EditText) findViewById(R.id.no);
        r = (RatingBar) findViewById(R.id.ratingBar);
    }

    public void submit(View v) {
        //TODO: Validate if all fields are proper using RxJava
        nam = name.getText().toString();
        phon = phone.getText().toString();
        bookname = bname.getText().toString();
        authorname = aname.getText().toString();
        edition = edi.getText().toString();
        price = pric.getText().toString();
        subject = sub.getText().toString();
        numberofbooks = num.getText().toString();
        rating = r.getRating();
        //TODO: Add this object into the SellingBook model class and retrofit it
        MyAsyncTask task = new MyAsyncTask();
        task.execute("");//url

    }

    String provider;
    Location location;

    public void updateCurrentLocation(View v) {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = LocationManager.GPS_PROVIDER;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(SellingBookForm.this, "Give GPS permissions", Toast.LENGTH_SHORT).show();


        } else {
            locationManager.requestLocationUpdates(provider, 5000, 0, SellingBookForm.this, Looper.getMainLooper());
            location = locationManager.getLastKnownLocation(provider);
            if(location!=null)
            {

                onLocationChanged(location);
                Log.d("lat","provider=GPS");
            }
            else{
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,5000,0,
                        SellingBookForm.this,Looper.getMainLooper());
                location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if(location!=null)
                    onLocationChanged(location);
                Log.d("lat","provider=Network");
            }
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        latn=location.getLatitude();
        lonn=location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    class MyAsyncTask extends AsyncTask<String, String, Void> {

        private ProgressDialog progressDialog = new ProgressDialog(SellingBookForm.this);


        protected void onPreExecute() {
            progressDialog.setMessage("Downloading your data...");
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(String... params) {
                /*   try {
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpPost = new HttpGet(params[0]);
                    HttpResponse httpResponse = httpClient.execute(httpPost);
                    HttpEntity httpEntity = httpResponse.getEntity();
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                } catch (ClientProtocolException e2) {
                    Log.e("ClientProtocolException", e2.toString());
                    e2.printStackTrace();
                } catch (IllegalStateException e3) {
                    Log.e("IllegalStateException", e3.toString());
                    e3.printStackTrace();
                } catch (IOException e4) {
                    Log.e("IOException", e4.toString());
                    e4.printStackTrace();
                }*/
            return null;
        }

        protected void onPostExecute(Void v) {
            progressDialog.dismiss();
            Toast.makeText(SellingBookForm.this, "Submitted succesfully", Toast.LENGTH_SHORT).show();
        }
    }
}
