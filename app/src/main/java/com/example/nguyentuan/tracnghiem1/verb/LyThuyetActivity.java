package com.example.nguyentuan.tracnghiem1.verb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nguyentuan.tracnghiem1.R;

import java.util.ArrayList;

public class LyThuyetActivity extends AppCompatActivity {

    ArrayList<Sverb> arr_verb = new ArrayList<Sverb>();
    VerbAdapter verbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ly_thuyet);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("12 thì tiếng Anh");

        ListView lv_verb = (ListView) findViewById(R.id.lv_verb);
        addVerbName();

        verbAdapter = new VerbAdapter(getApplication(), arr_verb);
        lv_verb.setAdapter(verbAdapter);

        lv_verb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplication(), VerbActivity.class);
                intent.putExtra("TenThi", arr_verb.get(i).getName().toString());
                startActivity(intent);
                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
            }
        });
    }

    public void addVerbName() {
        arr_verb.add(new Sverb("hiện tại đơn"));
        arr_verb.add(new Sverb("hiện tại tiếp diễn"));
        arr_verb.add(new Sverb("hiện tại hoàn thành"));
        arr_verb.add(new Sverb("hiện tại hoàn thành tiếp diễn"));
        arr_verb.add(new Sverb("quá khứ đơn"));
        arr_verb.add(new Sverb("quá khứ tiếp diễn"));
        arr_verb.add(new Sverb("quá khứ hoàn thành"));
        arr_verb.add(new Sverb("quá khứ hoàn thành tiếp diễn"));
        arr_verb.add(new Sverb("tương lai đơn"));
        arr_verb.add(new Sverb("tương lai tiếp diễn"));
        arr_verb.add(new Sverb("tương lai hoàn thành"));
        arr_verb.add(new Sverb("tương lai hoàn thành tiếp diễn"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}