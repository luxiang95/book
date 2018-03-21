package com.example.test21.Tools;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.test21.R;

/**
 * Created by luxia on 2018/3/14.
 */

public class TitleLayout extends LinearLayout {
    public TitleLayout(final Context context, AttributeSet attrs) {
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.simple,this);
        Button  btnBack =(Button)findViewById(R.id.btnBack);
        Button  btnEdit =(Button)findViewById(R.id.btnEdit);
        btnBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((Activity)context).finish();
            }
        });
        btnEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"You have clicked the Edit button",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
