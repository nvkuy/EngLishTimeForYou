package com.example.nguyentuan.tracnghiem1.word;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.CursorIndexOutOfBoundsException;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyentuan.tracnghiem1.R;

import java.util.ArrayList;
import java.util.Locale;

public class WordTraining extends AppCompatActivity {

    int TrueAns;
    int FalseAns;
    int NoAns;

    boolean IsNoAns = false;

    ArrayList<word_ques> Arr_WordQues = new ArrayList<word_ques>();
    WordTraining_Controller wordTraining_controller = new WordTraining_Controller(getApplication());
    TextToSpeech speech;
    SharedPreferences sharedPreferences;

    TextView tvQues, tvTrueAns;
    Button btnDongy, btnBoQua, btnContinue, btnListenQues;
    ImageView imgWordQues;
    EditText editAns;

    int[] arrNumQues = new int[21]; // mảng NumQues trong DB
    String topic;
    /**
     * Uy 6/25/2017.
     *
     * NumQues ở đây chỉ là số câu hiện tại
     * dùng để xét duyệt các phần tử trong mảng câu hỏi lấy từ DB
     * ko phải là NumQues trong DB.
     */
    int NumQues = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_training);
        final Intent i = getIntent();
        arrNumQues = i.getIntArrayExtra("arrNumQues");
        topic = i.getStringExtra("Topic");
        getSupportActionBar().setTitle(topic);
        start();
        sharedPreferences = getSharedPreferences(topic, MODE_PRIVATE);

        Arr_WordQues = getData();
        Log.d("Gem", Arr_WordQues.size() + " size của Arr_WordQues");
        tvQues.setText(Arr_WordQues.get(NumQues - 1).getQues().toString());
        Log.d("?", Arr_WordQues.get(NumQues - 1).getImage() + "");
        showQues();

        btnDongy.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            try {
                                                IsNoAns = false;
                                                checkAns();
                                                btnBoQua.setVisibility(View.INVISIBLE);
                                                btnDongy.setVisibility(View.INVISIBLE);
                                            } catch (Exception e) {

                                            }
                                        }
                                    }
        );

        btnContinue.setOnClickListener(new View.OnClickListener()

                                       {
                                           @Override
                                           public void onClick(View view) {
                                               try {
                                                   tvTrueAns.setVisibility(View.GONE);
                                                   editAns.setVisibility(View.VISIBLE);
                                                   editAns.setText("");
                                                   NumQues++;
                                                   tvQues.setText(Arr_WordQues.get(NumQues - 1).getQues());
                                                   btnDongy.setVisibility(View.VISIBLE);
                                                   btnBoQua.setVisibility(View.VISIBLE);
                                                   btnContinue.setVisibility(View.GONE);
                                                   showQues();
                                               } catch (Exception e) {
//                                                   Back();
                                                   goto_WordScoreAct();
                                               }
                                           }
                                       }

        );

        btnBoQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDongy.setVisibility(View.INVISIBLE);
                btnBoQua.setVisibility(View.INVISIBLE);
                IsNoAns = true;
                checkAns();
                btnContinue.setVisibility(View.VISIBLE);
            }
        });

    }

    public void goto_WordScoreAct() {
        Intent mIntent = new Intent(getApplication(), Word_ScoreActivity.class);
        mIntent.putExtra("TrueAns", TrueAns);
        mIntent.putExtra("FalseAns", FalseAns);
        mIntent.putExtra("NoAns", NoAns);
        mIntent.putExtra("NumQues", NumQues);
        mIntent.putExtra("Topic", topic);
        mIntent.putExtra("arrNumQues", arrNumQues);
        startActivity(mIntent);
        overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
        finish();
    }

