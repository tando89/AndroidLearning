package com.example.tan089.pushnotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Updates extends AppCompatActivity {
    TextView message;
    String getUpdates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);

        message = (TextView) findViewById(R.id.bodyTxt);
        getUpdates = getIntent().getExtras().getString("Update");
        message.setText("Message:"+""+getUpdates);
    }

}
