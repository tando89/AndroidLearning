package com.tando.imagesapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView imageView;
    Button downloadImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        downloadImg = (Button) findViewById(R.id.downloadImg);

        downloadImg.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {

        DownloadImageTask downloadImageTask = new DownloadImageTask(MainActivity.this);
        downloadImageTask.execute("http://www.rd.com/wp-content/uploads/sites/2/2016/04/01-cat-wants-to-tell-you-laptop.jpg");

    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        ProgressDialog progressDialog;
        Context context;

        public DownloadImageTask(Context context) {

            this.context = context;

            progressDialog = new ProgressDialog(context);
        }
        //before downloading from Internet
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Downloading...");
            progressDialog.show();
        }

        //after downloading from Internet. Background job
        @Override
        protected Bitmap doInBackground(String... params) {
            String stringUrl = params[0];
            Bitmap bitmap = null;

            try {
                URL url = new URL(stringUrl);
                //open the website
                InputStream inputStream = url.openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //return the object (background)
            return bitmap;
        }
        //Display

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            imageView.setImageBitmap(bitmap);
            if (progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }
    }
}
