package com.example.forceoffline;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forceoffline.Tools.BaseActivity;
import com.example.forceoffline.Tools.MyDatabaseHelper;

import java.io.BufferedReader;

public class RegisterActivity extends BaseActivity {

    private MyDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        helper = new MyDatabaseHelper(RegisterActivity.this, "usr.db", null, 1);

        ((Button) findViewById(R.id.btnCancer)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseActivity.finishAll();
            }
        });
        ((Button) findViewById(R.id.btnRegister)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase database = helper.getWritableDatabase();
                String user = ((EditText) findViewById(R.id.etUser)).getText().toString().trim();
                String pwd = ((EditText) findViewById(R.id.etPsw)).getText().toString().trim();

                if (user.equals("") || pwd.equals("")) {
                    Toast.makeText(RegisterActivity.this, "用户名，密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    String sql = "select * from user where user = '" + user+"'";
                    Cursor cursor = database.rawQuery(sql, null);
                    if (cursor.moveToFirst()) {
                        Toast.makeText(RegisterActivity.this, "用户名已存在，请重试", Toast.LENGTH_SHORT).show();
                        ((EditText) findViewById(R.id.etUser)).setText("");
                        ((EditText) findViewById(R.id.etPsw)).setText("");
                    } else {
                        ContentValues values = new ContentValues();
                        values.put("user", user);
                        values.put("pwd", pwd);
                        database.insert("user", "null", values);
                        database.close();
                        finish();
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
