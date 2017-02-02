package com.tando.vndcurrency;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public void buttonFunc(View view01) {
        //search for any input in the text area that ID named dollarInput
        EditText inputs = (EditText) findViewById(R.id.dollarInput);
        //Convert the input to double
        Double doubleInputs = Double.parseDouble(inputs.getText().toString());
        //Assign the function with the double Input
        //convert function when click the button
        Double result = doubleInputs * 22642.50;
        //Display the result of the convert function
        //format the result to be rounded 2 decimal place
        Toast.makeText(MainActivity.this, "VND" + String.format("%.2f", result), Toast.LENGTH_SHORT).show();

        Log.i("Test", inputs.getText().toString());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
//remember use number to be a text input