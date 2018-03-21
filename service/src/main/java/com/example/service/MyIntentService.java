package com.example.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by luxia on 2018/3/21.
 */

public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentServiceTest";
    public MyIntentService() {
        super("my intent service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
