package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public ArrayList<Mountain> list;
    public MountainAdapter mAdapter;
    public RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<Mountain>();
        rv = findViewById(R.id.myRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MountainAdapter(list, MainActivity.this);
        rv.setAdapter(mAdapter);
        //Testing while wwwlab is down.
        /*for(int i = 0; i < 5; i++){
            HashMap<String, String> auxdata = new HashMap<String, String>();
            auxdata.put("wiki", "wiki");
            auxdata.put("img", "img");
            Mountain m = new Mountain("id" + i, "name" + i, "type", "company", "location", "category", 100, 100, auxdata);
            list.add(m);
            mAdapter.notifyDataSetChanged();
        }*/
        new JsonTask().execute("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=brom");

    }
    @SuppressLint("StaticFieldLeak")
    private class JsonTask extends AsyncTask<String, String, String> {
        private HttpURLConnection connection = null;
        private BufferedReader reader = null;

        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null && !isCancelled()) {
                    Log.d("test", line);
                    builder.append(line).append("\n");
                }
                return builder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String json) {
            Log.d("TAG", "json");

            try {
                // Ditt JSON-objekt som Java
                JSONArray jsonArr = new JSONArray(json);
                for (int i = 0; i < jsonArr.length(); i++) {
                    JSONObject json1 = new JSONArray(json).getJSONObject(i);
                    String id = json1.getString("ID");

                    String name = json1.getString("name");
                    String type = json1.getString("type");
                    String company = json1.getString("company");
                    String location = json1.getString("location");
                    String category = json1.getString("category");
                    int size = json1.getInt("size");
                    int cost = json1.getInt("cost");
                    HashMap<String, String> auxdata = new HashMap<String, String>();
                    JSONObject jsonArrAux = json1.getJSONObject("auxdata");
                    auxdata.put("wiki", jsonArrAux.getString("wiki"));
                    auxdata.put("img", jsonArrAux.getString("img"));
                    Mountain m = new Mountain(id, name, type, company, location, category, size, cost, auxdata);
                    list.add(m);
                }

            } catch (JSONException e) {
                Log.e("brom", "E:" + e.getMessage());
            }
            mAdapter.notifyDataSetChanged();


        }
    }
}
