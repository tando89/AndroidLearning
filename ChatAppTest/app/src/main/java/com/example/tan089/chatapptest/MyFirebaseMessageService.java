package com.example.tan089.chatapptest;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import static android.app.Notification.DEFAULT_SOUND;
import static android.app.Notification.DEFAULT_VIBRATE;

/**
 * Created by tan089 on 8/25/2017.
 */

public class MyFirebaseMessageService extends FirebaseMessagingService{
    private static final String TAG = "MyFirebaseMsgService";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        /*Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message:" + remoteMessage.getNotification().getBody());*/
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                sendPushNotification(json);
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }

        // Check if message contains a notification payload.
        /*if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }*/
    }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        //this method will display the notification
        //We are passing the JSONObject that is received from
        //firebase cloud messaging
        private void sendPushNotification(JSONObject json) {
            //optionally we can display the json into log
            Log.e(TAG, "Notification JSON " + json.toString());
            try {
                //getting the json data
                JSONObject data = json.getJSONObject("data");

                //parsing json data
                String title = data.getString("title");
                String message = data.getString("message");
               // String imageUrl = data.getString("image");

                //creating MyNotificationManager object
                MyNotificationManager mNotificationManager = new MyNotificationManager(getApplicationContext());

                //creating an intent for the notification
                Intent intent = new Intent(getApplicationContext(), FirstActivity.class);

                //if there is no image
                //if(imageUrl.equals("null")){
                    //displaying small notification
                    mNotificationManager.showSmallNotification(title, message, intent);
               // }else{
                    //if there is an image
                    //displaying a big notification
                  //  mNotificationManager.showBigNotification(title, message, imageUrl, intent);
                //}
            } catch (JSONException e) {
                Log.e(TAG, "Json Exception: " + e.getMessage());
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }


}
