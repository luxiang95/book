package com.example.webview;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLogTags;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.tls.OkHostnameVerifier;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivityTest";
    private TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button request = (Button) findViewById(R.id.request);
        responseText = (TextView) findViewById(R.id.response_text);
        request.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.request) {
         //   sendRequestWithHttpURIConnection();
            sendRequestWithOkhttp();

    }
    }

    private void sendRequestWithOkhttp()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
             try{
                 OkHttpClient client =new OkHttpClient();
                 Request request =new Request.Builder()
                         .url("http://www.baidu.com")
                         .build();
                 Response response =client.newCall(request).execute();
                 String responseData =response.body().string(); //Sting 不是toString
                 showResponse(responseData);
                 Log.d(TAG, responseData);
             }catch (Exception e)
             {
                 e.printStackTrace();
             }
            }
        }).start();
    }
    private void sendRequestWithHttpURIConnection() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    showResponse(response.toString());
                    Log.d(TAG, response.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null)
                        connection.disconnect();
                }
            }
        }).start();
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(response);
            }
        });
    }
}
