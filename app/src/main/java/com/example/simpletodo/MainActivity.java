package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> items;

    Button addButton;
    EditText editItem;
    RecyclerView viewItems;
    ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.addButton);
        editItem = findViewById(R.id.editItem);
        viewItems = findViewById(R.id.viewItems);

        // Loads items from file to restore saved data
        loadItems();

        ItemsAdapter.OnLongClickListener onLongClickListener = new ItemsAdapter.OnLongClickListener(){

            @Override
            public void onItemLongClicked(int position) {
                // Delete the item at position from the model
                items.remove(position);

                // Notify the adapter
                itemsAdapter.notifyItemRemoved(position);

                // Notification for user to consume
                Toast.makeText(getApplicationContext(), "Item was removed", Toast.LENGTH_SHORT).show();

                // Save items to file
                saveItems();

            }
        };

        itemsAdapter = new ItemsAdapter(items, onLongClickListener);
        viewItems.setAdapter(itemsAdapter);
        viewItems.setLayoutManager(new LinearLayoutManager(this));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItem = editItem.getText().toString();

                // Add item to the model
                items.add(todoItem);

                // Notify adapter that an item is inserted
                itemsAdapter.notifyItemInserted(items.size()-1);

                // Clear text
                editItem.setText("");

                // Notification for user to consume
                Toast.makeText(getApplicationContext(), "Item was added", Toast.LENGTH_SHORT).show();

                // Save items to file
                saveItems();
            }
        });
    }

    private File getDataFile(){
        return new File(getFilesDir(), "data.txt");
    }

    // This function will load items by reading every line of the data file
    private void loadItems(){
        try {
            items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch(IOException e){
            Log.e("MainActivity", "Error reading items", e);

            // We need to initialize ArrayList if try fails
            items = new ArrayList<>();
        }
    }

    // This function saves items by writing them into the data file
    private void saveItems(){
        try {
            FileUtils.writeLines(getDataFile(), items);
        } catch(IOException e) {
            Log.e("MainActivity", "Error writing items", e);
        }

    }

}