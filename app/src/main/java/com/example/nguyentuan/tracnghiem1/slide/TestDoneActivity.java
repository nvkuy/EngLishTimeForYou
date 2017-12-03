package com.example.nguyentuan.tracnghiem1.slide;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyentuan.tracnghiem1.R;
import com.example.nguyentuan.tracnghiem1.question.Question;
import com.example.nguyentuan.tracnghiem1.score.ScoreController;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TestDoneActivity extends AppCompatActivity {

    ArrayList<Question> arr_QuesBegin = new ArrayList<Question>();
    int numNoAns = 0;
    int numTrue = 0;
    int numFalse = 0;
    int totalScore = 0;

    String info;

    ScoreController scoreController;

    TextView tvTrue, tvFalse, tvNotAns, tvTotalScore;
    Button btnSaveScore, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_done);
        scoreController = new ScoreController(TestDoneActivity.this);

        Intent intent = getIntent();
        info = intent.getStringExtra("info");
        arr_QuesBegin = (ArrayList<Question>) intent.getExtras().getSerializable("arr_Ques");
        begin();
        checkResult();
        totalScore = numTrue * 10;
        tvNotAns.setText("" + numNoAns);
        tvFalse.setText("" + numFalse);
        tvTrue.setText("" + numTrue);
        tvTotalScore.setText("" + totalScore);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(TestDoneActivity.this);
                builder.setIcon(R.drawable.exit);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn thoát hay không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                builder.show();
            }
        });

        btnSaveScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(TestDoneActivity.this);
                LayoutInflater inflater = TestDoneActivity.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.allert_dialog_save_score, null);
                builder.setView(view);

                final EditText edtName = (EditText) view.findViewById(R.id.edtName);
                final TextView tv_info = (TextView) view.findViewById(R.id.tv_info);
                TextView tvScore = (TextView) view.findViewById(R.id.tvScore);
                final int numTotal = numTrue * 10;
                tv_info.setText(info);
                tvScore.setText(numTotal + " điểm");

                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = edtName.getText().toString();
                        if (name.equals("")) {
                            Toast.makeText(getApplication(), "Vui lòng nhập tên!", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        } else if (name.length() >= 15) {
                            Toast.makeText(getApplication(), "Tên nhập vào phải nhỏ hơn 15 kí tự!", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        } else {
                            scoreController.insertScore(name, numTotal, info);
                            Toast.makeText(TestDoneActivity.this, "Lưu điểm thành công!", Toast.LENGTH_LONG).show();
                            finish();
                            dialog.dismiss();
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog b = builder.create();
                b.show();
            }
        });

    }

    public void begin() {
        tvFalse = (TextView) findViewById(R.id.tvFalse);
        tvTrue = (TextView) findViewById(R.id.tvTrue);
        tvNotAns = (TextView) findViewById(R.id.tvNotAns);
        tvTotalScore = (TextView) findViewById(R.id.tvTotalPoint);
        btnSaveScore = (Button) findViewById(R.id.btnSaveScore);
        btnExit = (Button) findViewById(R.id.btnExit);
    }

    //PT Check kết quả
    public void checkResult() {
        for (int i = 0; i < arr_QuesBegin.size(); i++) {
            if (arr_QuesBegin.get(i).getTraloi().equals("")) {
                numNoAns++;
            } else if (arr_QuesBegin.get(i).getResult().equals(arr_QuesBegin.get(i).getTraloi())) {
                numTrue++;
            } else numFalse++;
        }
    }

}
