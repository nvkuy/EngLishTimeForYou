package com.example.nguyentuan.tracnghiem1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nguyentuan.tracnghiem1.dict.DictActivity;
import com.example.nguyentuan.tracnghiem1.question.Home;
import com.example.nguyentuan.tracnghiem1.verb.LyThuyetActivity;
import com.example.nguyentuan.tracnghiem1.CSDL.DBHelper;
import com.example.nguyentuan.tracnghiem1.score.ScoreFragment;
import com.example.nguyentuan.tracnghiem1.verb_training.bai1;
import com.example.nguyentuan.tracnghiem1.verb_training.bai2;
import com.example.nguyentuan.tracnghiem1.verb_training.bai3;
import com.example.nguyentuan.tracnghiem1.word.WordActivity;

import java.io.IOException;

/**
 * Created by toannq on 1/30/2017.
 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper db = new DBHelper(this);

        try {
            db.createDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }


//
//        try {
//            db.deleteDataBase();
//            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
//        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Mặc định màn hình khi vào app
        Home home = new Home();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, home, home.getTag()).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    private void anim_intent() {
        overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.dict) {
            // Handle the camera action
//            HomeFragment homeFragment = new HomeFragment();
//            FragmentManager manager = getSupportFragmentManager();
//            manager.beginTransaction().replace(R.id.content_main, homeFragment, homeFragment.getTag()).commit();
            Intent i = new Intent(getApplication(), DictActivity.class);
            startActivity(i);
            anim_intent();
        } else if (id == R.id.bai1) {
            bai1 questionShowFragment = new bai1();
            FragmentManager quanli1 = getSupportFragmentManager();
            quanli1.beginTransaction().replace(R.id.content_main, questionShowFragment, questionShowFragment.getTag()).commit();

        } else if (id == R.id.bai2) {
            bai2 questionShowFragment = new bai2();
            FragmentManager quanli2 = getSupportFragmentManager();
            quanli2.beginTransaction().replace(R.id.content_main, questionShowFragment, questionShowFragment.getTag()).commit();

        } else if (id == R.id.bai3) {
            bai3 questionShowFragment = new bai3();
            FragmentManager quanli = getSupportFragmentManager();
            quanli.beginTransaction().replace(R.id.content_main, questionShowFragment, questionShowFragment.getTag()).commit();
        } else if (id == R.id.score) {
            ScoreFragment scoreFragment = new ScoreFragment();
            FragmentManager quanli3 = getSupportFragmentManager();
            quanli3.beginTransaction().replace(R.id.content_main, scoreFragment, scoreFragment.getTag()).commit();
        } else if (id == R.id.lythuyet) {
            Intent i = new Intent(getApplication(), LyThuyetActivity.class);
            startActivity(i);
            anim_intent();
        } else if (id == R.id.word) {
            Intent i = new Intent(getApplication(), WordActivity.class);
            startActivity(i);
            anim_intent();
        } else if (id == R.id.home) {
            Home home = new Home();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main, home, home.getTag()).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
