package com.example.forceoffline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.forceoffline.Tools.BaseActivity;

public class OfflineActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);
        ((Button)findViewById(R.id.btnOffline)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent("com.example.forceoffline.FORCE_OFFLINE");
                sendBroadcast(intent);
                Log.d("offline12","sended");
            }
        });
    }
}
