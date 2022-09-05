package com.aspirepublicschool.gyanmanjari.AdmissionDetailRegister;

import static android.content.Context.MODE_PRIVATE;

import static com.aspirepublicschool.gyanmanjari.AdmissionDetailRegister.Edu_detail.edMathsMarks;
import static com.aspirepublicschool.gyanmanjari.AdmissionDetailRegister.Edu_detail.edSchoolName;
import static com.aspirepublicschool.gyanmanjari.AdmissionDetailRegister.Edu_detail.edScienceMarks;
import static com.aspirepublicschool.gyanmanjari.AdmissionDetailRegister.Edu_detail.radioGroup1;
import static com.aspirepublicschool.gyanmanjari.AdmissionDetailRegister.Edu_detail.radioGroup2;
import static com.aspirepublicschool.gyanmanjari.AdmissionDetailRegister.basic_activity.edAlternateMN;
import static com.aspirepublicschool.gyanmanjari.AdmissionDetailRegister.basic_activity.edFatherName;
import static com.aspirepublicschool.gyanmanjari.AdmissionDetailRegister.basic_activity.edMobileNo;
import static com.aspirepublicschool.gyanmanjari.AdmissionDetailRegister.basic_activity.edName;
import static com.aspirepublicschool.gyanmanjari.AdmissionDetailRegister.basic_activity.edSurName;
import static com.aspirepublicschool.gyanmanjari.AdmissionDetailRegister.basic_activity.radioGroup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aspirepublicschool.gyanmanjari.MainActivity;
import com.aspirepublicschool.gyanmanjari.R;

import java.util.HashMap;
import java.util.Map;

public class AddDetail extends Fragment {

    TextView upload;
    View view;

    String recidenceAddress, recidenceVillageArea, recidenceCity;
    String saRecidenceAddress, saRecidenceVillageArea, saRecidenceCity;

    public static EditText edRecidenceAddress, edRecidenceVillageArea, edRecidenceCity;
    public static EditText edSaRecidenceAddress, edSaRecidenceVillageArea, edSaRecidenceCity;

    String schoolName, Medium, Group, mathsMarks, scienceMarks, totalMarks;
    RadioButton radioButton1, radioButton2, radioButton3;

    String surname, name, fatherName, mobileNo, alternateMN;
    String gender,id;
//    Button update;
    RadioButton radioButton;

