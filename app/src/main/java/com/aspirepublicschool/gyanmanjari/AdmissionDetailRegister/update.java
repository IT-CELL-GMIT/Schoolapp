package com.aspirepublicschool.gyanmanjari.AdmissionDetailRegister;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.aspirepublicschool.gyanmanjari.MainActivity;
import com.aspirepublicschool.gyanmanjari.R;
import com.google.android.material.tabs.TabLayout;

public class update extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_detail);
        getSupportActionBar().hide();

//        toolbar = (Toolbar) findViewById(R.id.etoolbar);
//        toolbar.setTitle("GMIT");

        tabLayout = findViewById(R.id.etablayout);

        tabLayout.addTab(tabLayout.newTab().setText("BasicActivity"));
        tabLayout.addTab(tabLayout.newTab().setText("EduDetail"));
        tabLayout.addTab(tabLayout.newTab().setText("addDetail"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setVisibility(View.INVISIBLE);
        viewPager = findViewById(R.id.pager);
        pageAdapter adapter = new pageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        tabLayout.setOnTabSelectedListener(this);




    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.java:
//                Toast.makeText(this, "moving to update", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(update.this, basic_activity.class));
                break;
            case R.id.php:
//                Toast.makeText(this, "login", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(update.this, Edu_detail.class));
                break;
            case R.id.css:
                startActivity(new Intent(update.this, AddDetail.class));

            default:
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
