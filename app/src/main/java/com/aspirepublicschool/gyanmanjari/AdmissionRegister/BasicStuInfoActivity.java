package com.aspirepublicschool.gyanmanjari.AdmissionRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aspirepublicschool.gyanmanjari.R;

import java.util.Objects;

public class BasicStuInfoActivity extends AppCompatActivity {

    EditText edSurName, edName, edFatherName, edMobileNo, edAlternateMN;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView btnSave;

    String surname, name, fatherName, mobileNo, alternateMN;
    String gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_stu_info);
        Objects.requireNonNull(getSupportActionBar()).hide();

        edSurName = findViewById(R.id.surName);
        edName = findViewById(R.id.name);
        edFatherName = findViewById(R.id.fatherName);
        edMobileNo = findViewById(R.id.mobileNo);
        edAlternateMN = findViewById(R.id.alternateMN);

        radioGroup = findViewById(R.id.rdgmedium);

        btnSave = findViewById(R.id.btnbasicSave);

        getData();

    }

    private void getData() {

        SharedPreferences sp = getSharedPreferences("FILE_NAME", MODE_PRIVATE);

        String surname = sp.getString("surname", String.valueOf(-1));
        String name = sp.getString("name", String.valueOf(-1));
        String fatherName = sp.getString("fatherName", String.valueOf(-1));
        String mobileNo = sp.getString("mobileNo", String.valueOf(-1));
        String gender = sp.getString("gender", String.valueOf(-1));

        if (surname.equals(String.valueOf(-1)) ||
                name.equals(String.valueOf(-1)) ||
                fatherName.equals(String.valueOf(-1)) ||
                mobileNo.equals(String.valueOf(-1)) ||
                gender.equals(String.valueOf(-1))){

            waitForResponse();

        }else {

            startActivity(new Intent(BasicStuInfoActivity.this, StuEduDetailActivity.class));
            finish();
        }

    }

    private void waitForResponse() {

        btnSave.setOnClickListener(view -> setStudentInfo());

    }

    private void setStudentInfo() {

        int ID = radioGroup.getCheckedRadioButtonId();

        if (edSurName.getText().toString().isEmpty() ||
                edName.getText().toString().isEmpty() ||
                edFatherName.getText().toString().isEmpty() ||
                edMobileNo.getText().toString().length()<8 ){

            Toast.makeText(this, "Please fill up every detail correctly", Toast.LENGTH_SHORT).show();

        }
        else if (findViewById(ID) == null){

            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();

        }
        else{


            radioButton = findViewById(ID);
            gender = radioButton.getText().toString();

            surname = edSurName.getText().toString();
            name = edName.getText().toString();
            fatherName = edFatherName.getText().toString();
            mobileNo = edMobileNo.getText().toString();
            alternateMN = edAlternateMN.getText().toString();

            SharedPreferences sp = getSharedPreferences("FILE_NAME", MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("surname", surname);
            edit.putString("name", name);
            edit.putString("fatherName", fatherName);
            edit.putString("mobileNo", mobileNo);
            edit.putString("alternateMN", alternateMN);
            edit.putString("gender", gender);
            edit.apply();

            startActivity(new Intent(BasicStuInfoActivity.this, StuEduDetailActivity.class));
            finish();

        }

    }

}