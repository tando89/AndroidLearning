package com.example.tan.math;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<String> num = new ArrayList<String>();
    double sum, res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //myInput is ID of Edittext
        final EditText editTextNum = (EditText) findViewById(R.id.myInput);
        //buttonAdd is ID of button 1
        Button buttonAdd = (Button)findViewById(R.id.buttonAdd);
        //buttonCalc is ID of button 2
        Button buttonCalc = (Button)findViewById(R.id.buttonCalc);
        //text2 is ID of TextView
        final TextView textViewRes = (TextView)findViewById(R.id.text2);
        //Insert Function
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numCatch=editTextNum.getText().toString().trim();
                if(numCatch.length() != 0){
                    num.add(numCatch);
                    Toast.makeText(MainActivity.this, "You have inserted: " + numCatch.toString(),Toast.LENGTH_SHORT).show();
                    editTextNum.setText("");
                }

                //textViewRes.setText("The average is ");
            }
        });

        //Average func
        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat format = new DecimalFormat("#.##");
                for(int i=0;i<num.size();i++){
                    sum = Integer.parseInt(num.get(i)) + sum;
                }
                res = sum/num.size();
                textViewRes.setText("The average is: "+format.format(res));
                res = 0;
                sum = 0;
                num.clear();
            }
        });
    }

}
