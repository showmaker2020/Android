package com.example.ui_project2024.DB_Manager;

import static android.content.ContentValues.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Mydatabase_Login_register extends SQLiteOpenHelper {
    private Context context;
    private static final String DB_NAME = "SignLog.db";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "user_table";
    private static final String COLUMN_NAME_USER = "column_name_user";
    private static final String COLUMN_PHONE_NUMBER = "column_phone_number";
    private static final String COLUMN_EMAIL = "column_email";
    private static final String COLUMN_PASSWORD = "column_password";

    public Mydatabase_Login_register(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }
    public Mydatabase_Login_register(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q = "create table " + TABLE_NAME + "(" + COLUMN_EMAIL + " text primary key, " +
                COLUMN_NAME_USER + " text, " + COLUMN_PHONE_NUMBER + " text, " +
                COLUMN_PASSWORD + " text)"
                ;
        db.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists " + TABLE_NAME);
    }

    public Boolean insertdata_user(String email, String name, String phone, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_NAME_USER, name);
        cv.put(COLUMN_PHONE_NUMBER, phone);
        cv.put(COLUMN_PASSWORD, pass);
        long res = db.insert(TABLE_NAME, null, cv);
        if (res == -1){
            Log.d("aaa", "insertdata_user: Khong thanh cong");
            return false;
        }
        else {
            Log.d("aaa", "insertdata_user: Thanh cong");
            return true;
        }
    }
    public Boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME + " where " +  COLUMN_EMAIL + " = ?";
        Cursor cursor = MyDatabase.rawQuery(query, new String[]{email});
        if(cursor.getCount() > 0) {
            Log.d("aaa", "checkEmail: true");
            return true;
        }else {
            Log.d("aaa", "checkEmail: false");
            return false;
        }
    }

    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME + " where " +  COLUMN_EMAIL + " = ? and " +
                COLUMN_PASSWORD + " = ?";
        Cursor cursor = MyDatabase.rawQuery(query, new String[]{email, password});
        if (cursor.getCount() > 0) {
            Log.d("aaa", "checkEmailPassword: true");
            return true;
        }else {
            Log.d("aaa", "checkEmailPassword: false");
            return false;
        }
    }

}
