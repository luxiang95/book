package com.example.videoview;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

import static android.os.Environment.getExternalStorageDirectory;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView =(VideoView)findViewById(R.id.videoView);
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else
        {
            initVideoPath();
        }
    }

    private void initVideoPath() {
        File file =new File(Environment.getExternalStorageDirectory()+"/test.mp4");
        videoView.setVideoPath(file.getPath());
    }

    public void myClick(View view)
    {
        switch(view.getId())
        {
            case R.id.btnPlay:
                if(!videoView.isPlaying())
                {
                    videoView.start();
                }
                break;
            case R.id.btnPause:
                if(videoView.isPlaying())
                {
                    videoView.pause();
                }
                break;
            case R.id.btnReplay:
                if(videoView.isPlaying())
                {
                    videoView.resume();
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initVideoPath();
                } else {
                    Toast.makeText(this, "你必须接受所有权限才能正常使用", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
                default:
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(videoView!=null)
            videoView.suspend();
    }
}
