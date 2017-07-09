package com.unisinos.m2.mateusmanica.vimeoclientapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private String mUrlPrefix = "http:/vimeo.com/api/v2/group/";
    private String mUrlSuffix = "/videos.json";
    private URL vimeoURL;
    private HttpURLConnection urlConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public static JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {
        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */);
        urlConnection.setConnectTimeout(15000 /* milliseconds */);
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        String jsonString = sb.toString();
        System.out.println("JSON: " + jsonString);

        return new JSONObject(jsonString);
    }

    @Override
    public void onClick(View v) {
        try{
            String username  = ((TextView)findViewById(R.id.userToBeSearchTxt)).getText().toString();
            String urlString = mUrlPrefix + username + mUrlSuffix;

            JSONObject jsonObject = getJSONObjectFromURL(urlString);
            //
            // Parse your json here
            //
            String content = jsonObject.toString();
            ((TextView)findViewById(R.id.resultTxt)).setText(content);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
//        Thread httpThread = new Thread(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        String username = ((TextView)findViewById(R.id.userToBeSearchTxt)).getText().toString();
//                        String urlText  = mUrlPrefix + username + mUrlSuffix;
//
//                        try {
//                            vimeoURL = new URL(urlText);
//                            urlConnection= (HttpURLConnection) vimeoURL.openConnection();
//                            InputStream in = new BufferedInputStream((urlConnection.getInputStream()));
//                            ((TextView)findViewById(R.id.resultTxt)).setText(in.toString());
//                        } catch (MalformedURLException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } finally {
//                            urlConnection.disconnect();
//                        }
//                    }
//                });
//        httpThread.start();
}
