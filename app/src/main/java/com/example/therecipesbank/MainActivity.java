package com.example.therecipesbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String username = "";
    private static final String password = "";
    EditText usernameInput, passwordInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameInput = findViewById(R.id.etEmail);
        passwordInput = findViewById(R.id.etPassword);
    }

    public void login(View view){
        if(usernameInput.getText().toString().equals(username)  && passwordInput.getText().toString().equals(password)){
            Intent intent = new Intent(MainActivity.this, Home.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
        }
    }
}