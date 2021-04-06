package com.example.therecipesbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    EditText usernameInput, passwordInput,EmailInput;
   DbHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameInput = findViewById(R.id.UsernameRegister);
        passwordInput = findViewById(R.id.PasswordRegister);
        EmailInput = findViewById(R.id.EmailRegister);
        dbHandler = MainActivity.dbHandler;
    }

    public void Register(View view){
        ArrayList<HashMap<String, String>> dataList = dbHandler.login(passwordInput.getText().toString(),usernameInput.getText().toString());

        if(dataList.size()==0){
            dbHandler.insertUserData(usernameInput.getText().toString(),passwordInput.getText().toString(),EmailInput.getText().toString());
            finishAffinity();
            Intent intent = new Intent(RegisterActivity.this, Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("Username",usernameInput.getText().toString());
            intent.putExtra("Password",passwordInput.getText().toString());
            intent.putExtra("Email",EmailInput.getText().toString());
            startActivity(intent);

        }else{
            Toast.makeText(this, "The Account Is Already Exist!", Toast.LENGTH_SHORT).show();
        }

    }
}