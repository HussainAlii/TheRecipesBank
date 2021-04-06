package com.example.therecipesbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class recipeView extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);

        button = findViewById(R.id.like);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.getBackground().equals(R.drawable.heart)){
                    button.setBackgroundResource(R.drawable.redheart);
                }
                else if(button.getCompoundDrawables().equals(R.drawable.redheart)){
                    button.setBackgroundResource(R.drawable.heart);
                }
            }
        });
    }


}