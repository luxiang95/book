package com.example.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent =new Intent(this,MainActivity.class);
        PendingIntent pi  =PendingIntent.getActivity(this,0,intent,0);
        final Notification notification =new NotificationCompat.Builder(MainActivity.this)
                .setContentTitle("This is contentTitle")
                .setContentText("this is contentText")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.small) //小图标 只能黑白色，否则图标纯白
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.bigicon))
                .setContentIntent(pi)
                .setSound(Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.ring)) //android 资源文件 URI样式
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build();

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        ((Button) findViewById(R.id.btnNotification)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.notify(1,notification);
            }
        });
    }
}
