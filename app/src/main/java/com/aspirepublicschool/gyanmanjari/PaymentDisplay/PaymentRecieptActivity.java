package com.aspirepublicschool.gyanmanjari.PaymentDisplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aspirepublicschool.gyanmanjari.DocumentUpload.DocumentUploadActivity;
import com.aspirepublicschool.gyanmanjari.R;
import com.gkemon.XMLtoPDF.PdfGenerator;
import com.gkemon.XMLtoPDF.PdfGeneratorListener;

public class PaymentRecieptActivity extends AppCompatActivity {

    private Context context;

    TextView tvAmountValue, tvFullName, tvNextPage;

    String paymentAmount;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String surname, fullName, fatherName;

    Button downloadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_reciept);
        context = PaymentRecieptActivity.this;
        tvAmountValue = findViewById(R.id.amountValue);
        tvFullName = findViewById(R.id.tv_fullName);
        tvNextPage = findViewById(R.id.nextPageBtn);
        downloadBtn = findViewById(R.id.downloadBtn);

        sharedPreferences = getSharedPreferences("FILE_NAME", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        surname = sharedPreferences.getString("surname", "none");
        fullName = sharedPreferences.getString("name", "none");
        fatherName = sharedPreferences.getString("fatherName", "none");
        paymentAmount = sharedPreferences.getString("PaymentAmount", "none");

        paymentAmount = String.valueOf(Integer.parseInt(paymentAmount) / 100);

        tvFullName.setText(surname + " " + fullName + " " + fatherName);

        tvAmountValue.setText(paymentAmount);

        tvNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, DocumentUploadActivity.class));
            }
        });


        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PdfGenerator.getBuilder()
                        .setContext(PaymentRecieptActivity.this)
                        .fromViewIDSource()
                        .fromViewID(PaymentRecieptActivity.this, R.id.pdfLayout)
                        .setFileName("Gyan Manjary Payment Receipt")
                        .build(new PdfGeneratorListener() {
                            @Override
                            public void onStartPDFGeneration() {
                                Toast.makeText(context, "starting generating pdf file", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFinishPDFGeneration() {
                                Toast.makeText(context, "pdf generated", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });


    }
}