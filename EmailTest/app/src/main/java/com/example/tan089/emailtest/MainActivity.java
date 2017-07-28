package com.example.tan089.emailtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{
    //Declaring EditText
    private EditText editTextSubject;
    private EditText editTextMessage;

    //Send button
    private Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initializing the views
        editTextSubject = (EditText) findViewById(R.id.editTextSubject);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);

        buttonSend = (Button) findViewById(R.id.buttonSend);

        //hide keyboard
        editTextSubject.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKyeboard(v);
                }
            }
        });
        editTextMessage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKyeboard(v);
                }
            }
        });

        //Adding click listener
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextSubject.length() <= 0 || editTextMessage.length() <= 0){
                    Toast.makeText(getApplicationContext(), "Please fill out all the required fields*", Toast.LENGTH_LONG).show();
                }
                else {

                    AlertDialog.Builder user_alert_tosend = new AlertDialog.Builder(MainActivity.this);
                    user_alert_tosend.setMessage("Once you click continue, you can't cancel it. Tap \"Continue\" to send")
                            .setPositiveButton("Continue", alertdialog_oncontinue)
                            .setNegativeButton("Stop", null)
                            .setCancelable(false).show();


                }

            }
        });
    }
    protected DialogInterface.OnClickListener alertdialog_oncontinue = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            sendEmail();
            editTextMessage.setText("");
            editTextSubject.setText("");
            //send to mainpage when done
            Intent page = new Intent(MainActivity.this, Page2.class);
            startActivity(page);
        }
    };
    private void sendEmail() {
        //Getting content for email
        String email = "tantdo89@gmail.com";
        String subject = editTextSubject.getText().toString().trim();
        String message = editTextMessage.getText().toString().trim();

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }

    public void hideKyeboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}
