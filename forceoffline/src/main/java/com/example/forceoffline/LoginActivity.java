package com.example.forceoffline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forceoffline.Tools.BaseActivity;
import com.example.forceoffline.Tools.MyDatabaseHelper;

import org.w3c.dom.Text;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivityTest";
    private MyDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        helper = new MyDatabaseHelper(this, "usr.db", null, 1);
        helper.getWritableDatabase();
        remeber();

        ((Button) findViewById(R.id.btnLogin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase database =helper.getWritableDatabase();
                String user = ((TextView) findViewById(R.id.etPsw)).getText().toString();
                String pwd = ((TextView) findViewById(R.id.etUser)).getText().toString();
                if (user.equals("") || pwd.equals("")) {
                    Toast.makeText(LoginActivity.this, "用户名，密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    String selectSql =String.format("select * from user where user ='%s' and pwd = '%s'",user,pwd);

                    Cursor cursor =database.rawQuery(selectSql,null);
                    if(cursor.moveToFirst()) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        if (((CheckBox) findViewById(R.id.rmbPwd)).isChecked()) {
                            SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                            editor.putString("userName", user);
                            editor.putString("pwd", pwd);
                            editor.putBoolean("isRmb", ((CheckBox) findViewById(R.id.rmbPwd)).isChecked());
                            editor.apply();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
                    }
                }
                database.close();
            }
        });
        ((Button) findViewById(R.id.btnCancer)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseActivity.finishAll();
            }
        });
    }

    public void clickRegister(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void remeber() {
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        ((EditText) findViewById(R.id.etUser)).setText(sharedPreferences.getString("userName", ""));
        ((EditText) findViewById(R.id.etPsw)).setText(sharedPreferences.getString("pwd", ""));
        ((CheckBox) findViewById(R.id.rmbPwd)).setChecked(sharedPreferences.getBoolean("isRmb", false));
    }
}
