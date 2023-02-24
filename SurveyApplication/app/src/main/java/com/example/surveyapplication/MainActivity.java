package com.example.surveyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    list_view =



    ArrayList<Question> questionsList = new ArrayList<>();

    questionList.add("What is your name?", Arrays.asList("roshan", "ram", "shyam", "hari"));

    CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.list_item, questionsList);

}