package com.aspirepublicschool.gyanmanjari.AdmissionDetailRegister;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aspirepublicschool.gyanmanjari.MainActivity;
import com.aspirepublicschool.gyanmanjari.R;

public class basic_activity extends Fragment {

    static View view;
   public static EditText edSurName, edName, edFatherName, edMobileNo, edAlternateMN;
    public static RadioGroup radioGroup1;
    public static RadioButton radioButton1, radioButton2;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.basic_activity, container, false);

        edSurName = view.findViewById(R.id.surName);
        edName = view.findViewById(R.id.name);
        edFatherName = view.findViewById(R.id.fatherName);
        edMobileNo = view.findViewById(R.id.mobileNo);
        edAlternateMN = view.findViewById(R.id.alternateMN);

        radioGroup1 = view.findViewById(R.id.rdgmediumBA);

//        radioButton1 = new RadioButton(view.getContext());
//        radioButton2 = new RadioButton(view.getContext());
//
//
//        radioButton1.setText("Male");
//        radioButton2.setText("Female");
//
//
//        radioGroup.addView(radioButton1);
//        radioGroup.addView(radioButton2);
//        radioGroup.check(radioButton1.getId());

        SharedPreferences sp = this.getActivity().getSharedPreferences("FILE_NAME", MODE_PRIVATE);

        String surname = sp.getString("surname", String.valueOf(-1));
        String name = sp.getString("name", String.valueOf(-1));
        String fatherName = sp.getString("fatherName", String.valueOf(-1));
        String mobileNo = sp.getString("mobileNo", String.valueOf(-1));
        String gender = sp.getString("gender", String.valueOf(-1));
        String alternate = sp.getString("alternateMN", String.valueOf(-1));

        setData(surname, name, fatherName, mobileNo, gender, alternate);

        return view;

    }

    private void setData(String surname, String name, String fatherName, String mobileNo, String gender, String alternate) {

        edSurName.setText(surname);
        edName.setText(name);
        edFatherName.setText(fatherName);
        edMobileNo.setText(mobileNo);
        edAlternateMN.setText(alternate);

    }

    public static String getRadio1(){

        int ID = radioGroup1.getCheckedRadioButtonId();
        if (view.findViewById(ID) == null){
            return null;
        }else {
            radioButton1 = view.findViewById(ID);
            return radioButton1.getText().toString();
        }

    }

}

