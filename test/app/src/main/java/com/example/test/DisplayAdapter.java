package com.example.test;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class DisplayAdapter extends ArrayAdapter<Option> {

    private ArrayList<Option> options;

    public DisplayAdapter(Context context, int resource, ArrayList<Option> options) {
        super(context, resource, options);
        this.options = options;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_options, parent, false);
        }

        Option option = options.get(position);

        TextView optionTextView = convertView.findViewById(R.id.option_text);
        optionTextView.setText(option.getOpt_name());

        TextView sumTextView = convertView.findViewById(R.id.votes_text);
        sumTextView.setText(String.valueOf(option.getValue()));

        return convertView;
    }

    @Override
    public Option getItem(int position) {
        return options.get(position);
    }

    @Override
    public int getCount() {
        return options.size();
    }
}