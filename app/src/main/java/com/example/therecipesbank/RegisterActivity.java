package com.example.therecipesbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void Register(View view){
        if(true){
            startActivity(new Intent(RegisterActivity.this, Home.class));

        }else{
            Toast.makeText(this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
        }
    }
}