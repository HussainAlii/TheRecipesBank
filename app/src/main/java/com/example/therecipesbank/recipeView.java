package com.example.therecipesbank;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
        System.out.println(MainActivity.selectedPost+"00000");
        button = findViewById(R.id.likeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                if(button.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.heart).getConstantState())){
                    button.setBackgroundResource(R.drawable.redheart);
                }
                else if(button.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.redheart).getConstantState())){
                    button.setBackgroundResource(R.drawable.heart);
                }
            }
        });
    }


}