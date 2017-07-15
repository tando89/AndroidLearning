package com.example.tan089.sendingdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    //Declare some variables
    TextView Data;
    String FisrtName;
    String LastName;
    String Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //get intent data from the first view. The keys must match the keys on the first activity
        FisrtName = getIntent().getExtras().getString("FISRTNAME");
        LastName = getIntent().getExtras().getString("LASTNAME");
        Email = getIntent().getExtras().getString("EMAIL");

        Data = (TextView) findViewById(R.id.dataTxt);
        //parse the data into TextView
        Data.setText("First Name:"+" "+FisrtName+'\n'+"Last Name:"+" "+LastName+'\n'+"Email:"+" "+Email);
    }
}
