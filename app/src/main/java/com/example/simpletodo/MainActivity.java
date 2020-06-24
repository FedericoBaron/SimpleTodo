package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> items;

    Button addButton;
    EditText editText;
    RecyclerView viewItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.addButton);
        editText = findViewById(R.id.editItem);
        viewItems = findViewById(R.id.viewItems);

        items = new ArrayList<>();
        items.add("Buy milk");
        items.add("Go to the Gym");
        items.add("Buy cheese");

        ItemsAdapter itemsAdapter = new ItemsAdapter(items);
        viewItems.setAdapter(itemsAdapter);
        viewItems.setLayoutManager(new LinearLayoutManager(this));
    }
}