package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class MountainInfo extends AppCompatActivity {
    private TextView name;
    private TextView location;
    private TextView cost;
    private TextView size;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain_info);
        Mountain m = (Mountain) getIntent().getSerializableExtra("mountain");
        Log.d("asd", m.toString());
        name = findViewById(R.id.mountainInfoName);
        location = findViewById(R.id.mountainInfoLocation);
        cost = findViewById(R.id.mountainInfoCost);
        size = findViewById(R.id.mountainInfoSize);
        img = findViewById(R.id.mountainInfoImg);

        name.setText(m.getName());
        location.setText(m.getLocation());
        cost.setText("Cost: " + m.getCost());
        size.setText("Size: " + m.getSize());
        new DownloadImageTask(img).execute(m.getAuxdata().get("img"));
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}