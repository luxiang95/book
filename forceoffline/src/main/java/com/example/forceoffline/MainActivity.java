package com.example.forceoffline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.forceoffline.Tools.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.btnJump)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent ();
                        intent.setClass(MainActivity.this,OfflineActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
