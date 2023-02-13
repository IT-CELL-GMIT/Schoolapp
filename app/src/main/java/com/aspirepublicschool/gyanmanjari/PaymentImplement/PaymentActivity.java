package com.aspirepublicschool.gyanmanjari.PaymentImplement;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aspirepublicschool.gyanmanjari.DocumentUpload.DocumentUploadActivity;
import com.aspirepublicschool.gyanmanjari.MainActivity;
import com.aspirepublicschool.gyanmanjari.PaymentDisplay.PaymentRecieptActivity;
import com.aspirepublicschool.gyanmanjari.R;
import com.razorpay.Checkout;
import com.razorpay.ExternalWalletListener;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity implements PaymentResultWithDataListener,ExternalWalletListener {

    ProgressDialog progressDialog;
    String API_ID = "rzp_test_0RHVnRvOPZwYaO";  //RazourPay API ID
    private AlertDialog.Builder alertDialogBuilder;
    Button btnPay;
    SharedPreferences sp;

    String amountValue = "100";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        btnPay = findViewById(R.id.payFees);

        progressDialog = new ProgressDialog(this);

        alertDialogBuilder = new AlertDialog.Builder(PaymentActivity.this);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("Payment Result");
        alertDialogBuilder.setPositiveButton("Ok", (dialog, which) -> {
            //do nothing
        });



//        btnPay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                progressDialog.setMessage("Please wait...");
//                progressDialog.show();
                startPayment();
//            }
//        });

    }


    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        co.setKeyID(API_ID);

        try {
            JSONObject options = new JSONObject();
            options.put("name", "GMIT ITCELL SANJAY PARMAR");
            options.put("description", "STUDENT REG. FEES");
            options.put("send_sms_hash",true);
            options.put("allow_rotation", true);
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://biochemical-damping.000webhostapp.com/GM%20Logo/gm.jpg");
            options.put("currency", "INR");
            options.put("amount", amountValue);

            JSONObject preFill = new JSONObject();
            preFill.put("email", "sanjayparmar7167@gmail.com");
            preFill.put("contact", "7435954425");

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            finish();
            e.printStackTrace();
        }

    }

//    @Override
//    public void onExternalWalletSelected(String s, PaymentData paymentData) {
//
//       // try{
////            alertDialogBuilder.setMessage("External Wallet Selected:\nPayment Data: "+paymentData.getData());
////            alertDialogBuilder.show();
////        }catch (Exception e){
////            e.printStackTrace();
////        }
//
//    }

//    @Override
//    public void onPaymentSuccess(String s, PaymentData paymentData) {
//
//    }

//    @Override
//    public void onPaymentError(int i, String s, PaymentData paymentData) {
//
//    }

    @Override
    public void onExternalWalletSelected(String s, PaymentData paymentData) {
        try{
            alertDialogBuilder.setMessage("External Wallet Selected:\nPayment Data: "+paymentData.getData());
            alertDialogBuilder.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {

        SharedPreferences sp = getSharedPreferences("FILE_NAME", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();

        try{
//            alertDialogBuilder.setMessage("Payment Successful :\nPayment ID: "+s+"\nPayment Data: "+paymentData.getData());
//            alertDialogBuilder.show();

            progressDialog.setMessage("Transaction is Successful. Just a moment...");
            progressDialog.show();

            Toast.makeText(this, "Payment Successful :\nPayment ID: "+s+"\nPayment Data: "+paymentData.getData(), Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();

                    edit.putString("PaymentStatus", "PaymentDone");
                    edit.putString("PaymentAmount", amountValue);
                    edit.apply();

                    startActivity(new Intent(getApplicationContext(), PaymentRecieptActivity.class));
                    finish();
                }
            }, 2000);

        }catch (Exception e){
            progressDialog.dismiss();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        try{
            alertDialogBuilder.setMessage("Payment Failed:\nPayment Data: "+paymentData.getData());
            alertDialogBuilder.show();
            finish();
        }catch (Exception e){
            e.printStackTrace();
            finish();
        }
    }


}