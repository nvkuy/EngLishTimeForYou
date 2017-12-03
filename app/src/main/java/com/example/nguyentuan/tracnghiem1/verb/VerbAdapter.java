package com.example.nguyentuan.tracnghiem1.verb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguyentuan.tracnghiem1.R;
import com.example.nguyentuan.tracnghiem1.verb.Sverb;

import java.util.ArrayList;

/**
 * Created by toannq on 6/18/2017.
 */

public class VerbAdapter extends ArrayAdapter<Sverb> {
    public VerbAdapter(Context context, ArrayList<Sverb> sverb) {
            super(context, 0, sverb);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.verb_line, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_verb);
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.iconVerb);

        Sverb p = getItem(position);
        if (p != null) {
            tvName.setText("Thì " + p.getName().toString());
            imgIcon.setImageResource(R.drawable.subject);
        }

        return convertView;
    }
}