package com.example.ui_project2024.DB_Manager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Random;

public class Database extends SQLiteOpenHelper {
    private Context context;
    private static final String DB_NAME = "SignLog_Otp.db";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "user_table_otp";
    private static final String COLUMN_NAME_USER = "column_name_user";
    private static final String COLUMN_PHONE_NUMBER = "column_phone_number";
    private static final String COLUMN_EMAIL = "column_email";
    private static final String COLUMN_PASSWORD = "column_password";
    private static final String COLUMN_OTP = "column_otp";
    private static final String COLUMN_CHECK = "column_check";
    public Database(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }

    private String otp() {
        Random rand = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int randomNumber = rand.nextInt(10);
            code.append(randomNumber);
        }
        return code.toString();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_NAME_USER + " TEXT, " +
                COLUMN_PHONE_NUMBER + " TEXT, " +
                COLUMN_EMAIL + " TEXT PRIMARY KEY, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_OTP + " TEXT, " +
                COLUMN_CHECK + " INTEGER DEFAULT 0" + // Giá trị mặc định là false (0)
                ");";
        db.execSQL(CREATE_TABLE);
    }

    public Boolean insertdata_user(String name, String phone, String email, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_NAME_USER, name);
        cv.put(COLUMN_PHONE_NUMBER, phone);
        cv.put(COLUMN_PASSWORD, pass);
        cv.put(COLUMN_OTP, otp());
        String s = otp();
        cv.put(COLUMN_CHECK, 0);
        Log.d("aaa", "insertdata_user: " + s);
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
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists " + TABLE_NAME);
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
    public boolean verifyOTP(String email, String otp) {
        SQLiteDatabase db = this.getReadableDatabase();
        String q = "SELECT " + COLUMN_OTP + " FROM " + TABLE_NAME + " WHERE " + COLUMN_EMAIL + "=?";
        Cursor cursor = db.rawQuery(q, new String[]{email});
        if (cursor != null) {
            if (cursor.moveToNext()) {
                @SuppressLint("Range")
                String storedOtp = cursor.getString(0);  // Lấy giá trị OTP từ cursor

                // In ra giá trị thực của storedOtp
                Log.d("verifyOTP", "OTP from DB: " + storedOtp);  // Sử dụng Log.d để in giá trị storedOtp

                cursor.close();
                return storedOtp.equals(otp);
            }
            cursor.close();
        }
        return false;
    }
    public void updateCheckColumn(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CHECK, 1);
        db.update(TABLE_NAME, contentValues, COLUMN_EMAIL + "=?", new String[]{email});
        db.close();
    }
    public boolean onSubmitOtpClicked(String email, String inputOtp) {
        SQLiteDatabase dbHelper = this.getWritableDatabase();
        boolean isOtpValid = verifyOTP(email, inputOtp);
        if (!isOtpValid) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_CHECK, 1);
            db.update(TABLE_NAME, contentValues, COLUMN_EMAIL + "=?", new String[]{email});
            db.close();
            Log.d("aaa", "Ma otp dung");
            return true;
        } else {
            // Thông báo lỗi OTP không đúng
           Log.d("aaa", "Ma otp khong dung");
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
