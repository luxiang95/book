package com.example.forceoffline.Tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luxia on 2018/3/15.
 */

public class BaseActivity extends AppCompatActivity {
  static   private MyBroadcastReceiver myBroadcastReceiver;
    static private List<AppCompatActivity> activityList=new ArrayList<>();
    public BaseActivity()
    {
        activityList.add( this);
    }
   public static void finishAll() {

        for (AppCompatActivity a : activityList) {
            if(!a.isFinishing()) {
                a.finish();
                Log.d("finishAll",a.toString());
            }
//            activityList.remove(a);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        myBroadcastReceiver=new MyBroadcastReceiver();
        IntentFilter intentFilter =new IntentFilter();
        intentFilter.addAction("com.example.forceoffline.FORCE_OFFLINE");
        registerReceiver(myBroadcastReceiver,intentFilter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(myBroadcastReceiver!=null)
        {
            unregisterReceiver(myBroadcastReceiver);
            myBroadcastReceiver=null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityList.remove(this);
    }

   static private class MyBroadcastReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("onReceiver", this.toString()+"onReceive:received ");
            finishAll();
        }
    }
}
