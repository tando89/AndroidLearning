package com.example.tan089.chatapptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {
    Button startBnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_activity);
        startBnt = (Button) findViewById(R.id.firstBnt);

        startBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(FirstActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });
    }
}
