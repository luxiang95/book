package com.example.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivityTest";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        ((EditText)findViewById(R.id.etEdit)).setText(display());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        save(((EditText) findViewById(R.id.etEdit)).getText().toString());
        Log.d(TAG, "onDestroy: ");
    }

    private void save(String s) {
        FileOutputStream outputStream = null;
        BufferedWriter bufferedWriter = null;
        try {
            outputStream = openFileOutput("data", Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private String display()
    {
        FileInputStream fileInputStream=null;
        BufferedReader reader=null;
        StringBuilder content=new StringBuilder();
        try{
            fileInputStream=openFileInput("data");
            reader  =new BufferedReader(new InputStreamReader(fileInputStream));
            String line ="";
            while((line=reader.readLine())!=null)
            {
                content.append(line);
            }
        }catch(IOException e)
        {
            e.printStackTrace();
        }finally {
            try{
                if(reader!=null)
                    reader.close();
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        Log.d(TAG, "display"+content.toString());
        return content.toString();

    }
}
