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

public class Data_Stadium extends SQLiteOpenHelper {

//  1.Sân
//  -maSan *
//  -tenSan
//  -diaChi
//  -Gia
//  -moTa
//
//  2.HoaDon
//  -MaHD *
//  -TenKhach
//  -Sdt
//  -maSan
//  -NgayDat
//  -GioDat
//  -GioTra
//  -TongTienSan
//
//  3.Dich Vu
//  -maDV *
//  -tenDV
//  -Gia
//  -SoLuong
//
//  4.CTHD
//  -MaHD *
//  -maDV
//  -tongTienDV
    private Context context;
    private static final String DB_NAME = "Stadium.db";
    private static final int VERSION = 1;
    // user
    private static final String TABLE_NAME_USER = "user";
    private static final String COLUMN_NAME_USER = "column_name_user";
    private static final String COLUMN_PHONE_NUMBER_USER = "column_phone_number";
    private static final String COLUMN_EMAIL_USER = "column_email";
    private static final String COLUMN_PASSWORD_USER = "column_password";
    private static final String COLUMN_OTP_USER = "column_otp";
    private static final String COLUMN_CHECK_USER = "column_check";

    // san
    private static final String TABLE_NAME_STADIUM = "stadium";
    private static final String COLUMN_ID_STADIUM = "column_id_stadium";
    private static final String COLUMN_NAME_STADIUM = "column_name_stadium";
    private static final String COLUMN_ADDRESS_STADIUM = "column_address_stadium";
    private static final String COLUMN_PRICE_STADIUM = "column_price_stadium";
    private static final String COLUMN_DESCRIPTION_STADIUM = "column_description_stadium";

    // hoa don
    private static final String TABLE_NAME_BILL = "bill";
    private static final String COLUMN_ID_BILL = "column_id_bill";
    private static final String COLUMN_NAME_CUSTOMER = "column_name_customer_bill";
    private static final String COLUMN_PHONE_CUSTOMER = "column_phone_customer_bill";
    private static final String COLUMN_ID_STADIUM_BILL = "column_id_stadium_bill";
    private static final String COLUMN_DATE_BILL = "column_date_bill";
    private static final String COLUMN_TIME_BILL = "column_time_bill";
    private static final String COLUMN_RETURN_TIME_BILL = "column_return_time_bill";
    private static final String COLUMN_TOTAL_PRICE_BILL = "column_total_price_bill";
    private static final String COLUMN_CHECK = "column_check";

    // dich vu
    private static final String TABLE_NAME_SERVICE = "service";
    private static final String COLUMN_ID_SERVICE = "column_id_service";
    private static final String COLUMN_NAME_SERVICE = "column_name_service";
    private static final String COLUMN_COUNT_NAME_SERVICE = "column_count_name_service";
    private static final String COLUMN_PRICE_SERVICE = "column_price_service";
    private static final String COLUMN_QUANTITY_SERVICE = "column_quantity_service";

    // chi tiet hoa don
    private static final String TABLE_NAME_BILL_DETAIL = "bill_detail";
    private static final String COLUMN_ID_BILL_DETAIL = "column_id_bill_detail";
    private static final String COLUMN_ID_BILL_DETAIL_SERVICE = "column_id_bill_detail_service";
    private static final String COLUMN_TOTAL_PRICE_DETAIL = "column_total_price_detail";



