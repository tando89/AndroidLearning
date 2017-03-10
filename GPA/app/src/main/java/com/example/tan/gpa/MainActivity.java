package com.example.tan.gpa;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    //create an array to store the unit values, format in double
    public static ArrayList<Double> units = new ArrayList<Double>();
    //Create an array to store the grade points values
    public static ArrayList<Double> gradePoints = new ArrayList<Double>();
    //declare GPA as totalGPA, sum of units array as totalUnits, total grade points array as sum
    //gradepoints is the value of each unit value * each grade points value
    //num1 is unit value, num 2 is grade point value
    double totalGPA, totalUnits, sum, gradepoints, num1, num2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //call out all the input and output by their IDs
        final EditText unitAdd = (EditText) findViewById(R.id.unitAdd);
        final EditText gradeAdd = (EditText) findViewById(R.id.gradeAdd);
        Button buttonCalc = (Button)findViewById(R.id.buttonCalc);
        final TextView result = (TextView) findViewById(R.id.result);
        final TextView resultTotalUnits = (TextView) findViewById(R.id.resultTotalUnits);
        final TextView resultGPA = (TextView) findViewById(R.id.gpa);
        Button buttonGPA = (Button) findViewById(R.id.calcGPA);

        //Insert values button
        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //retrieve data when user type in TextEdit (units and grade points)
                num1 = Double.parseDouble(unitAdd.getText().toString());
                num2 = Double.parseDouble(gradeAdd.getText().toString());
                //function for grade points =  unit * grade point
                gradepoints = num1 * num2;
                //add grade points values into an array
                if (gradepoints != 0) {
                    gradePoints.add(gradepoints);
                }
                //add unit values into an array
                if (num1 != 0) {
                    units.add(num1);
                }
                //hide the keyboard after clicking the button
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(gradeAdd.getWindowToken(), 0);
                //Display multiple strings at once Toast
                Toast.makeText(getApplicationContext(),
                        "You have inserted: " + num1 + " units and " + num2 + " Grade Points",
                        Toast.LENGTH_SHORT).show();
                //clear the TextEdit to continue adding
                unitAdd.setText("");
                gradeAdd.setText("");
            }
        });
        //calculate total of units array, total of grade points array and the GPA
        buttonGPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //format the values
                DecimalFormat format = new DecimalFormat("#.#");
                for(int i=0;i<units.size();i++){
                    //sum of the array called units
                    totalUnits =  (units.get(i)) + totalUnits;
                }

                for(int i=0;i<gradePoints.size();i++){
                    //sum of the array called gradePoints
                    sum =  (gradePoints.get(i)) + sum;
                }
                //GPA function = total grade points / total units
                totalGPA = sum / totalUnits;

                //Display the results in the TextView sections
                resultTotalUnits.setText("Total units :"+Double.toString(totalUnits));
                result.setText("Total grade points :"+format.format(sum));
                resultGPA.setText("GPA: "+format.format(totalGPA));
                //Clear the arrays
                totalGPA = 0;
                totalUnits = 0;
                sum = 0;
                gradePoints.clear();
                units.clear();
            }
        });


    }
    //link to Facebook page
    public void fbFunc (View viewFb) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/cbpamba")));
    }
    //link to Instagram page
    public void igFunc (View viewIg) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/mba_csusb/")));
    }
    //link to Linkedin page
    public void lnFunc (View viewLn) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/mbacsusb/")));
    }
    //link to Twitter page
    public void twFunc (View viewTw) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/MBACSUSB")));
    }
}
