package com.tando.button;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public void buttonFunc (View newView) {
        //R.id.textField R stands for resources. Meaning that it will search for id named textField
        EditText textField = (EditText) findViewById(R.id.textField);
        Toast.makeText(MainActivity.this, "Hello" + " " + textField.getText().toString(), Toast.LENGTH_SHORT).show();

    }
    public void buttonUpdate (View viewUpdate) {
        ImageView cat = (ImageView) findViewById(R.id.imageCat01);
        cat.setImageResource(R.drawable.img02);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
