package com.aspirepublicschool.gyanmanjari.AdmissionDetailRegister;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aspirepublicschool.gyanmanjari.R;

public class basic_activity extends Fragment {

  public basic_activity(){
        }
    View view;
    TextView btnbasicSave;
    EditText edSurName, edName, edFatherName, edMobileNo, edAlternateMN;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView btnSave;


    String surname, name, fatherName, mobileNo, alternateMN;
    String gender;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.basic_activity, container, false);

        return  view;
//        edSurName = view.findViewById(R.id.surName);
//        edName = view.findViewById(R.id.name);
//        edFatherName = view.findViewById(R.id.fatherName);
//        edMobileNo = view.findViewById(R.id.mobileNo);
//        edAlternateMN = view.findViewById(R.id.alternateMN);
//
//        radioGroup = view.findViewById(R.id.rdgmedium);
//
//        btnSave = view.findViewById(R.id.btnbasicSave);
//          getData();
//          return view;
//
//
//
//
//    }
//    private void getData() {
//        SharedPreferences sp = getActivity().getSharedPreferences("FILE_NAME", Context.MODE_PRIVATE);
////        SharedPreferences sp = getSharedPreferences("FILE_NAME", MODE_PRIVATE);
//
//        String surname = sp.getString("surname", String.valueOf(-1));
//        String name = sp.getString("name", String.valueOf(-1));
//        String fatherName = sp.getString("fatherName", String.valueOf(-1));
//        String mobileNo = sp.getString("mobileNo", String.valueOf(-1));
//        String gender = sp.getString("gender", String.valueOf(-1));
//
//        if (surname == String.valueOf(-1) ||
//                name == String.valueOf(-1) ||
//                fatherName == String.valueOf(-1) ||
//                mobileNo == String.valueOf(-1) ||
//                gender.isEmpty()){
//
//            waitForResponse();
//
//        }else {
//
//            Intent intent = new Intent(view.getContext(), Edu_detail.class);
//            startActivity(intent);
//            getActivity().finish();
//
////            startActivity(new Intent(getContext(), Edu_detail.class));
////            getActivity().finish();
//            Toast.makeText(getContext(), "else", Toast.LENGTH_SHORT).show();
//        }
//
//    }
//
//    private void waitForResponse() {
//
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "ghdgd", Toast.LENGTH_SHORT).show();
//                setStudentInfo();
//            }
//        });
//
//    }
//
//    private void setStudentInfo() {
//
//        int ID = radioGroup.getCheckedRadioButtonId();
//        radioButton = view.findViewById(ID);
//        gender = radioButton.getText().toString();
//
//        if (edSurName.getText().toString().isEmpty() ||
//                edName.getText().toString().isEmpty() ||
//                edFatherName.getText().toString().isEmpty() ||
//                edMobileNo.getText().toString().isEmpty() ){
//
////            Toast.makeText(this, "Please fill up every detail", Toast.LENGTH_SHORT).show();
//            Toast.makeText(getContext(), "Please fill up every detail", Toast.LENGTH_SHORT).show();
//
//        }else if (gender.isEmpty()){
//            Toast.makeText(getContext(), "Please select gender", Toast.LENGTH_SHORT).show();
//
//
//        }else{
//
//            surname = edSurName.getText().toString();
//            name = edName.getText().toString();
//            fatherName = edFatherName.getText().toString();
//            mobileNo = edMobileNo.getText().toString();
//            alternateMN = edAlternateMN.getText().toString();
//            SharedPreferences sp = getActivity().getSharedPreferences("FILE_NAME", Context.MODE_PRIVATE);
////            SharedPreferences sp = getSharedPreferences("FILE_NAME", MODE_PRIVATE);
//            SharedPreferences.Editor edit = sp.edit();
//            edit.putString("surname", surname);
//            edit.putString("name", name);
//            edit.putString("fatherName", fatherName);
//            edit.putString("mobileNo", mobileNo);
//            edit.putString("alternateMN", alternateMN);
//            edit.putString("gender", gender);
//            edit.apply();
//
//            Intent intent = new Intent(view.getContext(), Edu_detail.class);
//
//            startActivity(intent);
//            getActivity().finish();
//
////
////
//
//
//        }

    }

}

