package com.example.nguyentuan.tracnghiem1.word;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nguyentuan.tracnghiem1.CSDL.DBHelper;

import java.util.ArrayList;

/**
 * Created by toannq on 6/23/2017.
 */

public class LearningWord_Controller {
    private DBHelper dbHelper;

    public LearningWord_Controller(Context context){
        dbHelper = new DBHelper(context);
    }

    public ArrayList<TuVung> getArr_word(int i, String Topic){
        ArrayList<TuVung> arr_word = new ArrayList<TuVung>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Word WHERE Topic='" + Topic + "' AND NumQues='"+ i +"'", null);
        //request cua em can topic de search ah khong, q
        //cái topci  e nhân tư adctir
        cursor.moveToFirst();

        do {
            TuVung item;
            item = new TuVung(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5));
            arr_word.add(item);
        } while (cursor.moveToNext());
        cursor.close();
        return arr_word;
    }
}
