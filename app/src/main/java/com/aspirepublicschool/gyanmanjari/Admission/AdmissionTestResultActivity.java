package com.aspirepublicschool.gyanmanjari.Admission;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.aspirepublicschool.gyanmanjari.PayTM.checkSumActivity;
import com.aspirepublicschool.gyanmanjari.PayTM.checksum;
import com.aspirepublicschool.gyanmanjari.R;


public class AdmissionTestResultActivity extends AppCompatActivity {

    Button btnOnlineFees,btnOfflineFees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission_test_result);

        getSupportActionBar().hide();

        btnOnlineFees = findViewById(R.id.btnPayOnlineFees);
        btnOnlineFees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdmissionTestResultActivity.this, checkSumActivity.class));
            }
        });
    }
}