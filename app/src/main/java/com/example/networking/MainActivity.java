package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public ArrayList<Mountain> list;
    public ArrayList<String> list2;
    public ArrayAdapter<String> listAdapter;
    public ListView simpleListView;
    public String jsonString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list2 = new ArrayList<String>();
        list = new ArrayList<Mountain>();
        listAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.activity_main, R.id.myList, list2);

        new JsonTask().execute("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=brom");

        simpleListView = (ListView) findViewById(R.id.myList);

        listAdapter = new ArrayAdapter<String>(this,
                R.layout.item_view, R.id.itemTextView, list2);
        simpleListView.setAdapter(listAdapter);
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
            Log.d("TAG", json);

            try {
                // Ditt JSON-objekt som Java
                JSONArray jsonArr = new JSONArray(json);
                for (int i = 0; i < jsonArr.length(); i++) {
                    JSONObject json1 = new JSONArray(json).getJSONObject(i);
                    String id = json1.getString("ID");
                    list2.add(id);
                    String name = json1.getString("name");
                    String type = json1.getString("type");
                    String company = json1.getString("company");
                    String location = json1.getString("location");
                    String category = json1.getString("category");
                    int size = json1.getInt("size");
                    int cost = json1.getInt("cost");
                    Map<String, String> auxdata = new HashMap<String, String>();
                    JSONObject jsonArrAux = json1.getJSONObject("auxdata");
                    auxdata.put("wiki", jsonArrAux.getString("wiki"));
                    auxdata.put("img", jsonArrAux.getString("img"));
                    list.add(new Mountain(id, name, type, company, location, category, size, cost, auxdata));
                }

            } catch (JSONException e) {
                Log.e("brom", "E:" + e.getMessage());
            }
            listAdapter.notifyDataSetChanged();

            simpleListView = findViewById(R.id.myList);

        }
    }
}
