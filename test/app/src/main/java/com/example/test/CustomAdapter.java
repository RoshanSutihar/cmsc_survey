package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Question> {

    private ArrayList<Question> questions;
private int question_id;
    public CustomAdapter(Context context, int resource, ArrayList<Question> questions) {
        super(context, resource, questions);
        this.questions = questions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Question question = questions.get(position);
        int questionId = question.question_id;

        TextView questionTextView = convertView.findViewById(R.id.question_text_view);
        questionTextView.setText(question.getQuestion_name());
        questionTextView.setTag(questionId);
        RadioGroup answersRadioGroup = convertView.findViewById(R.id.answers_radio_group);

        RadioButton answer1RadioButton = convertView.findViewById(R.id.answer_1_radio_button);
        answer1RadioButton.setText(question.getOpt_a());
        answer1RadioButton.setTag('a');

        RadioButton answer2RadioButton = convertView.findViewById(R.id.answer_2_radio_button);
        answer2RadioButton.setText(question.getOpt_b());
        answer2RadioButton.setTag('b');

        RadioButton answer3RadioButton = convertView.findViewById(R.id.answer_3_radio_button);
        answer3RadioButton.setText(question.getOpt_c());
        answer3RadioButton.setTag('c');

        RadioButton answer4RadioButton = convertView.findViewById(R.id.answer_4_radio_button);
        answer4RadioButton.setText(question.getOpt_d());
        answer4RadioButton.setTag('d');
        return convertView;
    }
    @Override
    public Question getItem(int position) {
        return questions.get(position);
    }

    @Override
    public int getCount() {
        return questions.size();
    }
}
