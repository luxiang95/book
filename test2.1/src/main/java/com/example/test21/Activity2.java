package com.example.test21;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Intent intent = getIntent();
        String data = intent.getStringExtra("string");

        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
        ((Button) findViewById(R.id.btnActivity2)) .setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent();
                        intent1.putExtra("data_return", "data return to MainActivity");
                        setResult(RESULT_OK, intent1);
                        finish();
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {  //按下返回键触发
        Intent intent1 = new Intent();
        intent1.putExtra("data_return", "data return to MainActivity");
        setResult(RESULT_OK, intent1);
        finish();
    }
}


