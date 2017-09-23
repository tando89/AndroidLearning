package com.example.tan089.chatapptest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FirstActivity extends AppCompatActivity {
    Button startBnt;
    private Button buttonDisplayToken;
    private TextView textViewToken;
    private Button buttonRegister, goNotification;
    private ProgressDialog progressDialog;
    private EditText editTextEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_activity);
        startBnt = (Button) findViewById(R.id.firstBnt);
        buttonDisplayToken = (Button) findViewById(R.id.buttonDisplayToken);
        textViewToken = (TextView) findViewById(R.id.textViewToken);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        goNotification = (Button) findViewById(R.id.buttonSendNotification);
        startBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(FirstActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });
        buttonDisplayToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting token from shared preferences
                String token = SharedPrefManager.getInstance(FirstActivity.this).getDeviceToken();

                //if token is not null
                if (token != null) {
                    //displaying the token
                    textViewToken.setText(token);
                } else {
                    //if token is null that means something wrong
                    textViewToken.setText("Token not generated");
                }
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTokenToServer();
            }
        });
        goNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(FirstActivity.this, SendPushNotification.class));
            }
        });
    }
    //storing token to mysql server
    private void sendTokenToServer() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering Device...");
        progressDialog.show();

        final String token = SharedPrefManager.getInstance(this).getDeviceToken();
        final String registerEmail = editTextEmail.getText().toString();

        if (token == null) {
            progressDialog.dismiss();
            Toast.makeText(this, "Token not generated", Toast.LENGTH_LONG).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_REGISTER_DEVICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(FirstActivity.this, obj.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(FirstActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", registerEmail);
                params.put("token", token);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
