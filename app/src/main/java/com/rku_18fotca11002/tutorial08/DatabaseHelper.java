package com.rku_18fotca11002.tutorial08;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE = "university";
    public static final String TABLE = "user";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Firstname";
    public static final String COL_3 = "Lastname";
    public static final String COL_4 = "Username";
    public static final String COL_5 = "Password";
    public static final String COL_6 = "Branch";
    public static final String COL_7 = "Gender";
    public static final String COL_8 = "City";
    public static final String COL_9 = "Profile";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS "+ TABLE +"(ID INTEGER "+"PRIMARY KEY AUTOINCREMENT, Firstname text, Lastname text, Username text, Password text, Branch text, Gender text, City text, Profile text)";
        Log.i("sql",sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }

    public boolean insertData(String Firstname, String Lastname, String Username, String Password, String Branch, String Gender, String City, String Profile){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, Firstname);
        contentValues.put(COL_3, Lastname);
        contentValues.put(COL_4, Username);
        contentValues.put(COL_5, Password);
        contentValues.put(COL_6, Branch);
        contentValues.put(COL_7, Gender);
        contentValues.put(COL_8, City);
        contentValues.put(COL_9, Profile);

        long result = db.insert(TABLE,null,contentValues);

        if(result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public Boolean checkData(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE+" WHERE Username = ? and Password = ?", new String[] {username, password});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

}
