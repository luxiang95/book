package com.example.listviewtest.Tools;

import android.widget.ImageView;

/**
 * Created by luxia on 2018/3/14.
 */

public class Fruit {
    private  int imageId;
    private String name;
    public Fruit(String name,int imageId)
    {
        this.name =name;
        this.imageId=imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
