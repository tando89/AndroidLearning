package com.tando.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int randomNumber;

    public void makeToast(String string) {
        Toast.makeText(MainActivity.this,string,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //generate random number
        Random rand = new Random();
        //random numbers are generated from 20 to 1
        randomNumber = rand.nextInt(20) + 1;

    }
    public void buttonFunc(View view) {
        EditText guessEditText = (EditText) findViewById(R.id.editText);

        int guessInt = Integer.parseInt(guessEditText.getText().toString());

        if (guessInt > randomNumber) {
            makeToast("LOWER");
        } else if (guessInt < randomNumber) {
            makeToast("HIGHER");
        } else {
            makeToast("THAT'S RIGHT! TRY AGAIN!");
            //generate random number
            Random rand = new Random();
            //random numbers are generated from 20 to 1
            randomNumber = rand.nextInt(20) + 1;
        }

    }
}