    public Data_Stadium(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_NAME_USER + " (" +
                COLUMN_NAME_USER + " TEXT, " +
                COLUMN_PHONE_NUMBER_USER + " TEXT, " +
                COLUMN_EMAIL_USER + " TEXT PRIMARY KEY, " +
                COLUMN_PASSWORD_USER + " TEXT, " +
                COLUMN_OTP_USER + " TEXT, " +
                COLUMN_CHECK_USER + " INTEGER DEFAULT 0" + // Giá trị mặc định là false (0)
                ");";
        db.execSQL(CREATE_TABLE_USER);

        String CREATE_TABLE_STADIUM = "CREATE TABLE " + TABLE_NAME_STADIUM + " (" +
                COLUMN_ID_STADIUM + " TEXT PRIMARY KEY, " +
                COLUMN_NAME_STADIUM + " TEXT, " +
                COLUMN_ADDRESS_STADIUM + " TEXT, " +
                COLUMN_PRICE_STADIUM + " INTEGER, " +
                COLUMN_DESCRIPTION_STADIUM + " TEXT" +
                ");";
        db.execSQL(CREATE_TABLE_STADIUM);

        String CREATE_TABLE_BILL = "CREATE TABLE " + TABLE_NAME_BILL + " (" +
                COLUMN_ID_BILL + " TEXT PRIMARY KEY, " +
                COLUMN_NAME_CUSTOMER + " TEXT, " +
                COLUMN_PHONE_CUSTOMER + " TEXT, " +
                COLUMN_ID_STADIUM_BILL + " TEXT, " +
                COLUMN_DATE_BILL + " TEXT, " +
                COLUMN_TIME_BILL + " TEXT, " +
                COLUMN_RETURN_TIME_BILL + " TEXT, " +
                COLUMN_TOTAL_PRICE_BILL + " INTEGER, " +  // Thêm dấu phẩy sau INTEGER
                COLUMN_CHECK + " INTEGER DEFAULT 0, " +   // Thêm dấu phẩy sau DEFAULT 0
                "FOREIGN KEY (" + COLUMN_ID_STADIUM_BILL + ") REFERENCES " + TABLE_NAME_STADIUM + "(" + COLUMN_ID_STADIUM + ")" +
                ");";
        db.execSQL(CREATE_TABLE_BILL);

        String CREATE_TABLE_SERVICE = "CREATE TABLE " + TABLE_NAME_SERVICE
                + " (" +
                COLUMN_ID_SERVICE + " TEXT PRIMARY KEY, " +
                COLUMN_NAME_SERVICE + " TEXT, " +
                COLUMN_COUNT_NAME_SERVICE + " INTEGER, " +
                COLUMN_PRICE_SERVICE + " INTEGER, " +
                COLUMN_QUANTITY_SERVICE + " INTEGER" +
                ");";
        db.execSQL(CREATE_TABLE_SERVICE);

        String CREATE_TABLE_BILL_DETAIL = "CREATE TABLE " + TABLE_NAME_BILL_DETAIL + " (" +
                COLUMN_ID_BILL_DETAIL + " TEXT PRIMARY KEY, " +
                COLUMN_ID_BILL_DETAIL_SERVICE + " TEXT, " +
                COLUMN_TOTAL_PRICE_DETAIL + " INTEGER, " +
                "FOREIGN KEY (" + COLUMN_ID_BILL_DETAIL_SERVICE + ") REFERENCES " + TABLE_NAME_SERVICE + "(" + COLUMN_ID_SERVICE + "), " +
                "FOREIGN KEY (" + COLUMN_ID_BILL_DETAIL + ") REFERENCES " + TABLE_NAME_BILL + "(" + COLUMN_ID_BILL + ")" +
                ");";
        db.execSQL(CREATE_TABLE_BILL_DETAIL);
        initDB();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists " + TABLE_NAME_USER);
        db.execSQL("drop Table if exists " + TABLE_NAME_STADIUM);
        db.execSQL("drop Table if exists " + TABLE_NAME_BILL);
        db.execSQL("drop Table if exists " + TABLE_NAME_SERVICE);
        db.execSQL("drop Table if exists " + TABLE_NAME_BILL_DETAIL);
    }

    public void initDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        // insert data table name stadiim
        String a = "INSERT INTO TABLE_NAME_STADIUM (COLUMN_ID_STADIUM, COLUMN_NAME_STADIUM, COLUMN_ADDRESS_STADIUM, COLUMN_PRICE_STADIUM, COLUMN_DESCRIPTION_STADIUM) VALUES " +
                "('S001', 'Stadium A', '123 Stadium Street', " + 500 + ",'A large football stadium')," +
                "('S002', 'Stadium B', '456 Arena Avenue', " + 600 + ", 'A medium-sized arena')," +
                "('S003', 'Stadium C', '789 Field Road', " + 450 + ", 'A small sports field')," +
                "('S004', 'Stadium D', '321 Stadium Lane', " + 700 + ", 'An Olympic-size stadium'), " +
                "('S005', 'Stadium E', '654 Arena Circle'," + 550 + ", 'A modern indoor arena')";
        db.execSQL(a);

