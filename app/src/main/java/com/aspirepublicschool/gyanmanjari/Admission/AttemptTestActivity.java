package com.aspirepublicschool.gyanmanjari.Admission;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aspirepublicschool.gyanmanjari.R;
import com.aspirepublicschool.gyanmanjari.Test.ViewTestToday;


public class AttemptTestActivity extends AppCompatActivity {

    TextView btnAttemptTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attempt_test);
        getSupportActionBar().hide();

        //btnAttemptTest = findViewById(R.id.btnAttemptTest);

//        btnAttemptTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(AttemptTestActivity.this, ViewTestToday.class));
//            }
//        });


    }
}