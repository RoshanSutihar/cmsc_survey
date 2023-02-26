package com.example.test;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button sendData;
    private Gson gson;
    TextView message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // create sample data
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("What is your name?", "Roshan", "Ram", "Shyam", "Hari"));
        questions.add(new Question("What is your name?", "Ram", "Ram", "Shyam", "Hari"));

        // create custom adapter and set to ListView
        CustomAdapter adapter = new CustomAdapter(this, R.layout.list_item, questions);
        listView = findViewById(R.id.list_view);
        sendData = findViewById(R.id.sendData);
        message = findViewById(R.id.message);
        listView.setAdapter(adapter);

        gson = new Gson();


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
                    for (int i = 0; i < listView.getAdapter().getCount(); i++) {
                        View itemView = listView.getChildAt(i);

                        RadioGroup answersRadioGroup = itemView.findViewById(R.id.answers_radio_group);

                        int checkedRadioButtonId = answersRadioGroup.getCheckedRadioButtonId();

                        if (checkedRadioButtonId != -1) {
                            RadioButton checkedRadioButton = itemView.findViewById(checkedRadioButtonId);

                            Object tag = checkedRadioButton.getTag();

                            if (tag != null) {
                                String selectedAnswer = tag.toString();
                                message.setText("selected" + selectedAnswer + "\n");
                                message.setTextColor(Color.parseColor("#006400"));
                                // Do something with the selected answer
                            }
                        }
                    }
                } else {
                    message.setText("Every questions must be answred!");
                    message.setTextColor(Color.parseColor("#FF0000"));
                    // Show error message that all questions need to be answered
                }
            }




        });
    }
}