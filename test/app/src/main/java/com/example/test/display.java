package com.example.test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class display extends AppCompatActivity {
     int questionID;
     String Question;
     String opta;
     String optb;
    String optc;
    String optd;

    private String question;

    private Gson gson;

    ListView listview1;
    TextView textset;
    ArrayList<Option> arrNames = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.display);



        String random = getIntent().getStringExtra("id");
        Question = getIntent().getStringExtra("qname");
        opta = getIntent().getStringExtra("a");
        textset = findViewById(R.id.title_text);
        textset.setText(Question);

       optb = getIntent().getStringExtra("b");
       optc = getIntent().getStringExtra("c");
       optd = getIntent().getStringExtra("d");


         questionID = Integer.parseInt(random);

        gson = new Gson();
        new display.extractData().execute();

    }

    private class extractData extends AsyncTask<String, Void, String> {
        private String uri;

        extractData(){
            uri="http://"+URIHandler.hostName+"/summary?question="+questionID;
        }


        protected String doInBackground(String... urls) {
            return URIHandler.doGet(uri, "");
        }

        // onPostExecute displays the results of the AsyncTask.

        protected void onPostExecute(String result) {
            loadHandles(result);
        }
    }

    private void loadHandles(String json) {
        gson = new Gson();

        Option[] A = gson.fromJson(json, Option[].class);


        List<Option> questionList = Arrays.asList(A);

        Log.d("DisplayActivity", "Json: " + json);

        Option optionA = questionList.get(0);

        Option optionB = questionList.get(1);
        Option optionC = questionList.get(2);
        Option optionD = questionList.get(3);


        arrNames.add(new Option(opta, optionA.getValue()));

        arrNames.add(new Option(optb, optionB.getValue()));
        arrNames.add(new Option(optc, optionC.getValue()));
        arrNames.add(new Option(optd, optionD.getValue()));




        listview1 = findViewById(R.id.list_view2);
//
       // ArrayAdapter<Option> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrNames);
        DisplayAdapter adapter = new DisplayAdapter(this,R.layout.list_options,arrNames);
        listview1.setAdapter(adapter);

    }

}
