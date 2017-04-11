package com.tando.passportvalidation;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //Declare some variables
    Button login_button;
    EditText Passcode;
    String passcode;
    String login_url = "https://feedback-server-tand089.c9users.io/validation.php";
    //Variable for Alert Dialog
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //name of the activity of the dialog
        builder = new AlertDialog.Builder(MainActivity.this);

        login_button = (Button) findViewById(R.id.button_login);
        Passcode = (EditText) findViewById(R.id.login_passcode);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validation for username and password. Ensure corrected input

                passcode = Passcode.getText().toString();

                if(passcode.equals(""))
                {
                    builder.setTitle("Code not found");
                    displayAlert("Enter a valid passcode");
                }
                //validate data from server
                else
                {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, login_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        //"code" is the key from json object on server
                                        String code = jsonObject.getString("code");

                                        if(code.equals("login_failed"))
                                        {
                                            builder.setTitle("Login Error");
                                            displayAlert(jsonObject.getString("message"));
                                            //builder.setMessage("Response" + response);

                                        }
                                        //login successfully
                                        else
                                        {
                                            Toast.makeText(MainActivity.this,"Yay!!!", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(MainActivity.this, SuccessedPage.class);
                                            startActivity(intent);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "Error login",Toast.LENGTH_LONG);
                            error.printStackTrace();
                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            //"username" is the key name from mySQL server
                            params.put("passcode", passcode);
                            return params;
                        }
                    };

                    MySingleton.getInstance(MainActivity.this).addTorequestqueue(stringRequest);
                    //Code to change color of listview in here
                }

            }
        });
    }

    public void displayAlert(String message){
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //clear username and password fields
                Passcode.setText("");
            }
        });
        //display Alert
        //First create AlertDialog variable then display it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
