package com.example.therecipesbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {
     static final int DB_VERSION = 1;
     static final String DB_NAME = "theRecipesBank";
     static final String USER_TABLE = "User";
     static final String KEY_ID = "id";
     static final String KEY_Username = "username";
     static final String KEY_Email = "email";
     static final String KEY_Password = "password";

    public DbHandler(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + USER_TABLE + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                KEY_Username + " TEXT,"+
                KEY_Email + " TEXT,"+
                KEY_Password + " TEXT" + ")";
                db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        // Create tables again
        onCreate(db);
    }

    public void insertUserData(String username, String password, String email){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_Username, username);
        cValues.put(KEY_Password, password);
        cValues.put(KEY_Email, email);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(USER_TABLE,null, cValues);
    }

    public ArrayList<HashMap<String, String>> login(String password, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> dataList = new ArrayList<>();

        String query = "SELECT * FROM "+USER_TABLE+" where "+KEY_Password+"="+password+" and "+ KEY_Email+"="+email;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            HashMap<String, String> dataHash = new HashMap<>();
            dataHash.put(KEY_Username, cursor.getString((cursor.getColumnIndex(KEY_Username))));
            dataHash.put(KEY_Password, cursor.getString((cursor.getColumnIndex(KEY_Password))));
            dataHash.put(KEY_Email, cursor.getString((cursor.getColumnIndex(KEY_Email))));
            dataList.add(dataHash);
        }
        return dataList;
    }

}
