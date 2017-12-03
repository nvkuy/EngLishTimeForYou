package com.example.nguyentuan.tracnghiem1.dict;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguyentuan.tracnghiem1.R;

import java.util.ArrayList;

/**
 * Created by toannq on 6/19/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    public WordAdapter(Context context, ArrayList<Word> words) {
        super(context, 0, words);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_word, parent, false);
        }
        TextView tvKind = (TextView) convertView.findViewById(R.id.tvKind);
        TextView tvEng = (TextView) convertView.findViewById(R.id.tv_eng_word);
        TextView tvViet = (TextView) convertView.findViewById(R.id.tv_viet_word);
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.word_icon);

        Word p = getItem(position);
        if (p != null) {
            tvKind.setText(p.getKind().toString());
            tvEng.setText(p.getEngLish().toString() + ": ");
            tvViet.setText(p.getVietNam().toString());
            imgIcon.setImageResource(R.drawable.subject);
        }

        //Xử lí animation
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.adapter_anim);
        convertView.startAnimation(animation);

        return convertView;
    }
}
