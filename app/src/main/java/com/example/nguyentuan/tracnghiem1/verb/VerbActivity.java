package com.example.nguyentuan.tracnghiem1.verb;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.nguyentuan.tracnghiem1.R;

import java.util.ArrayList;

public class VerbActivity extends AppCompatActivity {

    VerbController verbController;
    ArrayList<Verb> arr_verb;
    String TenThi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        verbController = new VerbController(getApplicationContext());
        TenThi = intent.getStringExtra("TenThi");
        getSupportActionBar().setTitle(TenThi);
        arr_verb = verbController.get_verb(TenThi);

        TextView tvKhangDinh = (TextView) findViewById(R.id.KhangDinh);
        TextView tvPhuDinh = (TextView) findViewById(R.id.PhuDinh);
        TextView tvNghiVan = (TextView) findViewById(R.id.NghiVan);
        TextView tvNhanBiet = (TextView) findViewById(R.id.NhanBiet);
        TextView tvUse = (TextView) findViewById(R.id.Use);

        tvKhangDinh.setText(arr_verb.get(0).getKhangDinh());
        tvPhuDinh.setText(arr_verb.get(0).getPhuDinh());
        tvNghiVan.setText(arr_verb.get(0).getNghiVan());
        tvNhanBiet.setText(arr_verb.get(0).getNhanBiet());
        tvUse.setText(arr_verb.get(0).getCachDung());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
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
