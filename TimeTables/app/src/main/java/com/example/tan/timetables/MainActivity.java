package com.example.tan.timetables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView TimeTableListView;
    public void generateTimeTable(int timesTable) {
        //Listview function

        ArrayList<String> timesTableContent = new ArrayList<String>();

        for (int i =1; i <= 10; i++) {
            timesTableContent.add(Integer.toString(i * timesTable));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,timesTableContent);

        TimeTableListView.setAdapter(arrayAdapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar timeTableSeekBar = (SeekBar) findViewById(R.id.timeTableSeekBar);
        TimeTableListView = (ListView) findViewById(R.id.TimeTableListView);

        timeTableSeekBar.setMax(20);
        timeTableSeekBar.setProgress(10);

        timeTableSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //set the min of seekbar
                int min = 1;
                int timesTable;

                if (progress < min) {
                    timesTable = min;
                    //avoid dragging seekbar less than 1
                    timeTableSeekBar.setProgress(min);
                } else {
                    timesTable = progress;
                }

                generateTimeTable(timesTable);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

       generateTimeTable(10);
    }
}
