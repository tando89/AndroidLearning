package com.example.tan.events;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;

    private ListView lv;


    // URL to get contacts JSON
    private static String url = "https://databases-tand089.c9users.io/events.php";

    ArrayList<HashMap<String, String>> eventList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.lstView);

        new GetEvents().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetEvents extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray event = jsonObj.getJSONArray("Event");

                    // looping through All Events
                    for (int i = 0; i < event.length(); i++) {
                        JSONObject c = event.getJSONObject(i);
                        //get string from the json file
                        String Date = c.getString("Date");
                        String Event = c.getString("Event");
                        String Hosts = c.getString("Hosts");
                        String Loc = c.getString("Location");

                        // tmp hash map for single event
                        HashMap<String, String> events = new HashMap<>();

                        // adding each child node to HashMap key => value
                        events.put("Date", Date);
                        events.put("Event", Event);
                        events.put("Hosts", Hosts);
                        events.put("Location", Loc);

                        // adding contact to event list
                        eventList.add(events);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } //End if
            else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }
            return null;
        } //End doing backgroud

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, eventList,
                    R.layout.list_item, new String[]{"Date", "Event",
                    "Hosts", "Location"}, new int[]{R.id.date,
                    R.id.event, R.id.host, R.id.location});

            lv.setAdapter(adapter);
            lv.setOnItemClickListener(MainActivity.this);

        }

    }
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        startActivity(new Intent(this, PassportValidation.class));
    }
}
