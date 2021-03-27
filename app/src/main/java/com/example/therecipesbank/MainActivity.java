package com.example.therecipesbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String username = "admin";
    private static final String password = "123";
    EditText usernameInput, passwordInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameInput = findViewById(R.id.username);
        passwordInput = findViewById(R.id.passwordInput);
    }

    public void login(View view){
        if(usernameInput.getText().toString().equals("admin")  && passwordInput.getText().toString().equals("123")){
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
        }
    }
}