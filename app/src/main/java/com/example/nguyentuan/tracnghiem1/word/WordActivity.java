package com.example.nguyentuan.tracnghiem1.word;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.nguyentuan.tracnghiem1.R;

import java.util.ArrayList;

public class WordActivity extends AppCompatActivity {

    GridView gv_unit;
    ArrayList<topic> arr_unit = new ArrayList<topic>();
    TopicAdapter unitAdapter;
    int NumQues = 1;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        getSupportActionBar().setTitle("Học từ tiếng Anh");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gv_unit = (GridView) findViewById(R.id.gv_unit);
        arr_unit.add(new topic("Động vật"));
        arr_unit.add(new topic("Cơ thể con người"));
        arr_unit.add(new topic("Gia đình"));
        arr_unit.add(new topic("Thực vật"));
        arr_unit.add(new topic("Đồ dùng trong nhà"));
        arr_unit.add(new topic("Phương tiện giao thông"));
        arr_unit.add(new topic("Thời tiết"));
        arr_unit.add(new topic("Vấn đề sức khỏe"));
        arr_unit.add(new topic("Ăn uống"));
        arr_unit.add(new topic("Nghề nghiệp"));
        arr_unit.add(new topic("Quốc gia"));
        arr_unit.add(new topic("Môn học"));


        unitAdapter = new TopicAdapter(getApplication(), arr_unit);
        gv_unit.setAdapter(unitAdapter);
        gv_unit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplication(), LearningWord.class);
                intent.putExtra("topic", arr_unit.get(i).getUnit().toString());
                sharedPreferences = getSharedPreferences(arr_unit.get(i).getUnit().toString(), Context.MODE_PRIVATE);
                intent.putExtra("NumQues", sharedPreferences.getInt("NumQues", NumQues));
                startActivity(intent);
                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
            }
        });
    }
}
