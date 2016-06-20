package spider.project.booky.seller;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import spider.project.booky.R;

/**
 * Created by aravind on 23/5/16.
 */
public class FinalBookInfo extends AppCompatActivity {
    private TextView name,author,price,edition,seller_name,seller_email,seller_contact_no;
    private Toolbar newTool;
    private RatingBar condition;
    private ImageView main_backdrop;
    private Integer images [] = {R.drawable.ashe_stad,R.drawable.fed_serve,R.drawable.kabali_1,R.drawable.kabali_2,R.drawable.kabali_3,R.drawable.mata2,R.drawable.mata_fk};
    private LinearLayout imageGallery;

    //TODO: Use Android binding to directly display values without BoilerPlate

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_book_info);

        main_backdrop = (ImageView) findViewById(R.id.main_backdrop);
        imageGallery = (LinearLayout) findViewById(R.id.imageGallery);
        newTool = (Toolbar) findViewById(R.id.newTool);
        condition = (RatingBar) findViewById(R.id.condition);
        name = (TextView) findViewById(R.id.name);
        author = (TextView) findViewById(R.id.author);
        price = (TextView) findViewById(R.id.price);
        edition = (TextView) findViewById(R.id.edition);
        seller_name = (TextView) findViewById(R.id.seller_name);
        seller_email = (TextView) findViewById(R.id.seller_email);
        seller_contact_no = (TextView) findViewById(R.id.seller_contact_no);

        main_backdrop.setImageResource(R.drawable.beginning_android);

        newTool.setTitle("Beginning Android");
        newTool.setTitleTextColor(Color.WHITE);
        setSupportActionBar(newTool);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        for(Integer image : images)
            imageGallery.addView(getImageView(image));

        condition.setEnabled(false);
        condition.setNumStars(5);
        condition.setMax(5);
        condition.setStepSize((float) 0.5);
        condition.setRating((float) 3.5);


        name.setText("Beginning Android");
        author.setText("Jack Hughes");
        price.setText("350");
        edition.setText("5e");
        seller_name.setText("Seller name here");
        seller_email.setText("abc@gmail.com");
        seller_contact_no.setText("1234567890");

    }

    private View getImageView(final Integer image) {
        final ImageView imageView = new ImageView(getApplicationContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(300,300);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        lp.setMargins(0, 0, 10, 0);
        imageView.setLayoutParams(lp);
        imageView.setImageResource(image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent bookIntent = new Intent(this,FinalBookInfo.class);
                !----- Choose a unique ID for a book -----!
                bookIntent.putExtra("id",val);
                startActivity(bookIntent);
                 */
                //ONE POSSIBLE ID :
                //imageView.setTag(image);
                //then val = imageView.getTag().toString();
                //Toast.makeText(getApplicationContext(),imageView.getTag().toString(),Toast.LENGTH_SHORT).show();
            }
        });
        return imageView;
    }
    public void call(View v){
        Intent callIntent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + seller_contact_no.getText().toString()));
        Log.i("ph no : ",seller_contact_no.getText().toString());
        startActivity(callIntent);
    }
}
