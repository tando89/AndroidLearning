package com.example.tan.spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {
    //Declare an array to store units
    public static ArrayList<Double> unitsAdd = new ArrayList<Double>();
    //Declare spinner
    Spinner spinner;
    //Declare adapter to connect array in string value
    ArrayAdapter<CharSequence> adapter;
    //
    Double num1, totalUnits = 0.00;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);

        adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.units,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //display the adapter contain the list
        spinner.setAdapter(adapter);

        //delcare buttons
        Button bntInsert = (Button) findViewById(R.id.bntInsert);
        Button bntCount = (Button) findViewById(R.id.bntCount);
        //Declare textView
        final TextView resultTotalUnits = (TextView) findViewById(R.id.resultTotalUnits);
        //Insert function
        bntInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Declare a string for selected items
                String unitSelected = spinner.getSelectedItem().toString();
                //Covert string to double
                num1 = Double.parseDouble(unitSelected);
                //add double value into array
                unitsAdd.add(num1);
                Toast.makeText(getApplicationContext(),
                        "You have inserted: " + unitsAdd + " units",
                        Toast.LENGTH_SHORT).show();

            }
        });
        //Total units function
       bntCount.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //Have to declare totalUnits = 0.00 from the beginning
               for(int i=0;i<unitsAdd.size();i++){
                   //sum of the array called units
                   totalUnits +=  (unitsAdd.get(i));
               }
               //Display the results in the TextView sections
               resultTotalUnits.setText("Total units :"+Double.toString(totalUnits));
               //Clear the array after button clicked
               unitsAdd.clear();
           }
       });



    }
}
