package com.example.tan089.chatapptest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendPushNotification extends AppCompatActivity {
    private Button buttonSendPush;
    //private EditText editTextTitle, editTextMessage, editTextImage;
    private List<String> devices;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_push_notification);
        buttonSendPush = (Button) findViewById(R.id.buttonSendPush);

        //editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        //editTextMessage = (EditText) findViewById(R.id.editTextMessage);
        //editTextImage = (EditText) findViewById(R.id.editTextImageUrl);
        devices = new ArrayList<>();

        //radioGroup.setOnCheckedChangeListener(this);
        // buttonSendPush.setOnClickListener(this);
        buttonSendPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMultiplePush();
            }
        });
        loadRegisteredDevices();
    }

    //method to load all the devices from database
    private void loadRegisteredDevices() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Devices...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_FETCH_DEVICES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                JSONArray jsonDevices = obj.getJSONArray("devices");

                                for (int i = 0; i < jsonDevices.length(); i++) {
                                    JSONObject d = jsonDevices.getJSONObject(i);
                                    devices.add(d.getString("email"));
                                }

                                /*ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                                        ActivitySendPushNotification.this,
                                        android.R.layout.simple_spinner_dropdown_item,
                                        devices);

                                spinner.setAdapter(arrayAdapter);*/
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

        };
        MyVolley.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void sendMultiplePush() {
        //final String title = editTextTitle.getText().toString();
        final String title = "SOS";
        //final String message = editTextMessage.getText().toString();
        final String message = "Good morning, this is a test from Tan";
        //final String image = editTextImage.getText().toString();

        progressDialog.setMessage("Sending Push");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_SEND_MULTIPLE_PUSH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        //Toast.makeText(ActivitySendPushNotification.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("title", title);
                params.put("message", message);

               /* if (!TextUtils.isEmpty(image))
                    params.put("image", image);*/
                return params;
            }
        };

        MyVolley.getInstance(this).addToRequestQueue(stringRequest);
    }
}
