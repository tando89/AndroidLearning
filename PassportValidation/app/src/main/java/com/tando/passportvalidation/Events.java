package com.tando.passportvalidation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Events extends AppCompatActivity {
    static String[] DETAILS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        //event = (TextView) findViewById(R.id.events01);
        //date = (TextView) findViewById(R.id.dates01);

        //event2 = (TextView) findViewById(R.id.events02);
        //date2 = (TextView) findViewById(R.id.dates02);
        ListView myListv = (ListView) findViewById(R.id.listv);

        Bundle bundle1 = getIntent().getExtras();
        final String events= bundle1.getString("Events");
        Bundle bundle2 = getIntent().getExtras();
        final String date= bundle2.getString("Date");
        DETAILS = new String[] {events, date};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DETAILS);
        myListv.setAdapter(arrayAdapter);
        //event.setText("Event:" + bundle.getString("Events"));
        //date.setText("Date:" + bundle.getString("Date"));

        //event2.setText("Event:" + bundle.getString("Events"));
        //date2.setText("Date:" + bundle.getString("Date"));
    }
}
