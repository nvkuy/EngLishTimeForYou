package com.example.nguyentuan.tracnghiem1.question;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguyentuan.tracnghiem1.R;
import com.example.nguyentuan.tracnghiem1.question.Exam;

import java.util.ArrayList;

/**
 * Created by toannq on 11/10/2016.
 */

public class ExamAdapter extends ArrayAdapter<Exam> {
    public ExamAdapter(Context context, ArrayList<Exam> exam) {
        super(context, 0, exam);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_gridview, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tvNumExam);
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);

        Exam p = getItem(position);
        if (p != null) {
            tvName.setText("" + p.getName());
            imgIcon.setImageResource(R.drawable.blogicon);
        }

        return convertView;
    }
}
