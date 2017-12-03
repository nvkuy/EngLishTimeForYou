package com.example.nguyentuan.tracnghiem1.word;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguyentuan.tracnghiem1.R;

import java.util.ArrayList;

/**
 * Created by toannq on 6/18/2017.
 */

public class TopicAdapter extends ArrayAdapter<topic> {
    public TopicAdapter(Context context, ArrayList<topic> u) {
        super(context, 0, u);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.gv_unit_item, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tvNumExam);
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);

        topic p = getItem(position);
        if (p != null) {
            tvName.setText("" + p.getUnit().toString());
            imgIcon.setImageResource(R.drawable.blogicon);
        }

        return convertView;
    }
}
