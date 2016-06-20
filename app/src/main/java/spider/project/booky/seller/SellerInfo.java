package spider.project.booky.seller;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import spider.project.booky.R;

/**
 * Created by aravind on 24/5/16.
 */
public class SellerInfo extends AppCompatActivity {
    private TextView seller_name,seller_email,seller_contact_no,seller_location,underline;
    private Toolbar newTool;
    private ImageView main_backdrop;
    private Integer images [] = {R.drawable.ashe_stad,R.drawable.fed_serve,R.drawable.kabali_1,R.drawable.kabali_2,R.drawable.kabali_3,R.drawable.mata2,R.drawable.mata_fk};
    private LinearLayout imageGallery;

    //TODO: Use Android binding to directly display values without BoilerPlate
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_seller_info);

        underline = (TextView) findViewById(R.id.underline);
        seller_name = (TextView) findViewById(R.id.seller_name);
        seller_email = (TextView) findViewById(R.id.seller_email);
        seller_contact_no = (TextView) findViewById(R.id.seller_contact_no);
        main_backdrop = (ImageView) findViewById(R.id.main_backdrop);
        imageGallery = (LinearLayout) findViewById(R.id.imageGallery);
        newTool = (Toolbar) findViewById(R.id.newTool);
        seller_location = (TextView) findViewById(R.id.seller_location);

        main_backdrop.setImageResource(R.drawable.user);

        newTool.setTitle("Seller name");
        newTool.setTitleTextColor(Color.WHITE);
        setSupportActionBar(newTool);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        underline.setPaintFlags(underline.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        underline.setText("View in map");

        for(Integer image : images)
            imageGallery.addView(getImageView(image));

        seller_name.setText("Seller name");
        seller_email.setText("abc@gmail.com");
        seller_contact_no.setText("1234567890");
        seller_location.setText("your location");

    }
    private View getImageView(Integer image) {
        final ImageView imageView = new ImageView(getApplicationContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(650,650);
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
}
