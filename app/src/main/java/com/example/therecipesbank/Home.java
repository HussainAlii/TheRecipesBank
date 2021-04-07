package com.example.therecipesbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        if(intent.getSerializableExtra("isFirstLogin").toString().equals("true")) {
            Snackbar.make(findViewById(R.id.Activity2), "Welcome to The Recipes Bank Chef " + MainActivity.Username,
                    Snackbar.LENGTH_SHORT)
                    .show();
        }
    }
}