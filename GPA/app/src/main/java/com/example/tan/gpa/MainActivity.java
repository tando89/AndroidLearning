package com.example.tan.gpa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<String> units = new ArrayList<String>();
    public static ArrayList<String> grades = new ArrayList<String>();
    double totalUnits, totalGrades, result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ID of EditText of units data is units
        final EditText unitsAdd = (EditText) findViewById(R.id.units);
        //ID of add units Button is unitsAdd
        Button buttonUnitAdd = (Button) findViewById(R.id.unitsAdd);
        //ID of EditText of grade points data is gradePoints
        final EditText gradesAdd = (EditText) findViewById(R.id.gradePoints);
        //ID of add gpa Button is gpaAdd
        Button buttonGPAAdd = (Button) findViewById(R.id.gpaAdd);
        //ID of calc gpa Button is calcGPA
        Button gpaCalc = (Button) findViewById(R.id.calcGPA);
        //ID of total units is textUnitSum
        final TextView sumUnits = (TextView) findViewById(R.id.textUnitSum);
        //ID of total grades is textGPASum
        final TextView sumGPA = (TextView) findViewById(R.id.textGPASum);
        //ID of your gpa is result
        final TextView resGPA = (TextView) findViewById(R.id.result);


        //sumUnits.setText("Total Units :"+ totalGrades);

        //insert units function, store units data into an array
        buttonUnitAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String unitsCatch = unitsAdd.getText().toString().trim();
                if(unitsCatch.length() != 0){
                    units.add(unitsCatch);
                    Toast.makeText(MainActivity.this, "You have inserted: " + unitsCatch.toString() + " units", Toast.LENGTH_SHORT).show();
                    //to continue inserting into TextEdit
                    unitsAdd.setText("");
                }
            }
        });
        //insert grade function, store units data into an array
        buttonGPAAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gradeCatch = gradesAdd.getText().toString().trim();
                if(gradeCatch.length() != 0){
                    grades.add(gradeCatch);
                    Toast.makeText(MainActivity.this, "You have inserted: " + gradeCatch.toString() + " points", Toast.LENGTH_SHORT).show();
                    //to continue inserting into TextEdit
                    gradesAdd.setText("");
                }
            }
        });

        //GPA calculation function
        gpaCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat format = new DecimalFormat("#.##");
                //Total units
                for(int i=0;i<units.size();i++){
                    //parse Double values to an array to get the sum function
                    totalUnits =  Double.parseDouble(units.get(i)) + totalUnits;
                }
                sumUnits.setText("Total Units :"+ totalUnits);
                //Total grade points
                for(int i=0;i<grades.size();i++){
                    //parse Double values to an array to get the sum function
                    totalGrades =  Double.parseDouble(grades.get(i)) + totalGrades;
                }
                sumGPA.setText("Total grade points :"+ totalGrades);
                result = totalGrades / totalUnits;
                resGPA.setText("Your GPA is :"+ format.format(result));
                result = 0;
                totalUnits = 0;
                totalGrades = 0;
                units.clear();
                grades.clear();
            }
        });
    }
}
