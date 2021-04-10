package com.example.therecipesbank;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class recipeView extends AppCompatActivity {
    Button button,subButton;
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

        subButton = findViewById(R.id.subscribeButton);
        subButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                subButton.setVisibility(View.INVISIBLE);
            }
        });
        TextView title  = findViewById(R.id.viewRTitle);
        TextView desc = findViewById(R.id.viewRDesc);
        title.setText(MainActivity.selectedPost.getTitle());
        desc.setText(MainActivity.selectedPost.getDescription());
    }


}