package com.example.tan089.sendingdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //Declare variables
    EditText firstName;
    EditText lastName;
    EditText email;
    Button sendBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstName = (EditText)findViewById(R.id.fName);
        lastName = (EditText)findViewById(R.id.lName);
        email = (EditText) findViewById(R.id.emailTxt);

        sendBtn = (Button) findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Declare some string
                String Fname = firstName.getText().toString();
                String Lname = lastName.getText().toString();
                String Email = email.getText().toString();

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("FISRTNAME", Fname);
                    intent.putExtra("LASTNAME", Lname);
                    intent.putExtra("EMAIL", Email);
                startActivity(intent);

            }
        });
    }
}
