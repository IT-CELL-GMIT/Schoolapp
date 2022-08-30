package com.aspirepublicschool.gyanmanjari.AdmissionDetailRegister;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
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

public class Edu_detail extends Fragment {

    View view;
    String schoolName, Medium, Group, mathsMarks, scienceMarks, totalMarks;
    public static EditText edSchoolName, edMathsMarks, edScienceMarks;
    RadioButton radioButton1, radioButton2;
    RadioButton radioButton3, radioButton4, radioButton5;
    public static RadioGroup radioGroup1;
    public static RadioGroup radioGroup2;
    TextView tv;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.edudetail, container, false);

        edSchoolName = view.findViewById(R.id.schoolName);
        edMathsMarks = view.findViewById(R.id.mathsMarks);
        edScienceMarks = view.findViewById(R.id.scienceMarks);

        radioGroup1 = view.findViewById(R.id.rgMedium);
        radioGroup1.check(R.id.rbGujrati);

        radioGroup2 = view.findViewById(R.id.rgGroup);
        radioGroup2.check(R.id.rbA);

        tv = view.findViewById(R.id.totalOfMarks);

        SharedPreferences sp = this.getActivity().getSharedPreferences("FILE_NAME", MODE_PRIVATE);

        schoolName = sp.getString("schoolName", String.valueOf(-1));
        Medium = sp.getString("Medium", String.valueOf(-1));
        Group = sp.getString("Group", String.valueOf(-1));
        mathsMarks = sp.getString("mathsMarks", String.valueOf(-1));
        scienceMarks = sp.getString("scienceMarks", String.valueOf(-1));
        totalMarks = sp.getString("totalMarks", String.valueOf(-1));

        setData();

        return view;
    }

    private void setData() {

        edSchoolName.setText(schoolName);
        edMathsMarks.setText(mathsMarks);
        edScienceMarks.setText(scienceMarks);
        tv.setText(totalMarks);

    }
}