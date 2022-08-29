package com.aspirepublicschool.gyanmanjari.AdmissionRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aspirepublicschool.gyanmanjari.R;

public class StuEduDetailActivity extends AppCompatActivity {

    TextView btnEduSave;

    String schoolName, Medium, Group, mathsMarks, scienceMarks, totalMarks;
    RadioButton radioButton1, radioButton2;
    RadioGroup radioGroup1;
    RadioGroup radioGroup2;

    String surname, name, fatherName, mobileNo, alternateMN;
    String gender;

    String recidenceAddress, recidenceVillageArea, recidenceCity;
    String saRecidenceAddress, saRecidenceVillageArea, saRecidenceCity;

    String url = "https://sanjayparmarandroid.000webhostapp.com/insert.php";

    EditText edSchoolName, edMathsMarks, edScienceMarks;

    TextView btnNext,totalOfMarks;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_stu_edu_detail);
//
//        btnEduSave = findViewById(R.id.btnEduSave);
//        btnEduSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),AddressDetailStuActivity.class));
//            }
//        });
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_edu_detail);

        getSupportActionBar().hide();

        edSchoolName = findViewById(R.id.schoolName);
        edMathsMarks = findViewById(R.id.mathsMarks);
        edScienceMarks = findViewById(R.id.scienceMarks);

        radioGroup1 = findViewById(R.id.rgMedium);
        radioGroup2 = findViewById(R.id.rgGroup);

        btnNext = findViewById(R.id.btnEduSave);
        totalOfMarks  = findViewById(R.id.totalOfMarks);

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

        if (schoolName == String.valueOf(-1) ||
                Medium == String.valueOf(-1) ||
                Group == String.valueOf(-1) ||
                mathsMarks == String.valueOf(-1) ||
                scienceMarks == String.valueOf(-1)){

            waitForRespnse();

        }else {

            startActivity(new Intent(StuEduDetailActivity.this, AddressDetailStuActivity.class));
            finish();
        }

        totalOfMarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalOfMarks.setText(totalMarks);
            }
        });

    }

    private void waitForRespnse() {

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

    }

    private void getData() {

        int ID1 = radioGroup1.getCheckedRadioButtonId();
        radioButton1 = findViewById(ID1);
        Medium = radioButton1.getText().toString();

        int ID2 = radioGroup2.getCheckedRadioButtonId();
        radioButton2 = findViewById(ID2);
        Group = radioButton2.getText().toString();


        schoolName = edSchoolName.getText().toString();
        mathsMarks = edMathsMarks.getText().toString();
        scienceMarks = edScienceMarks.getText().toString();
        totalMarks = String.valueOf(Integer.parseInt(mathsMarks) + Integer.parseInt(scienceMarks));

        if (schoolName.isEmpty() ||
                mathsMarks.isEmpty() ||
                scienceMarks.isEmpty() ||
                Medium.isEmpty() ||
                Group.isEmpty()){

            Toast.makeText(this, "Please fill up every detail first", Toast.LENGTH_SHORT).show();

        }else {

            setData();

        }

    }

    private void setData() {

        SharedPreferences sp = getSharedPreferences("FILE_NAME", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();

        edit.putString("schoolName", schoolName);
        edit.putString("Medium", Medium);
        edit.putString("Group", Group);
        edit.putString("mathsMarks", mathsMarks);
        edit.putString("scienceMarks", scienceMarks);
        edit.putString("totalMarks", totalMarks);

        edit.apply();

        startActivity(new Intent(StuEduDetailActivity.this, AddressDetailStuActivity.class));
        finish();

    }

}