package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button sendData;
    private Gson gson;
    TextView message;
    String passQ;
    String passC;
    String passA;
    String passB;
    String passD;
    int questionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendData = findViewById(R.id.sendData);
        message = findViewById(R.id.message);

        gson = new Gson();
        new extractData().execute();



    }

    private class extractData extends AsyncTask<String, Void, String> {
        private String uri;

    extractData(){
        uri="http://"+URIHandler.hostName+"/questions";
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

        Question[] A = gson.fromJson(json,Question[].class);

        List<Question> questionList = Arrays.asList(A);


        ArrayList<Question> arrayList = new ArrayList<>(questionList);

        Log.d("TAG", "List size: " + String.valueOf(arrayList.size()));

       listView = findViewById(R.id.list_view);
//
        CustomAdapter adapter = new CustomAdapter(this, R.layout.list_item, arrayList);
        listView.setAdapter(adapter);


        sendData.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                boolean allSelected = true;


                // Check if all radio buttons are selected
                for (int i = 0; i < listView.getAdapter().getCount(); i++) {
                    View itemView = listView.getChildAt(i);

                    RadioGroup answersRadioGroup = itemView.findViewById(R.id.answers_radio_group);

                    int checkedRadioButtonId = answersRadioGroup.getCheckedRadioButtonId();

                    if (checkedRadioButtonId == -1) {
                        allSelected = false;
                        break;
                    }
                }

                // If all radio buttons are selected, get the selected answer for each question
                if (allSelected) {
                    // Assuming the question object array is called questionArray and contains a list of questions
                    int questionIndex = 0; // Replace this with the index of the question you want to display
                    Question question = arrayList.get(questionIndex);

                    View itemView = listView.getChildAt(questionIndex);

                    RadioGroup answersRadioGroup = itemView.findViewById(R.id.answers_radio_group);
                    int checkedRadioButtonId = answersRadioGroup.getCheckedRadioButtonId();
                    questionId = question.getQuestion_id();
                    Log.d(String.valueOf(questionId), "onClick: ");
                     passQ= question.getQuestion_name();
                    Log.d(passQ, "question ");
                     passA = question.getOpt_a();
                    Log.d(passA, "ans ");
                     passB = question.getOpt_b();
                    passC = question.getOpt_c();
                    passD = question.getOpt_d();
                    if (checkedRadioButtonId != -1) {
                        RadioButton checkedRadioButton = itemView.findViewById(checkedRadioButtonId);

                        Object tag = checkedRadioButton.getTag();

                        if (tag != null) {
                            String selectedAnswer = tag.toString();

                            new NewUserTask(selectedAnswer).execute();
                            message.setText("selected" + selectedAnswer + "\n");

                            message.setTextColor(Color.parseColor("#006400"));
                            // Do something with the selected answer
                        }
                    } else {
                        message.setText("Every questions must be answered!");
                        message.setTextColor(Color.parseColor("#FF0000"));
                        // Show error message that all questions need to be answered
                    }

                } else {
                    message.setText("Every questions must be answred!");
                    message.setTextColor(Color.parseColor("#FF0000"));
                    // Show error message that all questions need to be answered
                }
            }




        });

    }

    private class NewUserTask extends AsyncTask<String, Void, String> {
        private String uri;
        private String response;
        

        NewUserTask(String response) {
            uri = "http://" + URIHandler.hostName + "/responses";

            this.response = response;
        }

        @Override
        protected String doInBackground(String... urls) {

            return URIHandler.doPost(uri, "{\"question_id\":\"" + questionId+ "\",\"response\":\"" + response + "\"}");
        }



        @Override
        protected void onPostExecute(String result) {
            if(result.isEmpty())
                message.setText("Wasn't completes");
            else {
                message.setText("Data send sucessfull");
                Intent intent = new Intent(MainActivity.this, display.class);
                intent.putExtra("id", String.valueOf(questionId));
                intent.putExtra("qname", passQ);
                intent.putExtra("a", passA);
                intent.putExtra("b", passB);
                intent.putExtra("c", passC);
                intent.putExtra("d", passD);
                Log.d("MainActivity", "Starting display activity with intent: " + intent.toString());
                startActivity(intent);

            }
        }
    }
}