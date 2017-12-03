package com.example.nguyentuan.tracnghiem1.score;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.nguyentuan.tracnghiem1.R;

/**
 * Created by toannq on 11/20/2016.
 */

public class ScoreAdapter extends CursorAdapter {
    public ScoreAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_list_score, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvInfoScore = (TextView) view.findViewById(R.id.tvInfoScore);
        TextView tvScore = (TextView) view.findViewById(R.id.tv_score);
        TextView tvName = (TextView) view.findViewById(R.id.tvNameStudent);

        tvName.setText(cursor.getString(1));
        tvScore.setText(cursor.getString(2));
        tvInfoScore.setText(cursor.getString(4));
    }
}
