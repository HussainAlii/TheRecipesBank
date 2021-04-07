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
     static final String USER_TABLE = "Chefs";
     static final String KEY_ID = "id";
     static final String KEY_Username = "username";
     static final String KEY_Email = "email";
     static final String KEY_Password = "password";

    private static final String POST_TABLE  = "posts";
    private static final String TITLE       = "title";
    private static final String POST_ID     = "post_id";
    private static final String DESC        = "description";
    private static final String IMG         = "img";
    private static final String USER_ID     = "user_id";


    public DbHandler(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + USER_TABLE + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                KEY_Username + " TEXT,"+
                KEY_Email + " TEXT,"+
                KEY_Password + " TEXT" + ")";
                db.execSQL(CREATE_TABLE);


        String createPostTableQuery = "CREATE TABLE IF NOT EXISTS " + POST_TABLE + "(" +
                POST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TITLE + " TEXT,"
                + DESC + " TEXT,"
                + IMG +" TEXT,"
                + USER_ID +" INTEGER,"+
                " FOREIGN KEY ("+USER_ID+") REFERENCES "+ USER_TABLE+"("+KEY_ID+")"+
                ")";



        db.execSQL(CREATE_TABLE);
        db.execSQL(createPostTableQuery);

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

    
    public void insertIntoPosts(String title, String desc, String img, int userId){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(USER_ID, userId);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(POST_TABLE,null, cValues);

//        String PostInsertQuery = "INSERT INTO "+POST_TABLE+
//                "("+TITLE+ "," + DESC + ","+ IMG+","+ USER_ID + ")"+
//                " VALUES (" + "\""+title+ "\"" + "," + "\""+desc+ "\""+","+"\""+img +"\"" + ",1);";
//
//        db.execSQL(PostInsertQuery);
    }

    public ArrayList<HashMap<String, String>> login(String password, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> dataList = new ArrayList<>();

        String query = "SELECT * FROM "+USER_TABLE+" where "+KEY_Password+"="+"\""+password+"\" and "+ KEY_Email+"="+"\""+email+"\"";
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

    public ArrayList<HashMap<String, String>> getPostInfo() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> dataList = new ArrayList<>();

        String query = "SELECT * FROM "+POST_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            HashMap<String, String> dataHash = new HashMap<>();
            dataHash.put(TITLE, cursor.getString((cursor.getColumnIndex(TITLE))));
            dataHash.put(DESC, cursor.getString((cursor.getColumnIndex(DESC))));
            dataHash.put(IMG, cursor.getString((cursor.getColumnIndex(IMG))));
            dataHash.put(USER_ID, cursor.getString((cursor.getColumnIndex(USER_ID))));
            dataList.add(dataHash);
        }

        return dataList;
    }

}
