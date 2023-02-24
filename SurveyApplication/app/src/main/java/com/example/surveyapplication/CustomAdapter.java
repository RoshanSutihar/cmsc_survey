package com.example.surveyapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class CustomAdapter extends ArrayAdapter<Question> {
    private Context context;
    private List<Question> questionsList;

    public CustomAdapter(Context context, int layoutResourceId, List<Question> questionsList) {
        super(context, layoutResourceId, questionsList);
        this.context = context;
        this.questionsList = questionsList;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.questionTextView = convertView.findViewById(R.id.question_text_view);
            holder.answerRadioGroup = convertView.findViewById(R.id.answer_radio_group);
            holder.answer1RadioButton = convertView.findViewById(R.id.answer1_radio_button);
            holder.answer2RadioButton = convertView.findViewById(R.id.answer2_radio_button);
            holder.answer3RadioButton = convertView.findViewById(R.id.answer3_radio_button);
            holder.answer4RadioButton = convertView.findViewById(R.id.answer4_radio_button);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Question question = questionsList.get(position);
        holder.questionTextView.setText(question.getQuestion());
        holder.answer1RadioButton.setText(question.getAnswers().get(0));
        holder.answer2RadioButton.setText(question.getAnswers().get(1));
        holder.answer3RadioButton.setText(question.getAnswers().get(2));
        holder.answer4RadioButton.setText(question.getAnswers().get(3));

        return convertView;
    }

    private static class ViewHolder {
        TextView questionTextView;
        RadioGroup answerRadioGroup;
        RadioButton answer1RadioButton;
        RadioButton answer2RadioButton;
        RadioButton answer3RadioButton;
        RadioButton answer4RadioButton;
    }
}
