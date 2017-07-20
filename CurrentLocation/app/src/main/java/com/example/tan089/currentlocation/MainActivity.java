package com.example.tan089.currentlocation;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

//Must give permission ACCESS_FINE_LOCATION in the manifest
public class MainActivity extends AppCompatActivity {
    //Declare LocationManager and LocationListener
    LocationManager locationManager;
    LocationListener locationListener;
    String address = "Unable to get the address!";
    EditText Message;
    AlertDialog.Builder builder;
    String server_url ="https://schoolserver-tand089.c9users.io/Report.php";
    //Process when users give the permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //Start the location service
            startListening();
        }
    }
    //Checking permission granted method
    public void startListening() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        }
    }
    //Update location method. 1 variable location
    public void updatedLocationInfo(Location location) {
        Log.i ("Location", location.toString());
        //Cast the textViews
        TextView latTextView = (TextView) findViewById(R.id.txtLat);
        TextView lonTextView = (TextView) findViewById(R.id.txtLong);
        TextView altTextView = (TextView) findViewById(R.id.txtAltitude);
        TextView accTextView = (TextView) findViewById(R.id.txtAccuracy);
        //get the string from location
        latTextView.setText("Latitude: " + location.getLatitude());
        lonTextView.setText("Longitude: " + location.getLongitude());
        altTextView.setText("Altitude: " + location.getAltitude());
        accTextView.setText("Accuracy: " + location.getAccuracy());

        //Create Geocoder object to Get the address
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        //Using try/catch to prevent the app from crashing when failing to get Addresses
        try {
            //Declare the error string
            //String address = "Unable to get the address!";
            List<Address> listAddresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            //Check if the address is valid
            if (listAddresses != null && listAddresses.size() > 0) {
                //Set address to empty string again when we know it is working
                address = "Address: \n";
               // Log.i("Address", listAddresses.get(0).toString());
                //check for every item in the list Addresses is valid
                if (listAddresses.get(0).getSubThoroughfare() != null) {
                    address += listAddresses.get(0).getSubThoroughfare() + " ";
                }
                //Street name
                if (listAddresses.get(0).getThoroughfare() != null) {
                    address += listAddresses.get(0).getThoroughfare() + "\n";
                }
                //City name
                if (listAddresses.get(0).getLocality() != null) {
                    address += listAddresses.get(0).getLocality() + "\n";
                }
                //Zip code
                if (listAddresses.get(0).getPostalCode() != null) {
                    address += listAddresses.get(0).getPostalCode() + "\n";
                }
                //Country name
                if (listAddresses.get(0).getCountryName() != null) {
                    address += listAddresses.get(0).getCountryName() + "\n";
                }
                TextView addressTextView = (TextView)  findViewById(R.id.txtAddress);
                //set the address into the text View
                addressTextView.setText(address);
                //Log.i("Address", address.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //define builder
        builder = new AlertDialog.Builder(MainActivity.this);
        //set up locationManager and locationListener above and cast them into their type
        //Using built-in location service
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            //Get the location when it changes
            @Override
            public void onLocationChanged(Location location) {
                //call the updated location above
                        updatedLocationInfo(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        //Check for the version of SDK

        if (Build.VERSION.SDK_INT < 23) {
            startListening();
        } else {
            // above 23 we need to check for permission
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //ask for permission. Number 1 is just a request queue.
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
            //we have permission
            else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                //Get the last location from the built-in GPS
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                //In case location does not have lastknownlocation, we call the updatedLocation method above
                if (location != null) {
                    updatedLocationInfo(location);
                }
            }
        }

    }

    // using onClick
   public void bntTap (View bnt) {
        Message = (EditText) findViewById(R.id.message);
        //Hide virtual keyboard after click the button
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        final String message;

        message = Message.getText().toString();
        if (message.equals("")) {
            builder.setTitle("Unable to report");
            //Creating a AlertDialog to display errors
            AlertDialog alertDialog = builder.create();
            alertDialog.setMessage("Please Enter All Required Fields*");
            alertDialog.show();
        } else {
            //Post request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //get response form server to check if it is successfully submitted
                            builder.setTitle("Server Response");
                            builder.setMessage("Congratulation" + " " + response);
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Message.setText("");
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "Error!!!", Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    //send data to mySQL server
                    //the keys must be same as field names in mySQL server
                    // get the list of the address
                    params.put("LOCATION", address);
                    params.put("MESSAGES", message);
                    return params;
                }
            };
            MySingleton.getInstance(MainActivity.this).addTorequestqueue(stringRequest);
            //Log.i("Address", address.toString());
        }
    }
}
