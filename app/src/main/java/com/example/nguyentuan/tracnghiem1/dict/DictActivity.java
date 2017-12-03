package com.example.nguyentuan.tracnghiem1.dict;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nguyentuan.tracnghiem1.CSDL.DBHelper;
import com.example.nguyentuan.tracnghiem1.R;

import java.util.ArrayList;
import java.util.Locale;

public class DictActivity extends AppCompatActivity {

    SearchView searchView;
    ListView lv_word;

    TextToSpeech speech;
    ArrayList<Word> arr_word;
    WordController wordController;
    WordAdapter wordAdapter;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dict);
        getSupportActionBar().setTitle("Từ điển");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv_word = (ListView) findViewById(R.id.lv_word);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dict, menu);
        try {
            lv_word.setVisibility(View.VISIBLE);
            arr_word = getBeginArr_word();
            wordAdapter = new WordAdapter(getApplication(), arr_word);
            lv_word.setAdapter(wordAdapter);
            Speak();
        } catch (Exception e) {
            Toast.makeText(getApplication(), "Không tìm thấy dữ liệu!\n" +
                    "Vui lòng khởi động lại app", Toast.LENGTH_LONG);
            lv_word.setVisibility(View.GONE);
        }

        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    lv_word.setVisibility(View.VISIBLE);
                    wordController = new WordController(getApplication());
                    arr_word = wordController.getWord(newText);
                    wordAdapter = new WordAdapter(getApplication(), arr_word);
                    lv_word.setAdapter(wordAdapter);

                    Speak();
                } catch (Exception e) {
                    lv_word.setVisibility(View.GONE);
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public void Speak() {
        lv_word.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pos = i;
                speech = new TextToSpeech(getApplication(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        if (i != TextToSpeech.ERROR) {
                            speech.setLanguage(Locale.ENGLISH);
                            speech.speak(arr_word.get(pos).getEngLish().toString(), TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }
                });
            }


        });
    }

    public ArrayList<Word> getBeginArr_word() {
        DBHelper dbHelper = new DBHelper(getApplication());
        ArrayList<Word> BeginArr_word = new ArrayList<Word>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Dict", null);
        cursor.moveToFirst();

        do {
            Word item;
            item = new Word(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            BeginArr_word.add(item);
        } while (cursor.moveToNext());
        return BeginArr_word;
    }

}

