package com.example.nguyentuan.tracnghiem1.dict;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nguyentuan.tracnghiem1.CSDL.DBHelper;

import java.util.ArrayList;

/**
 * Created by toannq on 6/19/2017.
 */

public class WordController {
    private DBHelper dbHelper;

    public WordController(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<Word> getWord(String TuKhoa) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Word> lsData = new ArrayList<Word>();
        Cursor cursor = db.rawQuery("SELECT * FROM Dict WHERE English LIKE '%" + TuKhoa + "%' OR Vietnam LIKE '%" + TuKhoa + "%'", null);
        cursor.moveToFirst();
        do {
            Word item;
            item = new Word(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            lsData.add(item);
        } while (cursor.moveToNext());
        return lsData;
    }
}
