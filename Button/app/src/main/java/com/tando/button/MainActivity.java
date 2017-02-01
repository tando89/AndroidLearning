package com.tando.button;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public void buttonFunc (View newView) {

        EditText textField = (EditText) findViewById(R.id.textField);
        Toast.makeText(MainActivity.this, "Hello" + " " + textField.getText().toString(), Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