//    public void Back() {
//        NumQues = Math.round(Arr_WordQues.size() / 4) + 1;
//        Intent back = new Intent(getApplication(), LearningWord.class);
//        //lưu vào SharedPreferences
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt("NumQues", NumQues);
//        editor.commit();
//
//        back.putExtra("NumQues", NumQues);
//        back.putExtra("topic", topic);
//        startActivity(back);
//        overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
//    }

    public void checkAns() {
        if (Arr_WordQues.get(NumQues - 1).getTrueAns().equals(editAns.getText().toString())) {
            TrueAns++;
            editAns.setVisibility(View.GONE);
            speech = new TextToSpeech(getApplication(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int i) {
                    if (i != TextToSpeech.ERROR) {
                        speech.setLanguage(Locale.CANADA);
                        speech.speak("Correct", TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
            });

            tvTrueAns.setText("Đáp án đúng: " + Arr_WordQues.get(NumQues - 1).getTrueAns());
            tvTrueAns.setVisibility(View.VISIBLE);
            tvTrueAns.setTextColor(Color.GREEN);
            btnContinue.setVisibility(View.VISIBLE);

        } else if (IsNoAns) {
            NoAns++;
            speech = new TextToSpeech(getApplication(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int i) {
                    if (i != TextToSpeech.ERROR) {
                        speech.setLanguage(Locale.CANADA);
                        speech.speak("Incorrect", TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
            });

            tvTrueAns.setText("Đáp án đúng: " + Arr_WordQues.get(NumQues - 1).getTrueAns());
            tvTrueAns.setVisibility(View.VISIBLE);
            tvTrueAns.setTextColor(Color.RED);
            btnContinue.setVisibility(View.VISIBLE);

        } else {
            FalseAns++;
            editAns.setVisibility(View.GONE);
            speech = new TextToSpeech(getApplication(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int i) {
                    if (i != TextToSpeech.ERROR) {
                        speech.setLanguage(Locale.CANADA);
                        speech.speak("Incorrect", TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
            });

            tvTrueAns.setText("Đáp án đúng: " + Arr_WordQues.get(NumQues - 1).getTrueAns());
            tvTrueAns.setVisibility(View.VISIBLE);
            tvTrueAns.setTextColor(Color.RED);
            btnContinue.setVisibility(View.VISIBLE);
        }


    }

    public void start() {
        tvTrueAns = (TextView) findViewById(R.id.TrueAns);
        tvQues = (TextView) findViewById(R.id.tvQues);
        editAns = (EditText) findViewById(R.id.editAns);
        btnDongy = (Button) findViewById(R.id.btnOk);
        btnBoQua = (Button) findViewById(R.id.btnSkip);
        btnContinue = (Button) findViewById(R.id.btnContinue);
        imgWordQues = (ImageView) findViewById(R.id.imgWordQues);
        btnListenQues = (Button) findViewById(R.id.btnListenQues);
    }

    public ArrayList<word_ques> getData() {
        ArrayList<word_ques> Arr_WordQues = new ArrayList<word_ques>();
        wordTraining_controller = new WordTraining_Controller(getApplication());
        Arr_WordQues = wordTraining_controller.getArr_WordQues(topic, arrNumQues);
        return Arr_WordQues;
    }


    public void showQues() {
        if (Arr_WordQues.get(NumQues - 1).getImage() != null) {
            btnListenQues.setVisibility(View.GONE);
            imgWordQues.setVisibility(View.VISIBLE);
            imgWordQues.setImageResource(getResources().getIdentifier(Arr_WordQues.get(NumQues - 1).getImage(), "drawable", "com.example.nguyentuan.tracnghiem1"));
        } else if (Arr_WordQues.get(NumQues - 1).getListen() != null) {
            imgWordQues.setVisibility(View.GONE);
            btnListenQues.setVisibility(View.VISIBLE);
            speech = new TextToSpeech(getApplication(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int i) {
                    if (i != TextToSpeech.ERROR) {
                        speech.setLanguage(Locale.ENGLISH);
                        speech.speak(Arr_WordQues.get(NumQues - 1).getListen(), TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
            });

            btnListenQues.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    speech = new TextToSpeech(getApplication(), new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int i) {
                            if (i != TextToSpeech.ERROR) {
                                speech.setLanguage(Locale.ENGLISH);
                                speech.speak(Arr_WordQues.get(NumQues - 1).getListen(), TextToSpeech.QUEUE_FLUSH, null);
                            }
                        }
                    });
                }
            });
        } else {
            imgWordQues.setVisibility(View.GONE);
            btnListenQues.setVisibility(View.GONE);
            tvQues.setText(Arr_WordQues.get(NumQues - 1).getQues());
        }
    }
}
