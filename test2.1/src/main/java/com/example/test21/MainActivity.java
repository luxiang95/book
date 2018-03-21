package com.example.test21;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        init();
        ((Button) findViewById(R.id.Button1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Intent intent =new Intent(MainActivity.this,Activity2.class);//显式intent

                   Intent intent = new Intent("com.example.luxia.ACTION_START");

                intent.putExtra("stringData","stringToActivity2");
                startActivityForResult(intent,1);
            //    Toast.makeText(MainActivity.this, "moving to activity2 now", Toast.LENGTH_LONG).show();

            }
        });

        ((Button) findViewById(R.id.ViewWeb)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });
        ((Button) findViewById(R.id.btnCall)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call();
            }
        });
//    public void btn1Click(View view)
//    {
//        Toast.makeText(this,"This is a toast by btn1Click",Toast.LENGTH_LONG).show();
//    }
    }

    public void btnDialog(View view)
    {
        AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("this is a dialog");
        builder.setMessage("something to show");
        builder.setCancelable(true);//能否点击其他区域关闭对话框
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    private void init() {
        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
        }
    }


        private void call() {
            //    Intent intent =new Intent(Intent.ACTION_CALL);  //直接拨打
           Intent intent =new Intent(Intent.ACTION_DIAL);  //转到拨号界面
            intent.setData(Uri.parse("tel:10086"));
            try {
                startActivity(intent);
            }catch (SecurityException e)
            {
                e.printStackTrace();
            }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: "+requestCode);

        if(resultCode==RESULT_OK)
        {
            String returnData =data.getStringExtra("data_return");
            Toast.makeText(this,returnData,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode)
        {
            case 1:
                if (!(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED))

                {
                    Toast.makeText(this,"你必须同意所有的权限才能使用本软件",Toast.LENGTH_LONG).show();
                }
        }
    }

}