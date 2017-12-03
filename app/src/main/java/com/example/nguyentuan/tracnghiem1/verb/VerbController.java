package com.example.nguyentuan.tracnghiem1.verb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nguyentuan.tracnghiem1.CSDL.DBHelper;

import java.util.ArrayList;

/**
 * Created by toannq on 6/18/2017.
 */

public class VerbController {
    private DBHelper dbHelper;

    public VerbController(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<Verb> get_verb(String TenThi) {
        ArrayList<Verb> verbs = new ArrayList<Verb>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM ThiTiengAnh WHERE TenThi='" + TenThi + "'", null);
        cursor.moveToFirst();
        do {
            Verb item;
            item = new Verb(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
            verbs.add(item);
        } while (cursor.moveToNext());
        return verbs;
    }
}
