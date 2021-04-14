package com.example.therecipesbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import java.io.FileNotFoundException;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        final NavController navController = Navigation.findNavController(Home.this,
                R.id.nav_host_fragment);
//        if (intent.getSerializableExtra("isFirstLogin").toString().equals("true")) {
//            Snackbar.make(findViewById(R.id.Activity2), "Welcome to The Recipes Bank Chef " + MainActivity.Username,
//                    Snackbar.LENGTH_SHORT)
//                    .show();
//        }

        if (intent.getSerializableExtra("isRecCreated").toString().equals("true")) {
            intent.putExtra("isRecCreated",false);
            navController.navigate(R.id.action_popular_to_createRes);
        }
    }

}