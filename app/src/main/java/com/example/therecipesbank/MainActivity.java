package com.example.therecipesbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText usernameInput, passwordInput;
    public static DbHandler dbHandler;
    static String Username = "";
    static String Email = "";
    static String Password ="";
    static int Followers =0;
    static int UserId=-1;
    static chefs selectedChef =null;
    static post selectedPost =null;
    static int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameInput = findViewById(R.id.Email);
        passwordInput = findViewById(R.id.Password);
        dbHandler = new DbHandler(MainActivity.this);

        findViewById(R.id.registerView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void loginPage(View view){
        ArrayList<HashMap<String, String>> dataList = dbHandler.login(passwordInput.getText().toString(),usernameInput.getText().toString());

        if(dataList.size()==1){
            dataList.forEach((user) -> {
                UserId =Integer.parseInt(user.get(DbHandler.KEY_ID));
                Username=user.get(DbHandler.KEY_Username);
                Password=user.get(DbHandler.KEY_Password);
                Email=user.get(DbHandler.KEY_Email);
            });
            finishAffinity();
            Intent intent = new Intent(MainActivity.this, Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("isFirstLogin",true);
            intent.putExtra("isRecCreated",false);
            startActivity(intent);

        }else{
            Toast.makeText(this, "Wrong Credentials!", Toast.LENGTH_SHORT).show();
        }
    }

}