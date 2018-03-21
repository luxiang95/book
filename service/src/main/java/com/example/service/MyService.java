package com.example.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by luxia on 2018/3/20.
 */

public class MyService extends Service {

    private static final String TAG = "MyServiceTest";
    private MyThread myThread ;
    private  boolean isKeep=true;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        myThread =new MyThread();

        /*
        前台服务
         */
        Intent intent =new Intent(this,MainActivity.class);
        PendingIntent pi =PendingIntent.getActivity(this,0,intent,0);
        Notification notification =new Notification.Builder(this)
                .setContentTitle("this is content_title")
                .setContentText("this is content_text")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pi)
                .build();
        startForeground(1,notification);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        myThread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        if(myThread!=null)
        {
           isKeep=false;
        }
    }
    class MyThread extends  Thread
    {
        @Override
        public void run() {
            int i =1;
            while(i<10)
            {
                i++;
                try {
                    Thread.sleep(1000);
                    Log.d(TAG, "run: "+i);
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            stopSelf();
        }
    }
}
