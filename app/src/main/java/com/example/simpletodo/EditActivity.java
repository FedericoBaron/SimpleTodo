package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    EditText editItem;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editItem = findViewById(R.id.editItem);
        saveButton = findViewById(R.id.saveButton);

        getSupportActionBar().setTitle("Edit item");

        // Set text to be the item that's being edited
        editItem.setText(getIntent().getStringExtra(MainActivity.KEY_ITEM_TEXT));

        // Listener for save button click
        saveButton.setOnClickListener(new View.OnClickListener() {
            // Handler for save button click
            @Override
            public void onClick(View v) {
                // Create an intent which will contain the results
                Intent intent = new Intent();

                // Pass the data (results of editing)
                intent.putExtra(MainActivity.KEY_ITEM_TEXT, editItem.getText().toString());
                intent.putExtra(MainActivity.KEY_ITEM_POSITION, getIntent().getExtras().getInt(MainActivity.KEY_ITEM_POSITION));

                // Set the result of the intent
                setResult(RESULT_OK, intent);

                // Finish the activity, close the screen and go back
                finish();
            }
        });
    }
}