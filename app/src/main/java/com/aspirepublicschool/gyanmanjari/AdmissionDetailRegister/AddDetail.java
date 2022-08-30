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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aspirepublicschool.gyanmanjari.MainActivity;
import com.aspirepublicschool.gyanmanjari.R;

public class AddDetail extends Fragment {

    TextView upload;
    View view;

    String recidenceAddress, recidenceVillageArea, recidenceCity;
    String saRecidenceAddress, saRecidenceVillageArea, saRecidenceCity;

    public static EditText edRecidenceAddress, edRecidenceVillageArea, edRecidenceCity;
    public static EditText edSaRecidenceAddress, edSaRecidenceVillageArea, edSaRecidenceCity;

    String schoolName, Medium, Group, mathsMarks, scienceMarks, totalMarks;
    RadioButton radioButton1, radioButton2;

    String surname, name, fatherName, mobileNo, alternateMN;
    String gender,id;

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
        recidenceAddress = sp.getString("recidenceAddress", String.valueOf(-1));
        recidenceVillageArea = sp.getString("recidenceVillageArea", String.valueOf(-1));
        recidenceCity = sp.getString("recidenceCity", String.valueOf(-1));
        saRecidenceAddress = sp.getString("saRecidenceAddress", String.valueOf(-1));
        saRecidenceVillageArea = sp.getString("saRecidenceVillageArea", String.valueOf(-1));
        saRecidenceCity = sp.getString("saRecidenceCity", String.valueOf(-1));


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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                progressDialog.dismiss();
                startActivity(new Intent(view.getContext(), MainActivity.class));

            }
        }, 2000);

    }

}
