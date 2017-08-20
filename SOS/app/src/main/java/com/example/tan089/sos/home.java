package com.example.tan089.sos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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

import static com.example.tan089.sos.R.layout.fragment_home;

/**
 * Created by tan089 on 7/13/2017.
 */

public class home extends Fragment {

    Button getUpdates;
    TextView Message;
    ImageButton alarmButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        View viewHome =  inflater.inflate(fragment_home, container, false);
        //Casting variables
        Message = (TextView) viewHome.findViewById(R.id.msgText);

        LocalBroadcastManager.getInstance(this.getActivity()).registerReceiver(mHandler, new IntentFilter("com.example.tan089.sos_Message"));

        if (getActivity().getIntent().getExtras() != null) {
            for(String key : getActivity().getIntent().getExtras().keySet())
            {
                if(key.equals("message"))
                    Message.setText("Message from server:" + " " + getActivity().getIntent().getExtras().getString(key));
            }
        }
        getUpdates = (Button) viewHome.findViewById(R.id.getUpdates);
        getUpdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GetUpdates.class);
                startActivity(intent);
            }
        });

        alarmButton = (ImageButton) viewHome.findViewById(R.id.alarmBnt);
        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), confirmEmergencyPage.class);
                startActivity(intent);
            }
        });
        return viewHome;

    }
    //Declare the handler
    private BroadcastReceiver mHandler = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String MessageBody = intent.getStringExtra("message");
            Message.setText(MessageBody);

        }
    };

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this.getActivity()).unregisterReceiver(mHandler);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("S.O.S");

    }
}
