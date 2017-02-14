package com.tando.mba01;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ContactUs extends AppCompatActivity {

    Button button;
    EditText FirstName, LastName, StudentID, CoyoteEmail, Messages;

    String server_url = "https://feedback-server-tand089.c9users.io/feedback.php";

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        button = (Button) findViewById(R.id.btn01);
        FirstName = (EditText) findViewById(R.id.FirstName);
        LastName = (EditText) findViewById(R.id.LastName);
        StudentID =(EditText) findViewById(R.id.StudentID);
        CoyoteEmail = (EditText) findViewById(R.id.CoyoteEmail);
        Messages = (EditText) findViewById(R.id.Messages);

        builder = new AlertDialog.Builder(ContactUs.this);
        //set function for the button btn01
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String firstName, lastName, studentID, coyoteEmail, messages;
                //covert inputs to string
                firstName = FirstName.getText().toString();
                lastName = LastName.getText().toString();
                studentID = StudentID.getText().toString();
                coyoteEmail = CoyoteEmail.getText().toString();
                messages = Messages.getText().toString();
                //request with POST method and string
                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                builder.setTitle("Server Response");
                                builder.setMessage("Response" + response);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //clear the text fields after submit
                                        FirstName.setText("");
                                        LastName.setText("");
                                        StudentID.setText("");
                                        CoyoteEmail.setText("");
                                        Messages.setText("");
                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ContactUs.this,"Error!!!", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("FIRSTNAME", firstName);
                        params.put("LASTNAME",lastName);
                        params.put("STUDENTID",studentID);
                        params.put("COYOTEEMAIL",coyoteEmail);
                        params.put("MESSAGES",messages);
                        return params;
                    }
                };

                MySingleton.getInstance(ContactUs.this).addTorequestqueue(stringRequest);
            }
        });
    }
}
