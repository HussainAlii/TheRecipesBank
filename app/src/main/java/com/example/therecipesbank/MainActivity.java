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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameInput = findViewById(R.id.etEmail);
        passwordInput = findViewById(R.id.etPassword);
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
                Username=user.get(DbHandler.KEY_Username);
                Password=user.get(DbHandler.KEY_Password);
                Email=user.get(DbHandler.KEY_Email);
            });

            finishAffinity();
            Intent intent = new Intent(MainActivity.this, Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("Username",Username);
            intent.putExtra("Password",Password);
            intent.putExtra("Email",Email);
            startActivity(intent);

        }else{
            Toast.makeText(this, "Wrong Email or Password", Toast.LENGTH_SHORT).show();
        }
    }
}