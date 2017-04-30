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

import java.text.DecimalFormat;
import java.util.ArrayList;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {
    //Declare an array to store units
    public static ArrayList<Double> unitsAdd = new ArrayList<Double>();
    //Declare an array to store grade points
    public static ArrayList<Double> gradeAdd = new ArrayList<Double>();
    //Declare an array to store the result of units * gradepoints
    public static ArrayList<Double> gradePoints = new ArrayList<Double>();
    //Declare spinner
    Spinner spinner, spinner2;
    //Declare adapter to connect array in string value
    ArrayAdapter<CharSequence> adapter;
    //
    ArrayAdapter<CharSequence> adapter2;
    Double num1, num2, gradepoints, totalGPA, sum = 0.00, totalUnits = 0.00;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);

        spinner2 = (Spinner) findViewById(R.id.spinner2);

        adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.units,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //adater2

        adapter2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.grade_points,
                android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //display the adapter contain the list
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);

        //delcare buttons
        Button bntInsert = (Button) findViewById(R.id.bntInsert);
        Button bntCount = (Button) findViewById(R.id.bntCount);
        Button bntClear = (Button) findViewById(R.id.clear);
        //textView for total units
        final TextView resultTotalUnits = (TextView) findViewById(R.id.resultTotalUnits);
        //textView for total grade points
        final TextView result = (TextView) findViewById(R.id.result);
        //textView for GPA
        final TextView resultGPA = (TextView) findViewById(R.id.gpa);
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
                //grade points lists
                String gradeSelected = spinner2.getSelectedItem().toString();
                num2 = Double.parseDouble(gradeSelected);
                gradeAdd.add(num2);
                //checking
                /*Toast.makeText(getApplicationContext(),
                        "You have inserted: " + unitsAdd + " units" + gradeAdd,
                        Toast.LENGTH_SHORT).show();*/
                Toast.makeText(getApplicationContext(),
                        "You have inserted: " + num1 + " units and " + num2 + " Grade Points",
                        Toast.LENGTH_SHORT).show();
                gradepoints = num1 * num2;
                if (gradepoints >= 0) {
                    gradePoints.add(gradepoints);
                } else {
                    Toast.makeText(getApplicationContext(), "Error!!!", Toast.LENGTH_LONG).show();
                }
                spinner.setSelection(0);
                spinner2.setSelection(0);
            }
        });
        //Total units function
       bntCount.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               DecimalFormat format = new DecimalFormat("#.#");
               //Have to declare totalUnits = 0.00 from the beginning
               for(int i=0;i<unitsAdd.size();i++){
                   //sum of the array called units
                   totalUnits +=  (unitsAdd.get(i));
               }
                //total gradepoints
               for(int j=0;j<gradePoints.size();j++){
                   //sum of the array called gradePoints
                   sum =  (gradePoints.get(j)) + sum;
               }
               //GPA
               totalGPA = sum / totalUnits;
               //Display the results in the TextView sections
               resultTotalUnits.setText("Total units: "+Double.toString(totalUnits));
               //Display total gradepoints
               result.setText("Total grade points: "+format.format(sum));
               //Display GPA
               resultGPA.setText("GPA: "+format.format(totalGPA));
               //Clear the array after button clicked
               totalGPA = 0.00;
               totalUnits = 0.00;
               sum = 0.00;
               unitsAdd.clear();
               gradeAdd.clear();
               gradePoints.clear();
           }
       });

        //Button clear

        bntClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTotalUnits.setText("null");
                result.setText("null");
                resultGPA.setText("null");
                gradePoints.clear();
                unitsAdd.clear();
                gradeAdd.clear();
                gradePoints.clear();
                Toast.makeText(MainActivity.this, "Clear!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
