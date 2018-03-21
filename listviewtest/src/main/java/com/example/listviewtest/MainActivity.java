package com.example.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listviewtest.Tools.Fruit;
import com.example.listviewtest.Tools.FruitAdapter;
import com.example.listviewtest.Tools.FruitRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        ListView布局
         */
//        setContentView(R.layout.activity_main);
//        FruitInit();
//        FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item_layout, fruitList);
//        ListView listView = (ListView) findViewById(R.id.fruit_list_view);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Fruit fruit =(Fruit)fruitList.get(i);
//                Toast.makeText(MainActivity.this,fruit.getName(),Toast.LENGTH_SHORT).show();
//            }
//        });


        /*
        RecyclerView布局
         */
        setContentView(R.layout.activity_main2);
        FruitInit();
        FruitRecyclerAdapter fruitRecyclerAdapter = new FruitRecyclerAdapter(fruitList);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(fruitRecyclerAdapter);


    }

    private void FruitInit() {
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit("apple", R.drawable.apple);
            fruitList.add(apple);
            Fruit banana = new Fruit("banana", R.drawable.banana);
            fruitList.add(banana);
            Fruit cherry = new Fruit("cherry", R.drawable.cherry);
            fruitList.add(cherry);
            Fruit grape = new Fruit("grape", R.drawable.grape);
            fruitList.add(grape);
            Fruit mango = new Fruit("mango", R.drawable.mango);
            fruitList.add(mango);
            Fruit orange = new Fruit("orange", R.drawable.orange);
            fruitList.add(orange);
            Fruit pear = new Fruit("pear", R.drawable.pear);
            fruitList.add(pear);
        }
    }
}
