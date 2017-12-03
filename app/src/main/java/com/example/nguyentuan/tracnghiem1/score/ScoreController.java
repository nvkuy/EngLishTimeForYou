package com.example.nguyentuan.tracnghiem1.score;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nguyentuan.tracnghiem1.CSDL.DBHelper;

/**
 * Created by toannq on 11/20/2016.
 */

public class ScoreController {
    private DBHelper dbHelper;

    public ScoreController(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void insertScore(String name, int score, String info) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("score", score);
        values.put("room", info);
        db.insert("tbscore", null, values);
        db.close();
    }


    //Lấy danh sách điểm
    public Cursor getScore() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("tbscore", //tên bảng
                null, //danh sách COLUMN
                null, //điều kiện WHERE
                null, //đối số WHERE
                null, //biểu thức Groupby
                null, //biểu thức Having
                "_id DESC", //biểu thức orderby
                null
        );
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
