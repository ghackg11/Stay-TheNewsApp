package com.example.stay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class NewsShowingActivity extends AppCompatActivity {

    private ListView listView;
    private String stringurl = "";
    private ArrayList<NewsItem> newsArray;
    private Button nextbtn;
    private Button prevbtn;
    private int currentpos = 0;
    private TextView[] dotsViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_showing);

        Intent intent = getIntent();
        stringurl = intent.getStringExtra("url");

        listView = (ListView)findViewById(R.id.listView);
        Backgroundtasks bb = new Backgroundtasks();
        bb.execute();

        String heading = intent.getStringExtra("header");

        getSupportActionBar().setTitle("STAY-"+heading.toUpperCase());


    }



    private class Backgroundtasks extends AsyncTask<Void,Void,ArrayList<NewsItem>> {


        @Override
        protected ArrayList<NewsItem> doInBackground(Void... urls) {
            String jsonResponse = "";
            URL url = makeUrl();
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            newsArray = extractFeatures(jsonResponse);

            return newsArray;
        }


        @Override
        protected void onPostExecute(ArrayList<NewsItem> newsItems) {
            updateUi();
        }
    }
    private URL makeUrl()
    {
        URL url = null;
        try {
            url = new URL(stringurl);
        } catch (MalformedURLException e) {
            Log.e("SliderAdapter", "Error in makeurl function",e);
            return null;
        }
        return url;
    }

    private void updateUi()
    {
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        MyAdapter adapter = new MyAdapter(this,R.layout.news_layout,newsArray);
        listView.setAdapter(adapter);
    }
    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(1000000000 /* milliseconds */);
            urlConnection.setConnectTimeout(1500000000 /* milliseconds */);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } catch (IOException e) {
            // TODO: Handle the exception
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private ArrayList<NewsItem> extractFeatures(String earthquakeJSON) {

        ArrayList<NewsItem> list = new ArrayList<>();
        try {
            JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);
            String status = baseJsonResponse.getString("status");
            if(status.equals("OK")) {
                JSONObject response = baseJsonResponse.getJSONObject("response");
                JSONArray docs = response.getJSONArray("docs");
                if (docs.length() > 0 && docs != null) {
                    for (int i = 0; i < docs.length(); i++) {
                        JSONObject newss = docs.getJSONObject(i);
                        JSONObject headline = newss.getJSONObject("headline");
                        String title = headline.getString("main");
                        String description = newss.getString("lead_paragraph");

                        JSONArray multimedia = newss.getJSONArray("multimedia");
                        JSONObject multimediaobject = null;
                        String imageurl = "https://i.postimg.cc/sgP1kSZk/noun-No-Image-340719.png";
                        if(!(multimedia==null || multimedia.length()<=0)) {
                            multimediaobject = multimedia.getJSONObject(0);

                            imageurl = "https://www.nytimes.com/" + multimediaobject.getString("url");
                        }

                        list.add(new NewsItem(imageurl, title, description));
                    }
                    newsArray = list;
                    return list;
                }
            }
        } catch (JSONException e) {
            Log.e("in news activity", "Problem parsing the earthquake JSON results", e);
        }
        return null;
    }


}