        // insert into table name bill
        String b = "INSERT INTO TABLE_NAME_BILL (COLUMN_ID_BILL, COLUMN_NAME_CUSTOMER, COLUMN_PHONE_CUSTOMER, COLUMN_ID_STADIUM_BILL, COLUMN_DATE_BILL, COLUMN_TIME_BILL, COLUMN_RETURN_TIME_BILL, COLUMN_TOTAL_PRICE_BILL, COLUMN_CHECK)" +
                "VALUES" + "('BD001', 'John Doe', '123456789', 'S001', '2024-10-18', '10:00', +  '11:30', " + 200 + "," + 0 + " ), "
                + "('BD002', 'Jane Smith', '987654321', 'S002', '2024-10-17', '09:00', '10:30', " + 200 + "," + 0 + ")," +
                "('BD003', 'Bob Johnson', '456789123', 'S003', '2024-10-16', '9:30', '11:00', " + 200 + "," + 0 + ")," +
                "('BD004', 'Alice Brown', '321654987', 'S004', '2024-10-15', '14:00', '15:30'," + 250 + "," + 0 + ")," +
                "('BD005', 'Charlie White', '654987321', 'S005', '2024-10-14', '08:30', '10:00'," + 200 + "," + 0 + ")";
        db.execSQL(b);

        String c = "INSERT INTO TABLE_NAME_SERVICE (COLUMN_ID_SERVICE, COLUMN_NAME_SERVICE, COLUMN_COUNT_NAME_SERVICE, COLUMN_PRICE_SERVICE, COLUMN_QUANTITY_SERVICE)" +
                "VALUES" + "('SV001', 'Water Bottle'," + 2 + "," + 20 + ", " + 2 * 20 + ")," +
                "('SV002', 'Towel'," + 3 + "," + 20 + ", " + 3 * 20 + ")," +
                " ('SV003', 'Jersey'," + 1 + "," + 50 + ", " + 1 * 50 + "), " +
                " ('SV004', 'Cap'," + 1 + "," + 30 + ", " + 1 * 30 + "), " +
                " ('SV005', 'Shoes'," + 1 + "," + 100 + ", " + 1 * 100 + ")";
        db.execSQL(c);

