package com.aspirepublicschool.gyanmanjari;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

public class stu_document extends AppCompatActivity {
    Button button,button1,button2,button3,button4,button5,button6;
    Bitmap bitmap;
    String encodedImage,Document,Document1,Document2,Document3,Document4,Document5;
    byte[] bytes;
    String uriString;
    ProgressDialog progressDialog;

    ArrayList<String> DocumentList;

    LinearLayout ll1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_document);
        button = findViewById(R.id.button);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        ll1 = findViewById(R.id.ll1);
        DocumentList = new ArrayList<>();
        progressDialog = new ProgressDialog(this);

        DocumentList.add("");
        DocumentList.add("");
        DocumentList.add("");
        DocumentList.add("");
        DocumentList.add("");
        DocumentList.add("");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(stu_document.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                    //                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Intent intent = new Intent();
                        intent.setType("application/pdf");
                        intent.setAction(Intent.ACTION_GET_CONTENT);

                        startActivityForResult(Intent.createChooser(intent,"select image"),1);

                        button.setBackgroundColor(button.getContext().getResources().getColor(android.R.color.holo_green_dark));
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
                Dexter.withActivity(stu_document.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                    //                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Intent intent = new Intent();
                        intent.setType("application/pdf");
                        intent.setAction(Intent.ACTION_GET_CONTENT);

                        button1.setBackgroundColor(button1.getContext().getResources().getColor(android.R.color.holo_green_dark));
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
                Dexter.withActivity(stu_document.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                    //                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Intent intent = new Intent();
                        intent.setType("application/pdf");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        button2.setBackgroundColor(button2.getContext().getResources().getColor(android.R.color.holo_green_dark));
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
                Dexter.withActivity(stu_document.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                    //                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Intent intent = new Intent();
                        intent.setType("application/pdf");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        button3.setBackgroundColor(button3.getContext().getResources().getColor(android.R.color.holo_green_dark));
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
                Dexter.withActivity(stu_document.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                    //                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Intent intent = new Intent();
                        intent.setType("application/pdf");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        button4.setBackgroundColor(button4.getContext().getResources().getColor(android.R.color.holo_green_dark));
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

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(stu_document.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                    //                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Intent intent = new Intent();
                        intent.setType("application/pdf");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        button5.setBackgroundColor(button5.getContext().getResources().getColor(android.R.color.holo_green_dark));
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



                try {

                    Document = DocumentList.get(0);
                    Document1 = DocumentList.get(1);
                    Document2 = DocumentList.get(2);
                    Document3 = DocumentList.get(3);
                    Document4 = DocumentList.get(4);
                    Document5 = DocumentList.get(5);


                    if (Document.isEmpty()) {

                        Toast.makeText(stu_document.this, "Please Select Your Marksheet", Toast.LENGTH_SHORT).show();
                    } else if (Document1.isEmpty()) {

                        Toast.makeText(stu_document.this, "Please Select Your Image", Toast.LENGTH_SHORT).show();
                    } else if (Document2.isEmpty()) {

                        Toast.makeText(stu_document.this, "Please Select Your Father Image", Toast.LENGTH_SHORT).show();
                    } else if (Document3.isEmpty()) {

                        Toast.makeText(stu_document.this, "Please Select Your Mother Image", Toast.LENGTH_SHORT).show();
                    } else if (Document4.isEmpty()) {

                        Toast.makeText(stu_document.this, "Please Select Your Signature", Toast.LENGTH_SHORT).show();
                    } else if (Document5.isEmpty()) {

                        Toast.makeText(stu_document.this, "Please Select Your PWD Document", Toast.LENGTH_SHORT).show();
                    } else {

                        progressDialog.setMessage("loading");
                        progressDialog.show();

                        StringRequest request = new StringRequest(Request.Method.POST, "https://biochemical-damping.000webhostapp.com/uploadpdf.php",
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        progressDialog.dismiss();

                                        Toast.makeText(stu_document.this, response, Toast.LENGTH_SHORT).show();
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(stu_document.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }

                        ) {
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> Params = new HashMap<>();
                                Params.put("mainID", "");
                                Params.put("marksheet", Document);
                                Params.put("stuimg", Document1);
                                Params.put("fatherimg", Document2);
                                Params.put("motherimg", Document3);
                                Params.put("signimg", Document4);
                                Params.put("pwddoc", Document5);


                                return Params;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(stu_document.this);
                        requestQueue.add(request);
                    }

                }catch (ArrayIndexOutOfBoundsException e){

                    Toast.makeText(stu_document.this, "Enter Every Document", Toast.LENGTH_SHORT).show();


                }


            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode ==1 && resultCode == RESULT_OK && data!= null){
            Uri filepath = data.getData();

            ConvertToString(filepath);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void ConvertToString(Uri uri){
        uriString = uri.toString();
        Log.d("data", "onActivityResult: uri"+uriString);

        try {

            InputStream in = getContentResolver().openInputStream(uri);
            bytes=getBytes(in);
            Log.d("data", "onActivityResult: bytes size="+bytes.length);
            Log.d("data", "onActivityResult: Base64string="+Base64.encodeToString(bytes,Base64.DEFAULT));
            String ansValue = Base64.encodeToString(bytes,Base64.DEFAULT);
            Document = null;
            Document1 = null;
            Document2 = null;
            Document3 = null;
            Document4 = null;
            Document5 = null;

            Document=Base64.encodeToString(bytes,Base64.DEFAULT);
            Document1=Base64.encodeToString(bytes,Base64.DEFAULT);
            Document2=Base64.encodeToString(bytes,Base64.DEFAULT);
            Document3=Base64.encodeToString(bytes,Base64.DEFAULT);
            Document4=Base64.encodeToString(bytes,Base64.DEFAULT);
            Document5=Base64.encodeToString(bytes,Base64.DEFAULT);

//            DocumentList.clear();
            DocumentList.add(Document);
            DocumentList.add(Document1);
            DocumentList.add(Document2);
            DocumentList.add(Document3);
            DocumentList.add(Document4);
            DocumentList.add(Document5);



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


}

