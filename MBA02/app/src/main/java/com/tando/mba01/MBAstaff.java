package com.tando.mba01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MBAstaff extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mbastaff);
    }

    public void backHome (View home) {
        startActivity(new Intent(this, MainActivity.class));
    }


}