        String d = "INSERT INTO TABLE_NAME_BILL_DETAIL (COLUMN_ID_BILL_DETAIL, COLUMN_ID_BILL_DETAIL_SERVICE, COLUMN_TOTAL_PRICE_DETAIL)" +
                "VALUES" +
                "('BD001', 'SV001'," + 100 + ")," +
                "('BD002', 'SV002'," + 45 + ")," +
                "('BD003', 'SV003'," + 150 + ")," +
                "('BD004', 'SV004'," + 30 + ")," +
                "('BD005', 'SV005'," + 100 + ")";
        db.execSQL(d);
    }

    // user
    private String otp() {
        Random rand = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int randomNumber = rand.nextInt(10);
            code.append(randomNumber);
        }
        return code.toString();
    }
    public Boolean insertdata_user(String name, String phone, String email, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_EMAIL_USER, email);
        cv.put(COLUMN_NAME_USER, name);
        cv.put(COLUMN_PHONE_NUMBER_USER, phone);
        cv.put(COLUMN_PASSWORD_USER, pass);
        cv.put(COLUMN_OTP_USER, otp());
        String s = otp();
        cv.put(COLUMN_CHECK_USER, 0);
        Log.d("aaa", "insertdata_user: " + s);
        long res = db.insert(TABLE_NAME_USER, null, cv);
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
        String query = "Select * from " + TABLE_NAME_USER + " where " +  COLUMN_EMAIL_USER + " = ?";
        Cursor cursor = MyDatabase.rawQuery(query, new String[]{email});
        if(cursor.getCount() > 0) {
            Log.d("aaa", "checkEmail: true");
            return true;
        }else {
            Log.d("aaa", "checkEmail: false");
            return false;
        }
    }
    // check lai neu lam xong
    public boolean verifyOTP(String email, String otp) {
        SQLiteDatabase db = this.getReadableDatabase();
        String q = "SELECT " + COLUMN_OTP_USER + " FROM " + TABLE_NAME_USER + " WHERE " + COLUMN_EMAIL_USER + "=?";
        Cursor cursor = db.rawQuery(q, new String[]{email});
        if (cursor != null) {
            if (cursor.moveToNext()) {
                @SuppressLint("Range")
                String storedOtp = cursor.getString(0);
                Log.d("verifyOTP", "OTP from DB: " + storedOtp);
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
        contentValues.put(COLUMN_CHECK_USER, 1);
        db.update(TABLE_NAME_USER, contentValues, COLUMN_EMAIL_USER + "=?", new String[]{email});
        db.close();
    }
    public boolean onSubmitOtpClicked(String email, String inputOtp) {
        SQLiteDatabase dbHelper = this.getWritableDatabase();
        boolean isOtpValid = verifyOTP(email, inputOtp);
        if (!isOtpValid) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_CHECK_USER, 1);
            db.update(TABLE_NAME_USER, contentValues, COLUMN_EMAIL_USER + "=?", new String[]{email});
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
        String query = "Select * from " + TABLE_NAME_USER + " where " +  COLUMN_EMAIL_USER + " = ? and " +
                COLUMN_PASSWORD_USER + " = ?";
        Cursor cursor = MyDatabase.rawQuery(query, new String[]{email, password});
        if (cursor.getCount() > 0) {
            Log.d("aaa", "checkEmailPassword: true");
            return true;
        }else {
            Log.d("aaa", "checkEmailPassword: false");
            return false;
        }
    }

    // stadium
    public Boolean insertdata_stadium(String id, String name, String address, int price, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID_STADIUM, id);
        cv.put(COLUMN_NAME_STADIUM, name);
        cv.put(COLUMN_ADDRESS_STADIUM, address);
        cv.put(COLUMN_PRICE_STADIUM, price);
        cv.put(COLUMN_DESCRIPTION_STADIUM, description);
        long res = db.insert(TABLE_NAME_STADIUM, null, cv);
        if (res == -1) {
            Log.d("aaa", "insertdata_stadium: Khong thanh cong");
            return false;
        }
        else{
            Log.d("aaa", "insertdata_stadium: Thanh cong");
            return true;
        }
    }
    public Cursor getdata_stadium(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME_STADIUM, null);
        return cursor;
    }
    // hoa don
    public Boolean insertdata_bill(String id, String name, String phone, String id_stadium, String date, String time, String return_time, int total_price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID_BILL, id);
        cv.put(COLUMN_NAME_CUSTOMER, name);
        cv.put(COLUMN_PHONE_CUSTOMER, phone);
        cv.put(COLUMN_ID_STADIUM_BILL, id_stadium);
        cv.put(COLUMN_DATE_BILL, date);
        long res = db.insert(TABLE_NAME_BILL, null, cv);
        if (res == -1) {
            Log.d("aaa", "insertdata_bill: Khong thanh cong");
            return false;
        } else {
            Log.d("aaa", "insertdata_bill: Thanh cong");
            return true;
        }
    }
    public Cursor getdata_bill() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME_BILL, null);
        return cursor;
    }
    // dich vu
    public Boolean insertdata_service(String id, String name, int price, int quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID_SERVICE, id);
        cv.put(COLUMN_NAME_SERVICE, name);
        cv.put(COLUMN_PRICE_SERVICE, price);
        cv.put(COLUMN_QUANTITY_SERVICE, quantity);
        long res = db.insert(TABLE_NAME_SERVICE, null, cv);
        if (res == -1) {
            Log.d("aaa", "insertdata_service: Khong thanh cong");
            return false;
        } else {
            Log.d("aaa", "insertdata_service: Thanh cong");
            return true;
        }
    }
    public Cursor getdata_service() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME_SERVICE, null);
        return cursor;
    }
    // chi tiet hoa don
    public Boolean insertdata_bill_detail(String id, String id_service, int total_price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID_BILL_DETAIL, id);
        cv.put(COLUMN_ID_BILL_DETAIL_SERVICE, id_service);
        cv.put(COLUMN_TOTAL_PRICE_DETAIL, total_price);
        long res = db.insert(TABLE_NAME_BILL_DETAIL, null, cv);
        if (res == -1){
            Log.d("aaa", "insertdata_bill_detail: Khong thanh cong");
            return false;
        }
        else {
            Log.d("aaa", "insertdata_bill_detail: Thanh cong");
            return true;
        }
    }
    public Cursor getdata_bill_detail() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME_BILL_DETAIL, null);
        return cursor;
    }
}
