package com.aspirepublicschool.gyanmanjari.AdmissionRegister;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aspirepublicschool.gyanmanjari.Admission.AttemptTestActivity;
import com.aspirepublicschool.gyanmanjari.Common;
import com.aspirepublicschool.gyanmanjari.Login;
import com.aspirepublicschool.gyanmanjari.Payment.PayTMActivity;
import com.aspirepublicschool.gyanmanjari.PaymentImplement.PaymentActivity;
import com.aspirepublicschool.gyanmanjari.R;
import com.aspirepublicschool.gyanmanjari.Register.RegisterActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddressDetailStuActivity extends AppCompatActivity {

    EditText edRecidenceAddress, edRecidenceVillageArea, edRecidenceCity;
    EditText edSaRecidenceAddress, edSaRecidenceVillageArea, edSaRecidenceCity;
    TextView btnNext;

    String recidenceAddress, recidenceVillageArea, recidenceCity;
    String saRecidenceAddress, saRecidenceVillageArea, saRecidenceCity;

    String schoolName, Medium, Group, mathsMarks, scienceMarks, totalMarks, standard, class_id;
    RadioButton radioButton1, radioButton2;

    String surname, name, fatherName, mobileNo, alternateMN;
    String gender,id;

    String url = "https://biochemical-damping.000webhostapp.com/insert.php", urlId = "https://biochemical-damping.000webhostapp.com/idfetch.php";

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_detail_stu);

        progressDialog = new ProgressDialog(this);

        edRecidenceAddress = findViewById(R.id.recidenceAddress);
        edRecidenceVillageArea = findViewById(R.id.recidenceVillageArea);
        edRecidenceCity = findViewById(R.id.recidenceCity);

        edSaRecidenceAddress = findViewById(R.id.saRecidenceAddress);
        edSaRecidenceVillageArea = findViewById(R.id.saRecidenceVillageArea);
        edSaRecidenceCity = findViewById(R.id.saRecidenceCity);

        btnNext = findViewById(R.id.btnAddressSave);

        SharedPreferences sp = getSharedPreferences("FILE_NAME", MODE_PRIVATE);

        schoolName = sp.getString("schoolName", String.valueOf(-1));
        Medium = sp.getString("Medium", String.valueOf(-1));
        Group = sp.getString("Group", String.valueOf(-1));
        mathsMarks = sp.getString("mathsMarks", String.valueOf(-1));
        scienceMarks = sp.getString("scienceMarks", String.valueOf(-1));
        totalMarks = sp.getString("totalMarks", String.valueOf(-1));
        standard = sp.getString("standard", String.valueOf(-1));
        class_id = sp.getString("class_id", String.valueOf(-1));

        surname = sp.getString("surname", String.valueOf(-1));
        name = sp.getString("name", String.valueOf(-1));
        fatherName = sp.getString("fatherName", String.valueOf(-1));
        mobileNo = sp.getString("mobileNo", String.valueOf(-1));
        alternateMN = sp.getString("alternateMN", String.valueOf(-1));
        gender = sp.getString("gender", String.valueOf(-1));

        recidenceAddress = sp.getString("recidenceAddress", String.valueOf(-1));
        recidenceVillageArea = sp.getString("recidenceVillageArea", String.valueOf(-1));
        recidenceCity = sp.getString("recidenceCity", String.valueOf(-1));
        saRecidenceAddress = sp.getString("saRecidenceAddress", String.valueOf(-1));
        saRecidenceVillageArea = sp.getString("saRecidenceVillageArea", String.valueOf(-1));
        saRecidenceCity = sp.getString("saRecidenceCity", String.valueOf(-1));



        if (recidenceAddress == String.valueOf(-1) ||
                recidenceVillageArea == String.valueOf(-1) ||
                recidenceCity == String.valueOf(-1) ||
                saRecidenceAddress == String.valueOf(-1) ||
                saRecidenceVillageArea == String.valueOf(-1) ||
                saRecidenceCity == String.valueOf(-1)) {

            waitforResponse();

        } else {

            if (surname == String.valueOf(-1) ||
                    name == String.valueOf(-1) ||
                    fatherName == String.valueOf(-1) ||
                    mobileNo == String.valueOf(-1) ||
                    gender == String.valueOf(-1) ||
                    recidenceAddress == String.valueOf(-1) ||
                    recidenceVillageArea == String.valueOf(-1) ||
                    recidenceCity == String.valueOf(-1) ||
                    saRecidenceAddress == String.valueOf(-1) ||
                    saRecidenceVillageArea == String.valueOf(-1) ||
                    saRecidenceCity == String.valueOf(-1)) {

                startActivity(new Intent(AddressDetailStuActivity.this, BasicStuInfoActivity.class));
                finish();
                Toast.makeText(this, "something went wrong try to refill every detail", Toast.LENGTH_SHORT).show();

            } else {

                startActivity(new Intent(AddressDetailStuActivity.this, SchoolSelelctActivity.class));
                finish();

            }

        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();

            }
        });

    }

    private void uploadData() {

        String insertStudentData = Common.getAdmissionURL() + "insertStudentData.php";
        StringRequest request = new StringRequest(Request.Method.POST, insertStudentData,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(AddressDetailStuActivity.this, response, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

                if (response.equalsIgnoreCase("data inserted") || response.contains("data inserted")){
                    progressDialog.setMessage("Just a little moment...");
                    progressDialog.show();
                    fetchId();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(AddressDetailStuActivity.this, "something went wrong\n" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("surName", surname);
                params.put("name", name);
                params.put("fatherName", fatherName);
                params.put("mobileNo", mobileNo);
                params.put("alternateMN", alternateMN);
                params.put("genderTaken", gender);

                params.put("schoolName", schoolName);
                params.put("medium", Medium);
                params.put("groupTaken", Group);
                params.put("mathsMarks", mathsMarks);
                params.put("scienceMarks", scienceMarks);
                params.put("totalMarks", totalMarks);

                params.put("recidenceAddress", recidenceAddress);
                params.put("recidenceVillageArea", recidenceVillageArea);
                params.put("recidenceCity", recidenceCity);
                params.put("saRecidenceAddress", saRecidenceAddress);
                params.put("saRecidenceVillageArea", saRecidenceVillageArea);
                params.put("saRecidenceCity", saRecidenceCity);

                params.put("class_Id", class_id);
                params.put("standard", standard);

                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(AddressDetailStuActivity.this);
        requestQueue.add(request);

    }

    private void waitforResponse() {

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

    }

    private void getData() {

        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        SharedPreferences sp = getSharedPreferences("FILE_NAME", MODE_PRIVATE);

        String surname = sp.getString("surname", String.valueOf(-1));
        String name = sp.getString("name", String.valueOf(-1));
        String fatherName = sp.getString("fatherName", String.valueOf(-1));
        String mobileNo = sp.getString("mobileNo", String.valueOf(-1));
        String gender = sp.getString("gender", String.valueOf(-1));

        if (surname == String.valueOf(-1) ||
                name == String.valueOf(-1) ||
                fatherName == String.valueOf(-1) ||
                mobileNo == String.valueOf(-1) ||
                gender == String.valueOf(-1)) {

            Toast.makeText(this, "Please fill up previous detail", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();

        } else {

            readyData();

        }

    }

    private void readyData() {

        recidenceAddress = edRecidenceAddress.getText().toString();
        recidenceVillageArea = edRecidenceVillageArea.getText().toString();
        recidenceCity = edRecidenceCity.getText().toString();

        saRecidenceAddress = edSaRecidenceAddress.getText().toString();
        saRecidenceVillageArea = edSaRecidenceVillageArea.getText().toString();
        saRecidenceCity = edSaRecidenceCity.getText().toString();

        if (recidenceAddress.isEmpty() ||
                recidenceVillageArea.isEmpty() ||
                recidenceCity.isEmpty() ||
                saRecidenceAddress.isEmpty() ||
                saRecidenceVillageArea.isEmpty() ||
                saRecidenceCity.isEmpty()) {

            progressDialog.dismiss();
            Toast.makeText(this, "Please every detail first", Toast.LENGTH_SHORT).show();

        } else {

            setData();

        }

    }

    private void setData() {

        SharedPreferences sp = getSharedPreferences("FILE_NAME", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("recidenceAddress", recidenceAddress);
        edit.putString("recidenceVillageArea", recidenceVillageArea);
        edit.putString("recidenceCity", recidenceCity);
        edit.putString("saRecidenceAddress", saRecidenceAddress);
        edit.putString("saRecidenceVillageArea", saRecidenceVillageArea);
        edit.putString("saRecidenceCity", saRecidenceCity);
        edit.apply();

        uploadData();

    }

    private void fetchId() {

        String fetchID = Common.getAdmissionURL() + "idfetch.php";

        SharedPreferences sp = getSharedPreferences("FILE_NAME", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();

        surname = sp.getString("surname", String.valueOf(-1));
        name = sp.getString("name", String.valueOf(-1));
        fatherName = sp.getString("fatherName", String.valueOf(-1));
        mobileNo = sp.getString("mobileNo", String.valueOf(-1));



        StringRequest request = new StringRequest(Request.Method.POST, fetchID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();
                try {

                    progressDialog.dismiss();
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    if (success.equals("1")) {

                        SharedPreferences sp = getSharedPreferences("FILE_NAME", MODE_PRIVATE);
                        SharedPreferences.Editor edit = sp.edit();

                        JSONObject object = jsonArray.getJSONObject(0);

                        id = object.getString("id");
                        edit.putString("mainID", id);
                        edit.apply();

                        progressDialog.setMessage("Finishing...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();

                        Toast.makeText(AddressDetailStuActivity.this, "data inserted successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), SchoolSelelctActivity.class));
                        finish();

                    }


                } catch (JSONException e) {

                    progressDialog.dismiss();
                    Toast.makeText(AddressDetailStuActivity.this, e.getMessage() , Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AddressDetailStuActivity.this, "something went wrong\n" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }) {

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("surName", surname);
                params.put("name", name);
                params.put("fatherName", fatherName);
                params.put("mobileNo", mobileNo);


                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(AddressDetailStuActivity.this);
        requestQueue.add(request);

    }

//    private void postClassData() {
//        String registration = Common.GetWebServiceURL()+"register_student.php";
//        RequestQueue requestQueue = Volley.newRequestQueue(AddressDetailStuActivity.this);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, registration, new Response.Listener<String>() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(AddressDetailStuActivity.this, "kdkdkddieiid" + response, Toast.LENGTH_SHORT).show();
//                try {
//                    JSONObject object = new JSONObject(response);
//                    String message=object.getString("message");
//                    if(message.equals("success")) {
//                        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AddressDetailStuActivity.this);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putInt("login",1);
//                        // Snackbar.make(relregister, "Registration Successful.Username and password  will be provided by SMS after approval of admin.", Snackbar.LENGTH_LONG).show();
//                        //Toast.makeText(ctx, "Registration Successful.Username and password  will be provided by SMS after approval of admin.",Toast.LENGTH_LONG ).show();
//                        final Toast toast = Toast.makeText(getApplicationContext(), "Registration Successful.Username and password  will be provided by SMS after approval of admin.", Toast.LENGTH_SHORT);
//                        toast.show();
//
//                        Handler handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                toast.cancel();
//                            }
//                        }, 10000);
//
//
////                        Intent intent=new Intent(ctx,Login.class);
////                        startActivity(intent);
////                        finish();
//
////                        Intent intent=new Intent(ctx, PayTMActivity.class);
////                        startActivity(intent);
////                        finish();
//
//                        progressDialog.dismiss();
//                        startActivity(new Intent(AddressDetailStuActivity.this, AttemptTestActivity.class));
//                        Toast.makeText(AddressDetailStuActivity.this, "data inserted successfully", Toast.LENGTH_SHORT).show();
//                        finish();
//
//                    }
//                    else if(message.equals("Data Already Exist"))
//                    {
//                        final Toast toast = Toast.makeText(getApplicationContext(), "Data Already Exist", Toast.LENGTH_SHORT);
//                        toast.show();
//
//                        Handler handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                toast.cancel();
//                            }
//                        }, 10000);
//                        progressDialog.dismiss();
//                        Intent intent=new Intent(getApplicationContext(), AttemptTestActivity.class);
//                        startActivity(intent);
//                        finish();
//
//                    }
//                    else
//                    {
//                        progressDialog.dismiss();
//                        final Toast toast = Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT);
//                        toast.show();
//
//                        Handler handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                toast.cancel();
//                            }
//                        }, 10000);
//
//
//                    }
//
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    progressDialog.dismiss();
//                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressDialog.dismiss();
//                error.printStackTrace();
//
//                Toast.makeText(getApplicationContext(),R.string.no_connection_toast, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> data = new HashMap<>();
//
//
//                data.put("cid", class_id);
//                data.put("st_sname",name);
//                data.put("st_fname",surname);
//                data.put("mobile_no",mobileNo);
//                data.put("address",recidenceAddress);
//                data.put("district",recidenceCity);
//                data.put("f_name",fatherName);
//                data.put("email","temp@SanjayParmar.king");
//
//
//                data.put("med",med);
//                data.put("std","12-Science");
//                data.put("board","GSEB");
//                data.put("group",groups);
//                data.put("p_scname",schoolName);
//                Log.d("###", data.toString());
//                return data;
//            }
//        };
//        int socketTimeout = 30000;
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        stringRequest.setRetryPolicy(policy);
//        requestQueue.add(stringRequest);
//    }


}