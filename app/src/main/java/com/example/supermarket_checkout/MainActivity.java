package com.example.supermarket_checkout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Model> modelList;
    RecyclerView recyclerView;
    OrderAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.shopping);

        // creating an arraylist

        modelList = new ArrayList<>();
        modelList.add(new Model("Burger", getString(R.string.burger), R.drawable.burger));
        modelList.add(new Model("Freeze Chicken", getString(R.string.checken), R.drawable.checken));
        modelList.add(new Model("Dairy milk", getString(R.string.dairymilk), R.drawable.dairymilk));
        modelList.add(new Model("Cheese", getString(R.string.gebna), R.drawable.gebna));
        modelList.add(new Model("President triangle cheese", getString(R.string.president3), R.drawable.president3));
        modelList.add(new Model("Doritos", getString(R.string.doritos), R.drawable.doritos));
        modelList.add(new Model("Almarai Yogurt", getString(R.string.zabady), R.drawable.zabady));
        modelList.add(new Model("Chipsy", getString(R.string.chepsi), R.drawable.chepsi));


        // recyclerview
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        // adapter
        mAdapter = new OrderAdapter(this, modelList);
        recyclerView.setAdapter(mAdapter);

    }
}