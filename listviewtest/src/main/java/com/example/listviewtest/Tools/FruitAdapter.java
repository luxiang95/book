package com.example.listviewtest.Tools;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listviewtest.R;

import java.util.List;

/**
 * Created by luxia on 2018/3/14.
 */

public class FruitAdapter extends ArrayAdapter {
    private int resourceId;

    public FruitAdapter(@NonNull Context context,  int textViewResourceId, @NonNull List objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Fruit fruit = (Fruit) getItem(position);
        View view ;
        ViewHolder viewHolder;
        if(convertView==null) {
               view =LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
               viewHolder =new ViewHolder();
               viewHolder.imageView=(ImageView)view.findViewById(R.id.fruit_image);
               viewHolder.fruitName=(TextView)view.findViewById(R.id.fruit_name);
               view.setTag(viewHolder);
        }else
        {
            view =convertView;
            viewHolder=(ViewHolder)view.getTag();
        }

        (viewHolder.imageView).setImageResource(fruit.getImageId());
        (viewHolder.fruitName).setText(fruit.getName());
        return view;
    }
    class ViewHolder{
        ImageView imageView;
        TextView fruitName;
    }
}
