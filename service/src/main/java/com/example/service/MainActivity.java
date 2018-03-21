package com.example.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivityTest";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button startService = (Button) findViewById(R.id.start_service);
       startService.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

              Intent intent  =new Intent(MainActivity.this,MyService.class);
              startService(intent);
           }
       });

        Button stopService = (Button) findViewById(R.id.stop_service);
        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent  =new Intent(MainActivity.this,MyService.class);
                stopService(intent);
            }
        });
        Button intentService = (Button) findViewById(R.id.intent_service);
        intentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent =new Intent(MainActivity.this,MyIntentService.class);
             startService(intent);
            }
        });
    }
}