    ProgressDialog progressDialog;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_detail, container, false);


        progressDialog = new ProgressDialog(view.getContext());

        edRecidenceAddress = view.findViewById(R.id.recidenceAddress);
        edRecidenceVillageArea = view.findViewById(R.id.recidenceVillageArea);
        edRecidenceCity = view.findViewById(R.id.recidenceCity);

        edSaRecidenceAddress = view.findViewById(R.id.saRecidenceAddress);
        edSaRecidenceVillageArea = view.findViewById(R.id.saRecidenceVillageArea);
        edSaRecidenceCity = view.findViewById(R.id.saRecidenceCity);


        SharedPreferences sp = this.getActivity().getSharedPreferences("FILE_NAME", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        recidenceAddress = sp.getString("recidenceAddress", String.valueOf(-1));
        recidenceVillageArea = sp.getString("recidenceVillageArea", String.valueOf(-1));
        recidenceCity = sp.getString("recidenceCity", String.valueOf(-1));
        saRecidenceAddress = sp.getString("saRecidenceAddress", String.valueOf(-1));
        saRecidenceVillageArea = sp.getString("saRecidenceVillageArea", String.valueOf(-1));
        saRecidenceCity = sp.getString("saRecidenceCity", String.valueOf(-1));
        id = sp.getString("mainID", String.valueOf(-1));


        setData();


        upload = view.findViewById(R.id.btnAddressSave);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllData();
            }
        });

        return view;
    }

    private void setData() {

       edRecidenceAddress.setText(recidenceAddress);
       edRecidenceVillageArea.setText(recidenceVillageArea);
       edRecidenceCity.setText(recidenceCity);
       edSaRecidenceAddress.setText(saRecidenceAddress);
       edSaRecidenceVillageArea.setText(saRecidenceVillageArea);
       edSaRecidenceCity.setText(saRecidenceCity);



    }

    private void getAllData() {

        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        recidenceAddress = edRecidenceAddress.getText().toString();
        recidenceVillageArea = edRecidenceVillageArea.getText().toString();
        recidenceCity = edRecidenceCity.getText().toString();

        saRecidenceAddress = edSaRecidenceAddress.getText().toString();
        saRecidenceVillageArea = edSaRecidenceVillageArea.getText().toString();
        saRecidenceCity = edSaRecidenceCity.getText().toString();

        surname = edSurName.getText().toString();
        name = edName.getText().toString();
        fatherName = edFatherName.getText().toString();
        mobileNo = edMobileNo.getText().toString();
        alternateMN = edAlternateMN.getText().toString();

        schoolName = edSchoolName.getText().toString();
        mathsMarks = edMathsMarks.getText().toString();
        scienceMarks = edScienceMarks.getText().toString();
        totalMarks = String.valueOf(Integer.parseInt(mathsMarks) + Integer.parseInt(scienceMarks));

//        int id1, id2, id3;
//
//        id1 = radioGroup.getCheckedRadioButtonId();
//        id2 = radioGroup1.getCheckedRadioButtonId();
//        id3 = radioGroup2.getCheckedRadioButtonId();
//
//        if (view.findViewById(id1) != null ||
//                view.findViewById(id2) != null ||
//                        view.findViewById(id3) != null ){
//
//            radioButton1 = view.findViewById(id1);
//            radioButton2 = view.findViewById(id2);
//            radioButton3 = view.findViewById(id3);
//
//            Medium = radioButton1.getText().toString();
//            Group = radioButton2.getText().toString();
//            gender = radioButton.getText().toString();

            uploadData();

//        }
//        else {
//            progressDialog.dismiss();
//            Toast.makeText(getContext(), "select all radio buttons", Toast.LENGTH_SHORT).show();
//        }




//        int ID = radioGroup.getCheckedRadioButtonId();
//        radioButton = view.findViewById(ID);
//        gender = radioButton.getText().toString();
//
//        int ID1 = radioGroup1.getCheckedRadioButtonId();
//        radioButton1 = view.findViewById(ID1);
//        Medium = radioButton1.getText().toString();
//
//        int ID2 = radioGroup2.getCheckedRadioButtonId();
//        radioButton2 = view.findViewById(ID2);
//        Group = radioButton2.getText().toString();







    }

    private void uploadData() {
        SharedPreferences sp = this.getActivity().getSharedPreferences("FILE_NAME", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        final String gender = sp.getString("gender", String.valueOf(-1));
        Medium = sp.getString("Medium", String.valueOf(-1));
        Group = sp.getString("Group", String.valueOf(-1));

//        Toast.makeText(view.getContext(), name, Toast.LENGTH_SHORT).show();

        progressDialog.setMessage("Updating...");
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, "https://biochemical-damping.000webhostapp.com/update.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        editor.putString("updateStatus","updated");
                        editor.apply();
                        startActivity(new Intent(view.getContext(),MainActivity.class));
                        getActivity().finish();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> Params = new HashMap<String, String>();
                Params.put("mainID",id);
                Params.put("surName",surname);
                Params.put("name", name);
                Params.put("fatherName", fatherName);
                Params.put("mobileNo", mobileNo);
                Params.put("alternateMN", alternateMN);
                Params.put("genderTaken", gender);
                Params.put("schoolName", schoolName);
                Params.put("medium", Medium);
                Params.put("groupTaken", Group);
                Params.put("mathsMarks", mathsMarks);
                Params.put("scienceMarks", scienceMarks);
                Params.put("totalMarks", totalMarks);
                Params.put("recidenceAddress", recidenceAddress);
                Params.put("recidenceVillageArea", recidenceVillageArea);
                Params.put("recidenceCity", recidenceCity);
                Params.put("saRecidenceAddress", saRecidenceAddress);
                Params.put("saRecidenceVillageArea", saRecidenceVillageArea);
                Params.put("saRecidenceCity", saRecidenceCity);

                return Params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(request);




    }

}
