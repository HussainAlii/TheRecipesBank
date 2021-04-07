package com.example.therecipesbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    EditText usernameInput, passwordInput, EmailInput, repeatpasswordInput;
    DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameInput = findViewById(R.id.UsernameRegister);
        passwordInput = findViewById(R.id.PasswordRegister);
        repeatpasswordInput = findViewById(R.id.repeatPasswordRegister);
        EmailInput = findViewById(R.id.EmailRegister);
        dbHandler = MainActivity.dbHandler;
    }

    public void Register(View view) {
        if (repeatpasswordInput.getText().toString().equals(passwordInput.getText().toString())) {
            boolean isAccountExist = dbHandler.isAccountExist(EmailInput.getText().toString(), usernameInput.getText().toString());
            if (isAccountExist == false) {
                dbHandler.insertUserData(usernameInput.getText().toString(), passwordInput.getText().toString(), EmailInput.getText().toString());
                finishAffinity();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } else {
                Toast.makeText(this, "The Account Is Already Exist!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Passwords does not match!", Toast.LENGTH_SHORT).show();
        }
    }
}