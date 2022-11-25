package com.aspirepublicschool.gyanmanjari.AdmissionRegister;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aspirepublicschool.gyanmanjari.Admission.AttemptTestActivity;
import com.aspirepublicschool.gyanmanjari.Common;
import com.aspirepublicschool.gyanmanjari.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SchoolSelelctActivity extends AppCompatActivity {

    EditText edSchoolUsername, edSchoolPassword;
    String schoolUsername, schoolPassword;
    TextView btnConfirm;

    String schoolName, schoolId, schoolCity, schoolState;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    ProgressDialog progressDialog;

    String standard, mainId, class_id;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_selelct);

        edSchoolUsername = findViewById(R.id.edSchoolUsername);
        edSchoolPassword = findViewById(R.id.edSchoolPassword);
        btnConfirm = findViewById(R.id.btnConfirm);

        progressDialog = new ProgressDialog(SchoolSelelctActivity.this);
        progressDialog.setCancelable(false);

        sp = getSharedPreferences("FILE_NAME", MODE_PRIVATE);
        editor = sp.edit();

        String check = sp.getString("schoolId", null);
        standard = sp.getString("standard", null);
        mainId = sp.getString("mainID", null);
        class_id = sp.getString("class_id", null);

        if (check != null){
            startActivity(new Intent(this, AttemptTestActivity.class));
            finish();
        }

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                schoolUsername = edSchoolUsername.getText().toString().trim();
                schoolPassword = edSchoolPassword.getText().toString().trim();

                if (schoolUsername.isEmpty()){
                    Toast.makeText(SchoolSelelctActivity.this, "Please enter school username", Toast.LENGTH_SHORT).show();
                }else if (schoolPassword.isEmpty()){
                    Toast.makeText(SchoolSelelctActivity.this, "Please enter school password", Toast.LENGTH_SHORT).show();
                }else {
                    checkData();
                }

            }
        });

    }

    private void checkData() {

        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        String checkDataUrl = Common.getAdmissionURL() + "selectSchool.php";
        StringRequest request = new StringRequest(Request.Method.POST, checkDataUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("not matched") || response.contains("not matched")){
                            progressDialog.dismiss();
                            Toast.makeText(SchoolSelelctActivity.this, "please enter valid id password", Toast.LENGTH_SHORT).show();
                        }else {

                            try {

                                progressDialog.dismiss();

                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                
                                if (success.equals("1")){

                                    JSONObject object = jsonArray.getJSONObject(0);

                                    schoolName = object.getString("sc_name");
                                    schoolId = object.getString("sc_id");
                                    schoolCity = object.getString("sc_city");
                                    schoolState = object.getString("sc_state");

                                    updateData();

                                }else {
                                    Toast.makeText(SchoolSelelctActivity.this, "unable to succeed", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {

                                progressDialog.dismiss();
                                Toast.makeText(SchoolSelelctActivity.this, "please enter valid id pass", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(SchoolSelelctActivity.this, "something went wrong try again later", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("schoolUsername", schoolUsername);
                params.put("schoolPassword", schoolPassword);

                return params;

            }
        };

        RequestQueue queue = Volley.newRequestQueue(SchoolSelelctActivity.this);
        queue.add(request);

    }

    private void updateData() {

        progressDialog.setMessage("Little Moment...");
        progressDialog.show();

        String updateUrl = Common.getAdmissionURL() + "updateStudentIdClass.php";

        StringRequest request = new StringRequest(Request.Method.POST, updateUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equalsIgnoreCase("Data Updated") || response.contains("Data Updated")){

                            progressDialog.dismiss();
                            editor.putString("schoolId", schoolId);
                            editor.putString("schoolName", schoolName);
                            editor.putString("schoolCity", schoolCity);
                            editor.putString("schoolState", schoolState);
                            editor.putString("schoolUsername", schoolUsername);
                            editor.apply();

                            startActivity(new Intent(getApplicationContext(), AttemptTestActivity.class));
                            finish();
                        }else {
                            progressDialog.dismiss();
                            Toast.makeText(SchoolSelelctActivity.this, "unable to succed", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(SchoolSelelctActivity.this, "something went wrong try again later", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id", mainId);
                params.put("standard", standard);
                params.put("class_id", class_id);

                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);

    }
}