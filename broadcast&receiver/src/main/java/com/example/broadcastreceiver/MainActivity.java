package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
private NetworkChangeReceiver receiver;
private MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter =new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        receiver=new NetworkChangeReceiver();
        registerReceiver(receiver,intentFilter);

        intentFilter =new IntentFilter("com.example.broadcastreceiver.MY_BROADCAST");
        myBroadcastReceiver=new MyBroadcastReceiver();
        registerReceiver(myBroadcastReceiver,intentFilter);
        ((Button)findViewById(R.id.send)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent("com.example.broadcastreceiver.MY_BROADCAST");
                sendBroadcast(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        unregisterReceiver(myBroadcastReceiver);
    }

    private  class NetworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager =(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info =manager.getActiveNetworkInfo();
            if(info!=null&&info.isAvailable())
            {
                Toast.makeText(MainActivity.this,"network is available",Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(MainActivity.this,"network is unavailable",Toast.LENGTH_SHORT).show();
            }

        }
    }
}
