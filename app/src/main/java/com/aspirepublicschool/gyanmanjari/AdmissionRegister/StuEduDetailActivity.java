package com.aspirepublicschool.gyanmanjari.AdmissionRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aspirepublicschool.gyanmanjari.R;

public class StuEduDetailActivity extends AppCompatActivity {

    TextView btnEduSave;

    String schoolName, Medium, Group, mathsMarks, scienceMarks, totalMarks, standard, class_id;
    RadioButton radioButton1, radioButton2, radioButtonStd;
    RadioGroup radioGroup1;
    RadioGroup radioGroup2;
    RadioGroup radioGroupStd;

    String surname, name, fatherName, mobileNo, alternateMN;
    String gender;

    String recidenceAddress, recidenceVillageArea, recidenceCity;
    String saRecidenceAddress, saRecidenceVillageArea, saRecidenceCity;

    String url = "https://sanjayparmarandroid.000webhostapp.com/insert.php";

    EditText edSchoolName, edMathsMarks, edScienceMarks;

    TextView btnNext,totalOfMarks;

    int total = 0;

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

    @SuppressLint("MissingInflatedId")
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
        radioGroupStd = findViewById(R.id.rgsTD);

        btnNext = findViewById(R.id.btnEduSave);
        totalOfMarks  = findViewById(R.id.totalOfMarks_stu);

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

        edMathsMarks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (i2 == 0){

                    if (!edMathsMarks.getText().toString().isEmpty()){

                        if (!edScienceMarks.getText().toString().isEmpty()){
                            total = total - Integer.parseInt(mathsMarks);
                            mathsMarks = charSequence.toString();
                            total = total + Integer.parseInt(mathsMarks);
                            totalOfMarks.setText(String.valueOf(Integer.parseInt(scienceMarks) + Integer.parseInt(mathsMarks)));
                        }else {

                            total = Integer.parseInt(charSequence.toString());
                            mathsMarks = charSequence.toString();
                            totalOfMarks.setText(String.valueOf(Integer.parseInt(mathsMarks)));

                        }

                    }else{

                        if (!edScienceMarks.getText().toString().isEmpty()){
                            total = Integer.parseInt(scienceMarks);
                            mathsMarks = "";
                            setTotalMarks(Integer.parseInt(scienceMarks));
                        }else {

                            mathsMarks = "";
                            scienceMarks = "";
                            total = 0;
                            totalOfMarks.setText("0");

                        }
                    }


                }else {


                    if (edScienceMarks.getText().toString().isEmpty()){

                        mathsMarks = charSequence.toString();
                        total = Integer.parseInt(mathsMarks);
                        totalOfMarks.setText(mathsMarks);

                    }else {

                        mathsMarks = charSequence.toString();
                        total = Integer.parseInt(scienceMarks) + Integer.parseInt(mathsMarks);
                        setTotalMarks(total);

                    }


                }



            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edScienceMarks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (i2 == 0){

                    if (!edScienceMarks.getText().toString().isEmpty()){

                        if (!edMathsMarks.getText().toString().isEmpty()){

                            total = total - Integer.parseInt(scienceMarks);
                            scienceMarks = charSequence.toString();
                            total = total + Integer.parseInt(scienceMarks);
                            totalOfMarks.setText(String.valueOf(Integer.parseInt(mathsMarks) + Integer.parseInt(scienceMarks)));

                        }else {

                            total = Integer.parseInt(charSequence.toString());
                            scienceMarks = charSequence.toString();
                            totalOfMarks.setText(String.valueOf(Integer.parseInt(scienceMarks)));

                        }

                    }else{
                        if (!edMathsMarks.getText().toString().isEmpty()){
                            total = Integer.parseInt(mathsMarks);
                            scienceMarks = "";
                            setTotalMarks(Integer.parseInt(mathsMarks));
                        }else {

                            mathsMarks = "";
                            scienceMarks = "";
                            total = 0;
                            totalOfMarks.setText("0");

                        }
                    }

                }else {

                    if (edMathsMarks.getText().toString().isEmpty()){

                        scienceMarks = charSequence.toString();
                        total = Integer.parseInt(scienceMarks);
                        totalOfMarks.setText(scienceMarks);


                    }else {

                        scienceMarks = charSequence.toString();
                        total = Integer.parseInt(mathsMarks) + Integer.parseInt(scienceMarks);
                        setTotalMarks(total);

                    }

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void setTotalMarks(int marks) {

        if (String.valueOf(marks).isEmpty()){

            totalOfMarks.setText("0");

        }else {

            totalOfMarks.setText(String.valueOf(total));
        }

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
        int ID2 = radioGroup2.getCheckedRadioButtonId();
        int IDStd = radioGroupStd.getCheckedRadioButtonId();

        schoolName = edSchoolName.getText().toString();
//        mathsMarks = edMathsMarks.getText().toString();
//        scienceMarks = edScienceMarks.getText().toString();
        totalMarks = String.valueOf(Integer.parseInt(mathsMarks) + Integer.parseInt(scienceMarks));

        if (schoolName.isEmpty() ||
                mathsMarks.equals(String.valueOf(-1)) ||
                scienceMarks.equals(String.valueOf(-1))){

            Toast.makeText(this, "Please fill up every detail first", Toast.LENGTH_SHORT).show();

        }else if (findViewById(ID1) == null || findViewById(ID2) == null || findViewById(IDStd) == null){

            Toast.makeText(this, "Please check all radio buttons", Toast.LENGTH_SHORT).show();

        }else if (Integer.parseInt(mathsMarks) > 80 || Integer.parseInt(scienceMarks) > 80){

            Toast.makeText(this, "Marks out of 80 can't be greater than 80", Toast.LENGTH_SHORT).show();

        }else {

            radioButton1 = findViewById(ID1);
            Medium = radioButton1.getText().toString();

            radioButton2 = findViewById(ID2);
            Group = radioButton2.getText().toString();

            radioButtonStd = findViewById(IDStd);
            standard = radioButtonStd.getText().toString();

            if(standard.equals("11-Science"))
            {
                if(Medium.equals("Gujarati"))
                {
                    if(Group.equals("A Group"))
                    {
                        class_id="CIDN126";
//                            class_id = "SCIDN1";
                    }
                    else {
                        class_id="CIDN127";
//                            class_id = "SCIDN1";
                    }
                }
                else {

                    if(Group.equals("A Group"))
                    {
                        class_id="CIDN124";
//                            class_id = "SCIDN1";

                    }
                    else {
                        class_id="CIDN125";
//                            class_id = "SCIDN1";
                    }

                }
            }
            else
            {
                if(Medium.equals("Gujarati"))
                {
                    if(Group.equals("A Group"))
                    {
                        class_id="CIDN121";
//                            class_id = "SCIDN1";
                    }
                    else {
                        class_id="CIDN120";
//                            class_id = "SCIDN1";
                    }
                }
                else {

                    if(Group.equals("A Group"))
                    {
                        class_id="CIDN123";
//                            class_id = "SCIDN1";
                    }
                    else {
                        class_id="CIDN122";
//                            class_id = "SCIDN1";
                    }

                }

            }


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
        edit.putString("class_id", class_id);
        edit.putString("standard", standard);
        edit.apply();

        startActivity(new Intent(StuEduDetailActivity.this, AddressDetailStuActivity.class));
        finish();

    }

}