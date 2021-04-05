package com.example.therecipesbank;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;

public class Home extends AppCompatActivity {
    static Button profileButton =null;
    static Button createButton =null;
    static Button favButton =null;
    static Button myChefsButton =null;
    static Button trendsButton =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        profileButton = findViewById(R.id.profileButton);
        createButton = findViewById(R.id.createButton);
        favButton = findViewById(R.id.favButton);
        myChefsButton = findViewById(R.id.myChefsButton);
        trendsButton = findViewById(R.id.trendsButton);
    }
}