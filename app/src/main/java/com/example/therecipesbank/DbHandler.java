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
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "map";
    private static final String TABLE = "location";
    private static final String KEY_ID = "id";
    private static final String KEY_Note = "note";
    private static final String KEY_LAT = "lat";
    private static final String KEY_LNG = "lng";

    private static final String POST_TABLE  = "posts";
    private static final String TITLE       = "title";
    private static final String POST_ID     = "post_id";
    private static final String DESC        = "desc";
    private static final String IMG         = "img";
    private static final String USER_ID     = "user_id";


    public DbHandler(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE + "(" +
                KEY_Note + " TEXT,"+
                KEY_LAT + " TEXT," + KEY_LNG + " TEXT" + ")";
                db.execSQL(CREATE_TABLE);


        String createPostTableQuery = "CREATE TABLE " + POST_TABLE + "(" +
                POST_ID + " INTEGER PRIMARY KEY, auto increment ,"
                + TITLE + "TEXT,"
                + DESC + "TEXT,"
                + IMG +"TEXT,"
                +USER_ID +" TEXT,"+
                "FOREIGN KEY ("+USER_ID+") REFERENCES "+ USER_TABLE+"("+USER_ID+")"+
                ")";

        db.execSQL(CREATE_TABLE);
        db.execSQL(createPostTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        // Create tables again
        onCreate(db);
    }

    public void insertData(String note, String lat, String lng){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_Note, note);
        cValues.put(KEY_LAT, lat);
        cValues.put(KEY_LNG, lng);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE,null, cValues);
    }

    public void insertIntoPosts(String title, String desc, String img, int userid){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(TITLE, title);
        cValues.put(DESC, desc);
        cValues.put(IMG, img);
        cValues.put(USER_ID, userid);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(POST_TABLE,null, cValues);
    }

    public ArrayList<HashMap<String, String>> GetData() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> dataList = new ArrayList<>();

        String query = "SELECT * FROM "+TABLE;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            HashMap<String, String> dataHash = new HashMap<>();
            dataHash.put(KEY_Note, cursor.getString((cursor.getColumnIndex(KEY_Note))));
            dataHash.put(KEY_LAT, cursor.getString((cursor.getColumnIndex(KEY_LAT))));
            dataHash.put(KEY_LNG, cursor.getString((cursor.getColumnIndex(KEY_LNG))));
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
