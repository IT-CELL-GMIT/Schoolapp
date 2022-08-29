package com.aspirepublicschool.gyanmanjari.Admission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aspirepublicschool.gyanmanjari.NewLogin.StudentLoginActivity;
import com.aspirepublicschool.gyanmanjari.R;

public class NewAdmissionLoginActivity extends AppCompatActivity {

    EditText userName, userPassword;
    TextView userSignin;
    String tempUserName = "6355574383";
    String tempPasswodrd = "123";

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String USERNAME_KEY = "username_key";
    public static final String PASSWORD_KEY = "password_key";

    SharedPreferences sharedpreferences;
    String email, password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_admission_login);

        getSupportActionBar().hide();

        userName = findViewById(R.id.userName);
        userPassword = findViewById(R.id.userPassword);
        userSignin = findViewById(R.id.userSignin);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        email = sharedpreferences.getString(USERNAME_KEY, null);
        password = sharedpreferences.getString(PASSWORD_KEY, null);


        userSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String editUserName = userName.getText().toString().trim();
                String editPassword = userPassword.getText().toString().trim();

                if (tempUserName.equals(editUserName) && tempPasswodrd.equals(editPassword)) {

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(USERNAME_KEY, editUserName);
                    editor.putString(PASSWORD_KEY, editPassword);
                    editor.apply();

                    startActivity(new Intent(NewAdmissionLoginActivity.this, AttemptTestActivity.class));
                    finish();
                } else {
                    Toast.makeText(NewAdmissionLoginActivity.this, "Invalidate UserName OR Password", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}