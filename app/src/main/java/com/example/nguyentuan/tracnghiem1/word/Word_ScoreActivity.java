package com.example.nguyentuan.tracnghiem1.word;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nguyentuan.tracnghiem1.R;

public class Word_ScoreActivity extends AppCompatActivity {

    int NumQues, NoAns, TrueAns, FalseAns;
    String Description, topic;
    int[] arrNumQues = new int[21];
    double rs;

    SharedPreferences sharedPreferences;

    TextView tvTrueAns, tvFalseAns, tvNoAns, tvDes;
    Button btn_again, btn_continue, btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word__score);

        Intent i = getIntent();
        TrueAns = i.getIntExtra("TrueAns", 0);
        FalseAns = i.getIntExtra("FalseAns", 0);
        NoAns = i.getIntExtra("NoAns", 0);
        NumQues = i.getIntExtra("NumQues", 0);
        topic = i.getStringExtra("Topic");
        arrNumQues = i.getIntArrayExtra("arrNumQues");

        sharedPreferences = getSharedPreferences(topic, MODE_PRIVATE);

        start();

        rs = TrueAns * 100 / (NumQues - 1);

        if (rs <= 25) {
            Description = "Chưa thuộc";
        } else if (rs <= 50 && rs > 25) {
            Description = "Thuộc một ít";
        } else if (rs <= 75 && rs > 50) {
            Description = "Gần thuộc";
        } else {
            Description = "Đã thuộc";
        }

        tvTrueAns.setText(TrueAns + " câu");
        tvFalseAns.setText(FalseAns + " câu");
        tvNoAns.setText(NoAns + " câu");
        tvDes.setText(Description);

        if (Description.equals("Gần thuộc") || Description.equals("Đã thuộc")) {
            btn_continue.setVisibility(View.VISIBLE);
        } else {
            tvDes.setTextColor(Color.RED);
        }

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Continue();
            }
        });

        btn_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Again();
            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Exit();
            }
        });
    }

    public void start() {

        tvTrueAns = (TextView) findViewById(R.id.tvTrueAns);
        tvFalseAns = (TextView) findViewById(R.id.tvFalseAns);
        tvNoAns = (TextView) findViewById(R.id.tvNoAns);
        tvDes = (TextView) findViewById(R.id.tvDescription);

        btn_exit = (Button) findViewById(R.id.btn_exit);
        btn_continue = (Button) findViewById(R.id.btn_continue);
        btn_again = (Button) findViewById(R.id.btn_again);
    }

    public void Continue() {

        NumQues = Math.round(NumQues / 4) + 1;

        //lưu vào SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("NumQues", NumQues);
        editor.commit();

        Intent conti = new Intent(getApplication(), LearningWord.class);
        conti.putExtra("topic", topic);
        conti.putExtra("NumQues", NumQues);
        startActivity(conti);
        overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);

    }

    public void Again() {

        Intent again = new Intent(getApplication(), WordTraining.class);
        again.putExtra("Topic", topic);
        again.putExtra("arrNumQues", arrNumQues);
        startActivity(again);
        overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
    }

    public void Exit() {
        finish();
    }
}
