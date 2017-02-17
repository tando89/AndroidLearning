package com.tando.mba01;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //button About Us function
    public void startChild (View view) {

        startActivity(new Intent(this, Page02.class));
    }
    //button Contact Us Function
    public void contactUs (View viewContactUs) {
        startActivity(new Intent(this, ContactUs.class));
    }
    public void faceBookFunc (View viewFb) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/cbpamba")));
    }

}
