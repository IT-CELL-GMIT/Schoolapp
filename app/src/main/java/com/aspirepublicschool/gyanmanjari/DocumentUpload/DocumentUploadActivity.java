package com.aspirepublicschool.gyanmanjari.DocumentUpload;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aspirepublicschool.gyanmanjari.AdmissionDetailRegister.update;
import com.aspirepublicschool.gyanmanjari.MainActivity;
import com.aspirepublicschool.gyanmanjari.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DocumentUploadActivity extends AppCompatActivity {
    Button button,button1,button2,button3,button4,button5,button6;
    String Document="doc",Document1="doc1",Document2="doc2",Document3="doc3",Document4="doc4",Document5="doc5";
    String Doc,Doc1,Doc2,Doc3,Doc4,Doc5;
    byte[] bytes;
    String uriString, position;
    ProgressDialog progressDialog;
    TextView skip;

    LinearLayout ll1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_upload);
        button = findViewById(R.id.button);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        ll1 = findViewById(R.id.ll1);
        skip = findViewById(R.id.skipDocument);
        progressDialog = new ProgressDialog(this);

        Intent intent = getIntent();
        String status = intent.getStringExtra("from main");

        SharedPreferences sp = getSharedPreferences("FILE_NAME", MODE_PRIVATE);

        String updateStatus = sp.getString("updateStatus", null);

        Doc = sp.getString("doc", String.valueOf(-1));
        Doc1 = sp.getString("doc1", String.valueOf(-1));
        Doc2 = sp.getString("doc2", String.valueOf(-1));
        Doc3 = sp.getString("doc3", String.valueOf(-1));
        Doc4 = sp.getString("doc4", String.valueOf(-1));
        Doc5 = sp.getString("doc5", String.valueOf(-1));

        if (updateStatus != null && status == null){

            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        }

         if (Doc != String.valueOf(-1) ||
                Doc1 != String.valueOf(-1) ||
                Doc2 != String.valueOf(-1) ||
                Doc3 != String.valueOf(-1) ||
                Doc4 != String.valueOf(-1) ||
                Doc5 != String.valueOf(-1) ){

//            startActivity(new Intent(this, update.class));
//            finish();

        }

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sp = getSharedPreferences("FILE_NAME", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();

                editor.putString("updateStatus", "skipped");
                editor.putString("doc", "remaining");
                editor.putString("doc1", "remaining");
                editor.putString("doc2", "remaining");
                editor.putString("doc3", "remaining");
                editor.putString("doc4", "remaining");
                editor.putString("doc5", "remaining");

                editor.apply();

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(DocumentUploadActivity.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                    //                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        position = "0";
                        Intent intent = new Intent();
                        intent.setType("application/pdf");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,"select image"),1);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(DocumentUploadActivity.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                    //                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        position = "1";
                        Intent intent = new Intent();
                        intent.setType("application/pdf");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,"select image"),1);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(DocumentUploadActivity.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                    //                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        position = "2";
                        Intent intent = new Intent();
                        intent.setType("application/pdf");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,"select image"),1);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(DocumentUploadActivity.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                    //                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        position = "3";
                        Intent intent = new Intent();
                        intent.setType("application/pdf");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,"select image"),1);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(DocumentUploadActivity.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                    //                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        position = "4";
                        Intent intent = new Intent();
                        intent.setType("application/pdf");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,"select image"),1);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

            }
        });

        button5.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(DocumentUploadActivity.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                    //                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        position = "5";
                        Intent intent = new Intent();
                        intent.setType("application/pdf");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,"select image"),1);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

            }
        });

        button6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                    if (Document.equals("doc")) {

                        Toast.makeText(DocumentUploadActivity.this, "Please Select Your Marksheet", Toast.LENGTH_SHORT).show();
                    }
                    else if (Document1.equals("doc1")) {
                        Toast.makeText(DocumentUploadActivity.this, "Please Select Your Image", Toast.LENGTH_SHORT).show();
                    } else if (Document2.equals("doc2")) {

                        Toast.makeText(DocumentUploadActivity.this, "Please Select Your Father Image", Toast.LENGTH_SHORT).show();
                    } else if (Document3.equals("doc3")) {

                        Toast.makeText(DocumentUploadActivity.this, "Please Select Your Mother Image", Toast.LENGTH_SHORT).show();
                    } else if (Document4.equals("doc4")) {

                        Toast.makeText(DocumentUploadActivity.this, "Please Select Your Signature", Toast.LENGTH_SHORT).show();
                    } else if (Document5.equals("doc5")) {

                        Toast.makeText(DocumentUploadActivity.this, "Please Select Your PWD Document", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        progressDialog.setMessage("loading");
                        progressDialog.show();

                        SharedPreferences sp = getSharedPreferences("FILE_NAME", MODE_PRIVATE);
                        final String mainID = sp.getString("mainID", String.valueOf(-1));

                        StringRequest request = new StringRequest(Request.Method.POST, "https://biochemical-damping.000webhostapp.com/uploadpdf.php",
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        progressDialog.dismiss();
                                        Toast.makeText(DocumentUploadActivity.this, response, Toast.LENGTH_SHORT).show();

                                        String rep = response;

                                        if (rep.equalsIgnoreCase("PDF Uploaded")) {

                                            SharedPreferences sp = getSharedPreferences("FILE_NAME", MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sp.edit();

                                            editor.putString("doc", "uploaded");
                                            editor.putString("doc1", "uploaded");
                                            editor.putString("doc2", "uploaded");
                                            editor.putString("doc3", "uploaded");
                                            editor.putString("doc4", "uploaded");
                                            editor.putString("doc5", "uploaded");

                                            editor.apply();

                                            startActivity(new Intent(DocumentUploadActivity.this, update.class));
                                        }else
                                            Toast.makeText(DocumentUploadActivity.this, "some error occured try again", Toast.LENGTH_SHORT).show();

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Toast.makeText(DocumentUploadActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }

                        ) {
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> Params = new HashMap<>();
                                Params.put("mainID", mainID);
                                Params.put("marksheet", Document);
                                Params.put("stuimg", Document1);
                                Params.put("fatherimg", Document2);
                                Params.put("motherimg", Document3);
                                Params.put("signimg", Document4);
                                Params.put("pwddoc", Document5);


                                return Params;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(DocumentUploadActivity.this);
                        requestQueue.add(request);
                    }


            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode ==1 && resultCode == RESULT_OK && data!= null){
            Uri filepath = data.getData();

            if (filepath != null){
                ConvertToString(filepath, position);
            }else
                Toast.makeText(this, "DOCUMENT IS UNSELECTED", Toast.LENGTH_SHORT).show();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void ConvertToString(Uri uri, String position){
        uriString = uri.toString();
        Log.d("data", "onActivityResult: uri"+uriString);

        try {

            InputStream in = getContentResolver().openInputStream(uri);
            bytes=getBytes(in);
            Log.d("data", "onActivityResult: bytes size="+bytes.length);
            Log.d("data", "onActivityResult: Base64string="+Base64.encodeToString(bytes,Base64.DEFAULT));
            String ansValue = Base64.encodeToString(bytes,Base64.DEFAULT);



            if (position.equals("0")){
                Document=Base64.encodeToString(bytes,Base64.DEFAULT);
                button.setBackgroundResource(R.drawable.edittext_shape_blue);
            }else if (position.equals("1")){
                Document1=Base64.encodeToString(bytes,Base64.DEFAULT);
                button1.setBackgroundResource(R.drawable.edittext_shape_blue);
            }else if (position.equals("2")){
                Document2=Base64.encodeToString(bytes,Base64.DEFAULT);
                button2.setBackgroundResource(R.drawable.edittext_shape_blue);
            }else if (position.equals("3")){
                Document3=Base64.encodeToString(bytes,Base64.DEFAULT);
                button3.setBackgroundResource(R.drawable.edittext_shape_blue);
            }else if (position.equals("4")){
                Document4=Base64.encodeToString(bytes,Base64.DEFAULT);
                button4.setBackgroundResource(R.drawable.edittext_shape_blue);
            }else if (position.equals("5")){
                Document5=Base64.encodeToString(bytes,Base64.DEFAULT);
                button5.setBackgroundResource(R.drawable.edittext_shape_blue);
            }else {
                Toast.makeText(this, "something went wrong please restart the app", Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Log.d("error", "onActivityResult: " + e.getMessage());
        }
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        byte[] buffer;
        buffer = new byte[1024];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    // random comment by yogesh bhai


}

