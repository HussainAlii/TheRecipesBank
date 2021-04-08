package com.example.therecipesbank;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {
     static final int DB_VERSION = 5;
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
    private static final String Likes         = "likes";
    private static final String USER_ID     = "user_id";

    private static final String FAV_TABLE   = "favs";
    private static final String F_ENTRY_ID  = "id";
    private static final String F_USER_ID   = "user_id";
    private static final String REC_ID      = "rec_id";




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
                + Likes +" INTEGER,"
                + USER_ID +" INTEGER,"+
                " FOREIGN KEY ("+USER_ID+") REFERENCES "+ USER_TABLE+"("+KEY_ID+")"+
                ")";


        String createFavTableQuery = "CREATE TABLE IF NOT EXISTS " + FAV_TABLE + "(" +
                  F_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + F_USER_ID +" INTEGER,"+
                  REC_ID +" INTEGER,"+
                " FOREIGN KEY ("+F_USER_ID+") REFERENCES "+ USER_TABLE+"("+KEY_ID+")"+
                ", FOREIGN KEY ("+REC_ID+") REFERENCES "+ POST_TABLE+"("+POST_ID+")"+
                ")";



        db.execSQL(CREATE_TABLE);
        db.execSQL(createPostTableQuery);
        db.execSQL(createFavTableQuery);

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
        cValues.put(TITLE, title);
        cValues.put(DESC, desc);
        cValues.put(IMG, img);
        cValues.put(Likes, 0);
        cValues.put(USER_ID, userId);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(POST_TABLE,null, cValues);

//        String PostInsertQuery = "INSERT INTO "+POST_TABLE+
//                "("+TITLE+ "," + DESC + ","+ IMG+","+ USER_ID + ")"+
//                " VALUES (" + "\""+title+ "\"" + "," + "\""+desc+ "\""+","+"\""+img +"\"" + ",1);";
//
//        db.execSQL(PostInsertQuery);
    }

    public ArrayList<HashMap<String, String>> login(String password, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> dataList = new ArrayList<>();

        String query = "SELECT * FROM "+USER_TABLE+" where "+KEY_Password+"="+"\""+password+"\" and "+ KEY_Username+"="+"\""+username+"\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount()==0){
            query = "SELECT * FROM "+USER_TABLE+" where "+KEY_Password+"="+"\""+password+"\" and "+ KEY_Email+"="+"\""+username+"\"";
            cursor = db.rawQuery(query, null);
        }
        while (cursor.moveToNext()){
            HashMap<String, String> dataHash = new HashMap<>();
            dataHash.put(KEY_ID, cursor.getString((cursor.getColumnIndex(KEY_ID))));
            dataHash.put(KEY_Username, cursor.getString((cursor.getColumnIndex(KEY_Username))));
            dataHash.put(KEY_Password, cursor.getString((cursor.getColumnIndex(KEY_Password))));
            dataHash.put(KEY_Email, cursor.getString((cursor.getColumnIndex(KEY_Email))));
            dataList.add(dataHash);
        }
        return dataList;
    }

    public boolean isAccountExist(String email,String username){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+USER_TABLE+" where "+KEY_Email+"="+"\""+email+"\" or "+ KEY_Username+"="+"\""+username+"\"";
        Cursor cursor = db.rawQuery(query, null);
        return  cursor.getCount()!=0;
    }

