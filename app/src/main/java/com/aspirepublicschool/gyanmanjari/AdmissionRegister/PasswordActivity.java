package com.aspirepublicschool.gyanmanjari.AdmissionRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aspirepublicschool.gyanmanjari.R;

public class PasswordActivity extends AppCompatActivity {

    EditText edPassword, edConfirmPassword;
    TextView nextBtn;
    String password, confirmPassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        edPassword = findViewById(R.id.edPassword);
        edConfirmPassword = findViewById(R.id.edConfirmPassword);
        nextBtn = findViewById(R.id.btnNext);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password = edPassword.getText().toString().trim();
                confirmPassword = edConfirmPassword.getText().toString().trim();

                if (password.equals(confirmPassword)){

                    if (password.length() < 6){
                        Toast.makeText(PasswordActivity.this, "please select strong password must be off 7 characters", Toast.LENGTH_SHORT).show();
                    }else {
                        signUp();
                    }

                }else {
                    Toast.makeText(PasswordActivity.this, "passwords are not matching", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void signUp() {
    }
}