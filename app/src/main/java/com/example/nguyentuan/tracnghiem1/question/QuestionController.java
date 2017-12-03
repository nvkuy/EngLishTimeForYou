package com.example.nguyentuan.tracnghiem1.question;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nguyentuan.tracnghiem1.CSDL.DBHelper;

import java.util.ArrayList;

/**
 * Created by toannq on 11/12/2016.
 */

public class QuestionController {
    private DBHelper dbHelper;

    public QuestionController(Context context) {
        dbHelper = new DBHelper(context);
    }

    /**
     * num_exem là số đề
     * subject là số bài.
     */

    /**
     * Lấy arr list câu hỏi theo num_exem và subject
     */
    public ArrayList<Question> getQuestion(int num_exam, String subject) {
        ArrayList<Question> lsData = new ArrayList<Question>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM tracnghiem3 WHERE num_exem='" + num_exam + "' AND subject='" + subject + "'", null);

        cursor.moveToFirst();
        do {
            Question item;
            item = new Question(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7), cursor.getString(8), cursor.getString(9), "");
            lsData.add(item);
        } while (cursor.moveToNext());
        return lsData;

    }

}
