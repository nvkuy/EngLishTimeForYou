package com.example.nguyentuan.tracnghiem1.CSDL;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by toannq on 2/17/2017.
 */

/**
 * đường dẫn ở đây là đường dẫn đên nơi chứa DB
 * trong thiết bị người dùng
 * ko phải là đường dẫn dến file DB trong assets.
 */


public class DBHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.example.nguyentuan.tracnghiem1/databases/";
    //    private static String DB_NAME = "CSDL_20170426055741.sqlite";
    private static String DB_NAME = "CSDL.sqlite";
    private static final int DATABASE_VERSION = 6;

    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void deleteDataBase() {
        String myPath = DB_PATH + DB_NAME;
        SQLiteDatabase.deleteDatabase(new File(myPath));
    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    public boolean checkDataBase() {
        SQLiteDatabase checkDB = null;

        try {

            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {
            //database chưa đc tạo
        }

        if (checkDB != null) {
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    public void copyDataBase() throws IOException {

        //mở input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        //đường dẫn đến DB lưu trong thiết bị người dùng
        String outFileName = DB_PATH + DB_NAME;

        //mở output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //truyền dữ liệu từ inputfile sang outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Dong luon
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public static SQLiteDatabase initDatabase(Activity activity, String databaseName) {
        try {
            String outFileName = activity.getApplicationInfo().dataDir + "/databases/" + databaseName;
            File f = new File(outFileName);
            if (!f.exists()) {
                InputStream e = activity.getAssets().open(databaseName);
                File folder = new File(activity.getApplicationInfo().dataDir + "/databases/");
                if (!folder.exists()) {
                    folder.mkdir();
                }
                FileOutputStream myOutput = new FileOutputStream(outFileName);
                byte[] buffer = new byte[1024];

                int length;
                while ((length = e.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                e.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return activity.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();

        //check DB đã đc tạo chưa
        if (dbExist) {
            //DB đã đc tạo
        } else {
            this.getReadableDatabase();
            try {
                //copy Data từ file assets
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}