//        public ArrayList<HashMap<String, String>> getProfileInfo(int userId) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ArrayList<HashMap<String, String>> dataList = new ArrayList<>();
//
//        String query = "SELECT * FROM "+USER_TABLE+" where "+KEY_ID+" = "+userId;
//        Cursor cursor = db.rawQuery(query, null);
//        while (cursor.moveToNext()){
//            HashMap<String, String> dataHash = new HashMap<>();
//            dataHash.put(KEY_Username, cursor.getString((cursor.getColumnIndex(KEY_Username))));
//            dataHash.put(KEY_Password, cursor.getString((cursor.getColumnIndex(KEY_Password))));
//            dataHash.put(KEY_Email, cursor.getString((cursor.getColumnIndex(KEY_Email))));
//            dataList.add(dataHash);
//        }
//        return dataList;
//    }

    public void updateUserProfile(String userName, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL(  "update "+USER_TABLE+" set "+KEY_Username+" = \""+userName+", "+KEY_Email+" = \""+email+", "+KEY_Password+" = \""+password+" where "+KEY_Username+" = \""+MainActivity.Username );
        //db.execSQL(  "update Chefs set username = "+userName+" , email = "+email+" , password = "+password+" where username =  "+ MainActivity.Username);
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_Username, userName);
        contentValues.put(KEY_Email, email);
        contentValues.put(KEY_Password, password);
        db.update(USER_TABLE, contentValues, "id=?", new String[]{String.valueOf(MainActivity.UserId)});

    }

    public boolean isAccountExist(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+USER_TABLE+" where "+ KEY_Username+"="+"\""+username+"\"";
        Cursor cursor = db.rawQuery(query, null);
        return  cursor.getCount()!=0;
    }


    public ArrayList<post> getLatestPosts() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<post> postList = new ArrayList<>();

        String query = "SELECT * FROM "+POST_TABLE+" left join "+USER_TABLE+" where posts.user_id= Chefs.id order by "+POST_ID+" desc ";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){

            postList.add(new post(cursor.getString((cursor.getColumnIndex(POST_ID))),
                    cursor.getString((cursor.getColumnIndex(TITLE))),
                    cursor.getString((cursor.getColumnIndex(DESC))),
                    cursor.getString((cursor.getColumnIndex(IMG))),
                    cursor.getInt((cursor.getColumnIndex(USER_ID))),
                    cursor.getString((cursor.getColumnIndex(KEY_Username))),
                    cursor.getInt((cursor.getColumnIndex(Likes)))
            ));
           // dataHash.put(DESC, cursor.getString((cursor.getColumnIndex(DESC))));
           // dataHash.put(IMG, cursor.getString((cursor.getColumnIndex(IMG))));


        }
        return postList;
    }

    public ArrayList<post> getFavoriteList(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<post> postList = new ArrayList<>();
        //SELECT  distinct posts.title , posts.description , posts.img, Chefs.username  from posts, favs, Chefs where favs.user_id = 2 and favs.rec_id = posts.post_id and Chefs.id = posts.user_id yay
        String query = "SELECT distinct favs.user_id, posts.likes, posts.title, posts.post_id, posts.user_id, posts.description, posts.img, Chefs.username FROM posts, favs, Chefs WHERE favs.user_id = "+userId+" and favs.rec_id = posts.post_id and Chefs.id = posts.user_id";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            // ||| SELECT  username , email, posts.title, posts.description, posts.img  from Chefs , favs, posts where  favs.user_id= 1 and favs.rec_id = posts.user_id and Chefs.id = posts.user_id
                //SELECT  username , email, posts.title, posts.description, posts.img  from Chefs , favs, posts where    Chefs.id = posts.user_id  this shows the whole favorite list
            //SELECT  distinct posts.title , posts.description , posts.img  from posts, favs where favs.user_id = 1  and favs.rec_id = posts.post_id  <--GUCCI

            postList.add(new post(cursor.getString((cursor.getColumnIndex(POST_ID))),
                    cursor.getString((cursor.getColumnIndex(TITLE))),
                    cursor.getString((cursor.getColumnIndex(DESC))),
                    cursor.getString((cursor.getColumnIndex(IMG))),
                    cursor.getInt((cursor.getColumnIndex(USER_ID))),
                    cursor.getString((cursor.getColumnIndex(KEY_Username))),
                    cursor.getInt((cursor.getColumnIndex(Likes)))
            ));

        }
        return postList;
    }


    public ArrayList<post> getLatestChefs(int username) {
        return null;
    }
}
