package com.example.tan089.pushnotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    Button bnt01;

    TextView Message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnt01 = (Button) findViewById(R.id.bnt01);
        Message = (TextView) findViewById(R.id.bodyTxt);
        LocalBroadcastManager.getInstance(this).registerReceiver(mHandler, new IntentFilter("com.example.tan089.pushnotification_Tan"));

        if (getIntent().getExtras() != null) {
            for(String key : getIntent().getExtras().keySet())
            {
                if(key.equals("message"))
                    Message.setText(getIntent().getExtras().getString(key));
            }
        }

        bnt01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Updates.class);
                intent.putExtra("Update", Message.getText().toString());
                startActivity(intent);
            }
        });

    }
    private BroadcastReceiver mHandler = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String MessageBody = intent.getStringExtra("message");
            Message.setText(MessageBody);

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mHandler);
    }
}
