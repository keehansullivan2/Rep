package keehansullivan.berkeley.edu.whosreppn;

import android.content.Intent;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.util.Linkify;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class Main3Activity extends AppCompatActivity {
    TextView textView5;
    TextView textView6;
    String fullName;
    String Party;
    String contactlink;
    String websitelink;
    TextView textView7;
    TextView textView8;
    TextView textView9;
    TextView textView10;
    TextView textView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView6.setText("Political Party: ");
        textView7 = (TextView) findViewById(R.id.textView7);
        textView8 = (TextView) findViewById(R.id.textView8);
        textView9 = (TextView) findViewById(R.id.textView9);
        textView10 = (TextView) findViewById(R.id.textView10);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView10.setText("Website: ");
        textView9.setText("Contact: ");

        fullName = bundle.getString("fullName");
        Party = bundle.getString("Party");
        contactlink = bundle.getString("contactlink");
        websitelink = bundle.getString("websitelink");
        textView5.setText(fullName);
        //textView7.setText("Party: " + Party + "\n" + "Website: " + websitelink + "\n" + "Contactlink: " + contactlink);
        textView7.setText(websitelink);
        Pattern pattern = Pattern.compile(websitelink);
        Linkify.addLinks(textView7, pattern, "http://");
        textView7.setText(Html.fromHtml("<a href='"+websitelink+"'>"+websitelink+"</a>"));

        textView8.setText(contactlink);
        Pattern contpattern = Pattern.compile(contactlink);
        Linkify.addLinks(textView8, contpattern, "http://");
        if (contactlink.equals("null")) {
            textView8.setText("NOT AVALIABLE");
        } else {
            textView8.setText(Html.fromHtml("<a href='" + contactlink + "'>" + contactlink + "</a>"));
        }
        textView3.setText(Party);










    }
}
