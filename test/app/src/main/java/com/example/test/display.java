package com.example.test;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class display extends AppCompatActivity {
    ListView listview1;
    ArrayList<Option> arrNames = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        listview1 = findViewById(R.id.list_view2);
        arrNames.add(new Option("c", 13));
        arrNames.add(new Option("d", 18));

        DisplayAdapter adapter = new DisplayAdapter(this,R.layout.list_options,arrNames);
    listview1.setAdapter(adapter);
    }





}
