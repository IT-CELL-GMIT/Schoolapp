package com.aspirepublicschool.gyanmanjari.AdmissionRegister;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
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
import com.aspirepublicschool.gyanmanjari.R;

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

    String schoolName, Medium, Group, mathsMarks, scienceMarks, totalMarks;
    RadioButton radioButton1, radioButton2;

    String surname, name, fatherName, mobileNo, alternateMN;
    String gender,id;

    String url = "https://biochemical-damping.000webhostapp.com/insert.php", urlId = "https://biochemical-damping.000webhostapp.com/idfetch.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_detail_stu);

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

//                startActivity(new Intent(AddressDetailStuActivity.this, AttemptTestActivity.class));
//                finish();

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

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                fetchId();

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

        SharedPreferences sp = getSharedPreferences("FILE_NAME", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();

        surname = sp.getString("surname", String.valueOf(-1));
        name = sp.getString("name", String.valueOf(-1));
        fatherName = sp.getString("fatherName", String.valueOf(-1));
        mobileNo = sp.getString("mobileNo", String.valueOf(-1));



        StringRequest request = new StringRequest(Request.Method.POST, urlId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    if (success.equals("1")) {

                        SharedPreferences sp = getSharedPreferences("FILE_NAME", MODE_PRIVATE);
                        SharedPreferences.Editor edit = sp.edit();

                        JSONObject object = jsonArray.getJSONObject(0);

                        id = object.getString("id");
                        edit.putString("mainID", id);

                        startActivity(new Intent(AddressDetailStuActivity.this, AttemptTestActivity.class));
                        Toast.makeText(AddressDetailStuActivity.this, "data inserted successfully", Toast.LENGTH_SHORT).show();
                        finish();


                    }


                } catch (JSONException e) {

                    Toast.makeText(AddressDetailStuActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

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
}