

package spider.project.booky.seller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import spider.project.booky.R;

public class sellermain extends AppCompatActivity {
    TextView bname, aname, edi, pric, sub;
    String  bookname, authorname, edition, price, subject;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellermain);
        bname = (TextView) findViewById(R.id.bookname);
        aname = (TextView) findViewById(R.id.bookauthor);
        edi = (TextView) findViewById(R.id.edition);
        pric = (TextView) findViewById(R.id.price);
        sub = (TextView) findViewById(R.id.subjec);
        img=(ImageView)findViewById(R.id.thumbnail);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(getApplicationContext(),SellerForm.class);
                    startActivity(myIntent);
                    //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    //        .setAction("Action", null).show();
                }
            });
        }
    }

}