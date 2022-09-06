package com.aspirepublicschool.gyanmanjari.Admission;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aspirepublicschool.gyanmanjari.DocumentUpload.DocumentUploadActivity;
import com.aspirepublicschool.gyanmanjari.PayTM.checkSumActivity;
import com.aspirepublicschool.gyanmanjari.PayTM.checksum;
import com.aspirepublicschool.gyanmanjari.PaymentImplement.PaymentActivity;
import com.aspirepublicschool.gyanmanjari.R;


public class AdmissionTestResultActivity extends AppCompatActivity {

    Button btnOnlineFees,btnOfflineFees;
    TextView totalMarks, obtainedMarks;
    int totalM, obtainM;
    String tM, oM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission_test_result);

        getSupportActionBar().hide();

        btnOnlineFees = findViewById(R.id.btnPayOnlineFees);
        totalMarks = findViewById(R.id.totalMarks);
        obtainedMarks = findViewById(R.id.obtainedMarks);

       SharedPreferences sp = getSharedPreferences("MARKSTEST", MODE_PRIVATE);
//

        SharedPreferences sp1 = getSharedPreferences("FILE_NAME", MODE_PRIVATE);
        String paymentStatus = sp1.getString("PaymentStatus", null);

        if (paymentStatus != null){

            startActivity(new Intent(getApplicationContext(), DocumentUploadActivity.class));
            finish();

        }

        tM = sp.getString("TotalMarks", null);
        oM = sp.getString("marksMCQ", null);

        totalM = Integer.parseInt(tM);
        obtainM = Integer.parseInt(oM);

        totalMarks.setText("Total Mark : " + tM);
        obtainedMarks.setText("Obtained Mark : " + oM);

//        btnOnlineFees.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(AdmissionTestResultActivity.this, checkSumActivity.class));
//            }
//        });

       btnOnlineFees.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if (obtainM >= (totalM*0.66)){
                   startActivity(new Intent(AdmissionTestResultActivity.this, PaymentActivity.class));
               }else {
                   Toast.makeText(AdmissionTestResultActivity.this, "Not Enough Marks To Get A Admission", Toast.LENGTH_SHORT).show();
               }

           }
       });

    }
}
