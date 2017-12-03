package com.example.nguyentuan.tracnghiem1.word;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.nguyentuan.tracnghiem1.CSDL.DBHelper;

import java.util.ArrayList;

/**
 * Created by toannq on 6/26/2017.
 */

public class WordTraining_Controller {
    private DBHelper dbHelper;

    public WordTraining_Controller(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<word_ques> getArr_WordQues(String topic, int[] arrNumQues) {
        ArrayList<word_ques> Arr_WordQues = new ArrayList<word_ques>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        for (int i = 0; i < arrNumQues.length - 1; i++) {
            Cursor cursor = db.rawQuery("SELECT * FROM word_ques WHERE Topic='" + topic + "' AND NumQues='" + arrNumQues[i] + "'", null);
            cursor.moveToFirst();

            while (cursor.moveToNext()) {
                word_ques item;
                item = new word_ques(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
                Arr_WordQues.add(item);
            }
            cursor.close();
        } return Arr_WordQues;
    }
}
