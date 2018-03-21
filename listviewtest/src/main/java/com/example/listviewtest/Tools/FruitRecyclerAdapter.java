package com.example.listviewtest.Tools;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listviewtest.R;

import java.util.List;

/**
 * Created by luxia on 2018/3/14.
 */

public class FruitRecyclerAdapter extends RecyclerView.Adapter<FruitRecyclerAdapter.ViewHolder> {

    private List<Fruit>mFruitList;
    static class ViewHolder extends RecyclerView.ViewHolder
    {
        View fruitView;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View itemView) {
            super(itemView);
            fruitView=itemView;
            fruitImage =(ImageView)itemView.findViewById(R.id.fruit_image);
            fruitName =(TextView)itemView.findViewById(R.id.fruit_name);
        }
    }
    public FruitRecyclerAdapter(List<Fruit>fruitList) {
        mFruitList=fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item_layout,parent,false);
       final ViewHolder viewHolder =new ViewHolder(view);
       viewHolder.fruitView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               int position =viewHolder.getAdapterPosition();
               Fruit fruit =mFruitList.get(position);
               Toast.makeText(view.getContext(),fruit.getName(),Toast.LENGTH_SHORT).show();
           }
       });
        viewHolder.fruitName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position =viewHolder.getAdapterPosition();
                Fruit fruit =mFruitList.get(position);
                Toast.makeText(view.getContext(),fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit =mFruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        holder.fruitImage.setImageResource(fruit.getImageId());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
