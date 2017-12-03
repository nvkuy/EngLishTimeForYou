package com.example.nguyentuan.tracnghiem1.word;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguyentuan.tracnghiem1.CSDL.DBHelper;
import com.example.nguyentuan.tracnghiem1.MainActivity;
import com.example.nguyentuan.tracnghiem1.R;

import java.util.ArrayList;
import java.util.Locale;

public class LearningWord extends AppCompatActivity {

    ArrayList<TuVung> Arr_word = new ArrayList<TuVung>();
    DBHelper dbHelper = new DBHelper(getApplication());
    int[] arrNumQues = new int[21];

    LearningWord_Controller learningWord_controller;
    TextToSpeech speech;
    String topic;
    int NunQues;
    String img;
    ImageView imgWord;
    TextView tvTiengAnh, tvTiengViet;
    Button btnListen, btnMore, btnExam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_word);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        topic = i.getStringExtra("topic");
        NunQues = i.getIntExtra("NumQues", 1);
        getSupportActionBar().setTitle(topic);
        start();
        Arr_word = getData();
        tuvung();
        btnListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phatam();
            }
        });
        arrNumQues[NunQues] = Arr_word.get(0).getNumQues();
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    NunQues++;
                    Arr_word = getData();
                    arrNumQues[NunQues] = Arr_word.get(0).getNumQues();
                    tuvung();
                } catch (Exception e) {

                }
            }
        });

        btnExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplication(), WordTraining.class);
                i.putExtra("Topic", topic);
                i.putExtra("arrNumQues", arrNumQues);
                startActivity(i);
                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                finish();
            }
        });


    }

    public void start() {
        tvTiengAnh = (TextView) findViewById(R.id.tiengAnh);
        tvTiengViet = (TextView) findViewById(R.id.tiengViet);
        imgWord = (ImageView) findViewById(R.id.word_img);
        btnListen = (Button) findViewById(R.id.nghe);
        btnExam = (Button) findViewById(R.id.btnExam);
        btnMore = (Button) findViewById(R.id.btnLearn);
    }

    public void tuvung() {
        tvTiengAnh.setText(Arr_word.get(0).getTiengAnh() + ":");
        tvTiengViet.setText(Arr_word.get(0).getTiengViet().toString());
        img = Arr_word.get(0).getImage().toString();
        imgWord.setImageResource(getResources().getIdentifier(Arr_word.get(0).getImage() + "", "drawable", "com.example.nguyentuan.tracnghiem1"));
    }

    public void phatam() {
        speech = new TextToSpeech(getApplication(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    speech.setLanguage(Locale.ENGLISH);
                    speech.speak(Arr_word.get(0).getTiengAnh().toString(), TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }

    public ArrayList<TuVung> getData() {
        ArrayList<TuVung> Arr_word = new ArrayList<TuVung>();
        learningWord_controller = new LearningWord_Controller(getApplication());
        Arr_word = learningWord_controller.getArr_word(NunQues, topic);
        return Arr_word;
    }

}
