package com.example.therecipesbank;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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

        if(MainActivity.dbHandler.isSubscribedTo(MainActivity.UserId,MainActivity.selectedPost.getUser_id()))
            subButton.setVisibility(View.INVISIBLE);

        if(MainActivity.dbHandler.isLiked(MainActivity.UserId,Integer.parseInt(MainActivity.selectedPost.getPost_id())));
            button.setBackgroundResource(R.drawable.redheart);

        button = findViewById(R.id.likeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                if(button.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.heart).getConstantState())){
                    button.setBackgroundResource(R.drawable.redheart);
                    MainActivity.dbHandler.addToFavs(Integer.parseInt(MainActivity.selectedPost.getPost_id()),MainActivity.UserId);
                }
                else if(button.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.redheart).getConstantState())){
                    MainActivity.dbHandler.unlike(MainActivity.UserId,MainActivity.selectedPost.getUser_id());
                    button.setBackgroundResource(R.drawable.heart);
                }
            }
        });

        subButton = findViewById(R.id.subscribeButton);
        subButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                MainActivity.dbHandler.subscribe(MainActivity.UserId,MainActivity.selectedPost.getUser_id());
                subButton.setVisibility(View.INVISIBLE);
            }
        });
        TextView title  = findViewById(R.id.viewRTitle);
        TextView desc = findViewById(R.id.viewRDesc);
        title.setText(MainActivity.selectedPost.getTitle());
        desc.setText(MainActivity.selectedPost.getDescription());
    }


}