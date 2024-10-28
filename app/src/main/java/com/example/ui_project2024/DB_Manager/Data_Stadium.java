package com.example.ui_project2024.DB_Manager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.ui_project2024.List.List_Bill_Items;
import com.github.mikephil.charting.data.PieEntry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
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
    private static final int VERSION = 2;
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
    private static final String COLUMN_NUMBER_SERVICE = "column_number_service";
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

        String CREATE_TABLE_STADIUM = "CREATE TABLE  IF NOT EXISTS " + TABLE_NAME_STADIUM + " (" +
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
// bang do day nay
        String CREATE_TABLE_BILL_DETAIL = "CREATE TABLE " + TABLE_NAME_BILL_DETAIL + " (" +
                COLUMN_ID_BILL_DETAIL + " TEXT, " +
                COLUMN_ID_BILL_DETAIL_SERVICE + " TEXT, " +
                COLUMN_NUMBER_SERVICE + " INTEGER, " +
                COLUMN_TOTAL_PRICE_DETAIL + " INTEGER, " +
                "PRIMARY KEY ("+COLUMN_ID_BILL_DETAIL+","+COLUMN_ID_BILL_DETAIL_SERVICE+"),"+
                "FOREIGN KEY (" + COLUMN_ID_BILL_DETAIL_SERVICE + ") REFERENCES " + TABLE_NAME_SERVICE + "(" + COLUMN_ID_SERVICE + "), " +
                "FOREIGN KEY (" + COLUMN_ID_BILL_DETAIL + ") REFERENCES " + TABLE_NAME_BILL + "(" + COLUMN_ID_BILL + ")" +
                ");";
        db.execSQL(CREATE_TABLE_BILL_DETAIL);
    }

    public void logAllBillDetail() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_BILL_DETAIL;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String idBillDetail = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID_BILL_DETAIL));
                String idBillDetailService = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID_BILL_DETAIL_SERVICE));
                // int numberService = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NUMBER_SERVICE));
                int totalPriceDetail = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TOTAL_PRICE_DETAIL));

                Log.d("BILL_DETAIL", "ID_BILL_DETAIL: " + idBillDetail + ", " +
                        "ID_BILL_DETAIL_SERVICE: " + idBillDetailService + ", " +
                        "NUMBER_SERVICE: ?"  +
                        "TOTAL_PRICE_DETAIL: " + totalPriceDetail);

            } while (cursor.moveToNext());
        } else {
            Log.d("BILL_DETAIL", "No data found in BILL_DETAIL table.");
        }

        cursor.close();
        db.close();
    }


//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop Table if exists " + TABLE_NAME_USER);
//        db.execSQL("drop Table if exists " + TABLE_NAME_STADIUM);
//        db.execSQL("drop Table if exists " + TABLE_NAME_BILL);
//        db.execSQL("drop Table if exists " + TABLE_NAME_SERVICE);
//        db.execSQL("drop Table if exists " + TABLE_NAME_BILL_DETAIL);
//    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_NAME_BILL_DETAIL + " ADD COLUMN " + COLUMN_NUMBER_SERVICE + " INTEGER DEFAULT 0");
        }
    }

    public void checkColumnExists(SQLiteDatabase db, String tableName, String columnName) {
        Cursor cursor = db.rawQuery("PRAGMA table_info(" + tableName + ")", null);
        boolean columnExists = false;

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String currentColumn = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                if (currentColumn.equals(columnName)) {
                    columnExists = true;
                    break;
                }
            }
            cursor.close();
        }

        if (columnExists) {
            Log.d("DatabaseCheck", "Cột '" + columnName + "' đã tồn tại trong bảng '" + tableName + "'.");
        } else {
            Log.d("DatabaseCheck", "Cột '" + columnName + "' chưa được thêm vào bảng '" + tableName + "'.");
        }
    }

    public void checkTableExists() {
        SQLiteDatabase db = this.getReadableDatabase();
        db = this.getReadableDatabase();
        checkColumnExists(db, TABLE_NAME_BILL_DETAIL, COLUMN_NUMBER_SERVICE);
    }


    public void ExData(String s){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(s);
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
        String q = "SELECT " + COLUMN_OTP_USER + " FROM " + TABLE_NAME_USER + " WHERE " + COLUMN_EMAIL_USER + " = " + "'" + email + "'";
        Cursor cursor = db.rawQuery(q, null);
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
        db.update(TABLE_NAME_USER, contentValues, COLUMN_EMAIL_USER + " = " + "'" + email + "'", null);
        db.close();
    }
    public boolean onSubmitOtpClicked(String email, String inputOtp) {
        SQLiteDatabase dbHelper = this.getWritableDatabase();
        boolean isOtpValid = verifyOTP(email, inputOtp);
        if (isOtpValid) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_CHECK_USER, 1);
            db.update(TABLE_NAME_USER, contentValues, COLUMN_EMAIL_USER + " = " + "'" + email + "'", null);
            db.close();
            Log.d("aaa", "Ma otp dung");
            return true;
        } else {
            // Thông báo lỗi OTP không đúng
            Log.d("aaa", "Ma otp khong dung");
            return false;
        }
    }

    public boolean onSubmitOtpClicked2(String email, String inputOtp) {
        SQLiteDatabase dbHelper = this.getWritableDatabase();
        boolean isOtpValid = verifyOTP(email, inputOtp);
        if (isOtpValid) {
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

    public void initDB() {
        SQLiteDatabase db = this.getWritableDatabase();

        // insert data into stadium table
        String a = "INSERT INTO " + TABLE_NAME_STADIUM + " VALUES " +
                "('S001', 'Stadium A', '123 Stadium Street', 500, 'A large football stadium')," +
                "('S002', 'Stadium B', '456 Arena Avenue', 600, 'A medium-sized arena')," +
                "('S003', 'Stadium C', '789 Field Road', 450, 'A small sports field')," +
                "('S004', 'Stadium D', '321 Stadium Lane', 700, 'An Olympic-size stadium'), " +
                "('S005', 'Stadium E', '654 Arena Circle', 550, 'A modern indoor arena')";
        db.execSQL(a);

        // insert data into bill table
        String b = "INSERT INTO " + TABLE_NAME_BILL + " VALUES " +
                "('BD001', 'John Doe', '123456789', 'S001', '2024-10-18', '10:00', '11:30', 200, 0)," +
                "('BD002', 'Jane Smith', '987654321', 'S002', '2024-10-17', '09:00', '10:30', 200, 0)," +
                "('BD003', 'Bob Johnson', '456789123', 'S003', '2024-10-16', '9:30', '11:00', 200, 0)," +
                "('BD004', 'Alice Brown', '321654987', 'S004', '2024-10-15', '14:00', '15:30', 250, 0)," +
                "('BD005', 'Charlie White', '654987321', 'S005', '2024-10-14', '08:30', '10:00', 200, 0)";
        db.execSQL(b);

        // insert data into service table
        String c = "INSERT INTO " + TABLE_NAME_SERVICE +
                " (COLUMN_ID_SERVICE, COLUMN_NAME_SERVICE, COLUMN_COUNT_NAME_SERVICE, COLUMN_PRICE_SERVICE, COLUMN_QUANTITY_SERVICE) " +
                "VALUES " +
                "('SV001', 'Water Bottle', 2, 20, 40)," +
                "('SV002', 'Towel', 3, 20, 60)," +
                "('SV003', 'Jersey', 1, 50, 50)," +
                "('SV004', 'Cap', 1, 30, 30)," +
                "('SV005', 'Shoes', 1, 100, 100)";
        db.execSQL(c);
        // cu de data do cho ban con lam
        //thu insert 2 du lieu co chung 1 ma bill di r chay bai
        // uh
        // insert data into bill detail table

        Log.d("aaa", "initDB: thanh cong");

    }

    public void init2DB() {
        SQLiteDatabase db = this.getWritableDatabase();
        String d = "INSERT INTO " + TABLE_NAME_BILL_DETAIL +
                " VALUES " +
                "('BD001', 'SV001' ,2, 100)," +
                "('BD002', 'SV002', 3,45)," +
                "('BD001', 'SV003', 5, 150)," +
                "('BD004', 'SV004', 4, 30)," +
                "('BD005', 'SV005', 3, 100)";
        db.execSQL(d);

        String f = "INSERT INTO " + TABLE_NAME_BILL_DETAIL + " VALUES " +
                "('BD006', 'SV001',2, 100)," +
                "('BD007', 'SV001',3, 50)," +
                "('BD008', 'SV003',4, 170)," +
                "('BD009', 'SV004',5, 90)," +
                "('BD010', 'SV005',1, 100)";
        db.execSQL(f);
        Log.d("aaa", "initDB3: thanh cong");
    }

    public void init3DB() {
        SQLiteDatabase db = this.getWritableDatabase();
        String e = "INSERT INTO " + TABLE_NAME_BILL + " VALUES " +
                "('BD006', 'John Doe', '123456789', 'S001', '2024-10-20', '10:00', '11:30', 250, 1)," +
                "('BD007', 'Jane Smith', '987654321', 'S002', '2024-10-21', '09:00', '10:30', 230, 1)," +
                "('BD008', 'Bob Johnson', '456789123', 'S003', '2024-10-22', '9:30', '11:00', 240, 1)," +
                "('BD009', 'Alice Brown', '321654987', 'S004', '2024-10-23', '14:00', '15:30', 270, 1)," +
                "('BD010', 'Charlie White', '654987321', 'S005', '2024-10-24', '08:30', '10:00', 280, 1)";
        db.execSQL(e);
        Log.d("aaa", "initDB3: thanh cong");
    }

    public ArrayList<List_Bill_Items> getAllBills() {
        ArrayList<List_Bill_Items> billList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        // Lấy tất cả các hóa đơn từ bảng BILL (giả định)
        String q = "SELECT " + TABLE_NAME_BILL + "." + COLUMN_ID_BILL + ", " +
                TABLE_NAME_BILL + "." + COLUMN_NAME_CUSTOMER + ", " +
                TABLE_NAME_BILL + "." + COLUMN_PHONE_CUSTOMER + ", " +
                TABLE_NAME_BILL + "." + COLUMN_ID_STADIUM_BILL + ", " +
                TABLE_NAME_BILL + "." + COLUMN_DATE_BILL + ", " +
                TABLE_NAME_BILL + "." + COLUMN_TIME_BILL + ", " +
                TABLE_NAME_BILL + "." + COLUMN_RETURN_TIME_BILL + ", " +
                "SUM(" +
                TABLE_NAME_BILL + "." + COLUMN_TOTAL_PRICE_BILL + " + " +
                TABLE_NAME_BILL_DETAIL + "." + COLUMN_TOTAL_PRICE_DETAIL + " + " +
                TABLE_NAME_SERVICE + "." + COLUMN_QUANTITY_SERVICE + " + " +
                TABLE_NAME_STADIUM + "." + COLUMN_PRICE_STADIUM + ") as 'total', " +
                TABLE_NAME_BILL + "." + COLUMN_CHECK + " " +
                " FROM " + TABLE_NAME_BILL +
                " INNER JOIN " + TABLE_NAME_STADIUM +
                " ON " + TABLE_NAME_BILL + "." + COLUMN_ID_STADIUM_BILL + " = " + TABLE_NAME_STADIUM + "." + COLUMN_ID_STADIUM +
                " INNER JOIN " + TABLE_NAME_BILL_DETAIL +
                " ON " + TABLE_NAME_BILL + "." + COLUMN_ID_BILL + " = " + TABLE_NAME_BILL_DETAIL + "." + COLUMN_ID_BILL_DETAIL +
                " INNER JOIN " + TABLE_NAME_SERVICE +
                " ON " + TABLE_NAME_BILL_DETAIL + "." + COLUMN_ID_BILL_DETAIL_SERVICE + " = " + TABLE_NAME_SERVICE + "." + COLUMN_ID_SERVICE +
                " GROUP BY " + TABLE_NAME_BILL + "." + COLUMN_ID_BILL + ", " + TABLE_NAME_STADIUM + "." + COLUMN_PRICE_STADIUM;
        Cursor cursor = db.rawQuery(q, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                String id_studium = cursor.getString(3);
                String date = cursor.getString(4);
                String time = cursor.getString(5);
                String time_return  = cursor.getString(6);
                int total = cursor.getInt(7);
                int status = cursor.getInt(8);
                // Thêm hóa đơn vào danh sách
                billList.add(new List_Bill_Items(id, name, phone, id_studium, date, time, time_return, total, status));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return billList;
    }


    public ArrayList<List_Bill_Items> getBillsPayment() {
        ArrayList<List_Bill_Items> billList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "SELECT " + TABLE_NAME_BILL + "." + COLUMN_ID_BILL + ", " +
                TABLE_NAME_BILL + "." + COLUMN_NAME_CUSTOMER + ", " +
                TABLE_NAME_BILL + "." + COLUMN_PHONE_CUSTOMER + ", " +
                TABLE_NAME_BILL + "." + COLUMN_ID_STADIUM_BILL + ", " +
                TABLE_NAME_BILL + "." + COLUMN_DATE_BILL + ", " +
                TABLE_NAME_BILL + "." + COLUMN_TIME_BILL + ", " +
                TABLE_NAME_BILL + "." + COLUMN_RETURN_TIME_BILL + ", " +
                "SUM(" +
                TABLE_NAME_BILL + "." + COLUMN_TOTAL_PRICE_BILL + " + " +
                TABLE_NAME_BILL_DETAIL + "." + COLUMN_TOTAL_PRICE_DETAIL + " + " +
                TABLE_NAME_SERVICE + "." + COLUMN_QUANTITY_SERVICE + " + " +
                TABLE_NAME_STADIUM + "." + COLUMN_PRICE_STADIUM + ") as 'total', " +
                TABLE_NAME_BILL + "." + COLUMN_CHECK + " " +
                " FROM " + TABLE_NAME_BILL +
                " INNER JOIN " + TABLE_NAME_STADIUM +
                " ON " + TABLE_NAME_BILL + "." + COLUMN_ID_STADIUM_BILL + " = " + TABLE_NAME_STADIUM + "." + COLUMN_ID_STADIUM +
                " INNER JOIN " + TABLE_NAME_BILL_DETAIL +
                " ON " + TABLE_NAME_BILL + "." + COLUMN_ID_BILL + " = " + TABLE_NAME_BILL_DETAIL + "." + COLUMN_ID_BILL_DETAIL +
                " INNER JOIN " + TABLE_NAME_SERVICE +
                " ON " + TABLE_NAME_BILL_DETAIL + "." + COLUMN_ID_BILL_DETAIL_SERVICE + " = " + TABLE_NAME_SERVICE + "." + COLUMN_ID_SERVICE +
                " GROUP BY " + TABLE_NAME_BILL + "." + COLUMN_ID_BILL + ", " + TABLE_NAME_STADIUM + "." + COLUMN_PRICE_STADIUM
                + " HAVING " + TABLE_NAME_BILL + "." + COLUMN_CHECK + " = " + 1;
        Cursor cursor = db.rawQuery(Query , null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                String id_studium = cursor.getString(3);
                String date = cursor.getString(4);
                String time = cursor.getString(5);
                String time_return  = cursor.getString(6);
                int total = cursor.getInt(7);
                int status = cursor.getInt(8);
                // Thêm hóa đơn vào danh sách
                billList.add(new List_Bill_Items(id, name, phone, id_studium, date, time, time_return, total, status));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return billList;
    }

    public int gettotal(){
        int total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "SELECT SUM(" +
                TABLE_NAME_BILL + "." + COLUMN_TOTAL_PRICE_BILL + " + " +
                TABLE_NAME_BILL_DETAIL + "." + COLUMN_TOTAL_PRICE_DETAIL + " + " +
                "(" + TABLE_NAME_SERVICE + "." + COLUMN_QUANTITY_SERVICE + " * " +
                TABLE_NAME_SERVICE + "." + COLUMN_PRICE_SERVICE + ") + " +
                TABLE_NAME_STADIUM + "." + COLUMN_PRICE_STADIUM + ") AS total_revenue " +
                "FROM " + TABLE_NAME_BILL +
                " INNER JOIN " + TABLE_NAME_STADIUM +
                " ON " + TABLE_NAME_BILL + "." + COLUMN_ID_STADIUM_BILL + " = " + TABLE_NAME_STADIUM + "." + COLUMN_ID_STADIUM +
                " INNER JOIN " + TABLE_NAME_BILL_DETAIL +
                " ON " + TABLE_NAME_BILL + "." + COLUMN_ID_BILL + " = " + TABLE_NAME_BILL_DETAIL + "." + COLUMN_ID_BILL_DETAIL +
                " INNER JOIN " + TABLE_NAME_SERVICE +
                " ON " + TABLE_NAME_BILL_DETAIL + "." + COLUMN_ID_BILL_DETAIL_SERVICE + " = " + TABLE_NAME_SERVICE + "." + COLUMN_ID_SERVICE +
                " WHERE " + TABLE_NAME_BILL + "." + COLUMN_CHECK + " = 1";
        Cursor cs = db.rawQuery(Query, null);
        if (cs.moveToFirst()) {
            do {
                total = cs.getInt(0);
            } while (cs.moveToNext());
        }
        return total;
    }
    public ArrayList<List_Bill_Items> getnoBillsPayment() {
        ArrayList<List_Bill_Items> billList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String q = "SELECT " + TABLE_NAME_BILL + "." + COLUMN_ID_BILL + ", " +
                TABLE_NAME_BILL + "." + COLUMN_NAME_CUSTOMER + ", " +
                TABLE_NAME_BILL + "." + COLUMN_PHONE_CUSTOMER + ", " +
                TABLE_NAME_BILL + "." + COLUMN_ID_STADIUM_BILL + ", " +
                TABLE_NAME_BILL + "." + COLUMN_DATE_BILL + ", " +
                TABLE_NAME_BILL + "." + COLUMN_TIME_BILL + ", " +
                TABLE_NAME_BILL + "." + COLUMN_RETURN_TIME_BILL + ", " +
                "SUM(" +
                TABLE_NAME_BILL + "." + COLUMN_TOTAL_PRICE_BILL + " + " +
                TABLE_NAME_BILL_DETAIL + "." + COLUMN_TOTAL_PRICE_DETAIL + " + " +
                TABLE_NAME_SERVICE + "." + COLUMN_QUANTITY_SERVICE + " + " +
                TABLE_NAME_STADIUM + "." + COLUMN_PRICE_STADIUM + ") as 'total', " +
                TABLE_NAME_BILL + "." + COLUMN_CHECK + " " +
                " FROM " + TABLE_NAME_BILL +
                " INNER JOIN " + TABLE_NAME_STADIUM +
                " ON " + TABLE_NAME_BILL + "." + COLUMN_ID_STADIUM_BILL + " = " + TABLE_NAME_STADIUM + "." + COLUMN_ID_STADIUM +
                " INNER JOIN " + TABLE_NAME_BILL_DETAIL +
                " ON " + TABLE_NAME_BILL + "." + COLUMN_ID_BILL + " = " + TABLE_NAME_BILL_DETAIL + "." + COLUMN_ID_BILL_DETAIL +
                " INNER JOIN " + TABLE_NAME_SERVICE +
                " ON " + TABLE_NAME_BILL_DETAIL + "." + COLUMN_ID_BILL_DETAIL_SERVICE + " = " + TABLE_NAME_SERVICE + "." + COLUMN_ID_SERVICE +
                " GROUP BY " + TABLE_NAME_BILL + "." + COLUMN_ID_BILL + ", " + TABLE_NAME_STADIUM + "." + COLUMN_PRICE_STADIUM
                + " HAVING " + TABLE_NAME_BILL + "." + COLUMN_CHECK + " = " + 0;
        Cursor cursor = db.rawQuery(q , null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                String id_studium = cursor.getString(3);
                String date = cursor.getString(4);
                String time = cursor.getString(5);
                String time_return  = cursor.getString(6);
                int total = cursor.getInt(7);
                int status = cursor.getInt(8);
                billList.add(new List_Bill_Items(id, name, phone, id_studium, date, time, time_return, total, status));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return billList;
    }

    public ArrayList<PieEntry> getchart(String time1, String time2) {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Chuyển đổi time1 và time2 sang định dạng yyyy-MM-dd
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        try {
            Date date1 = inputFormat.parse(time1);
            Date date2 = inputFormat.parse(time2);

            time1 = outputFormat.format(date1);  // Chuyển sang định dạng yyyy-MM-dd
            time2 = outputFormat.format(date2);
            Log.d("aaa", "getchart: " + time1);
            Log.d("aaa", "getchart: " + time2);
        } catch (ParseException e) {
            e.printStackTrace();
            return pieEntries; // Trả về danh sách rỗng nếu có lỗi trong việc chuyển đổi ngày
        }
        // Câu truy vấn SQL
        String q = "SELECT " + TABLE_NAME_STADIUM + "." + COLUMN_NAME_STADIUM + ", " +
                "SUM(" +
                TABLE_NAME_BILL + "." + COLUMN_TOTAL_PRICE_BILL + " + " +
                TABLE_NAME_BILL_DETAIL + "." + COLUMN_TOTAL_PRICE_DETAIL + " + " +
                TABLE_NAME_SERVICE + "." + COLUMN_QUANTITY_SERVICE + " + " +
                TABLE_NAME_STADIUM + "." + COLUMN_PRICE_STADIUM + ") as 'total' " +
                " FROM " + TABLE_NAME_BILL +
                " INNER JOIN " + TABLE_NAME_STADIUM +
                " ON " + TABLE_NAME_BILL + "." + COLUMN_ID_STADIUM_BILL + " = " + TABLE_NAME_STADIUM + "." + COLUMN_ID_STADIUM +
                " INNER JOIN " + TABLE_NAME_BILL_DETAIL +
                " ON " + TABLE_NAME_BILL + "." + COLUMN_ID_BILL + " = " + TABLE_NAME_BILL_DETAIL + "." + COLUMN_ID_BILL_DETAIL +
                " INNER JOIN " + TABLE_NAME_SERVICE +
                " ON " + TABLE_NAME_BILL_DETAIL + "." + COLUMN_ID_BILL_DETAIL_SERVICE + " = " + TABLE_NAME_SERVICE + "." + COLUMN_ID_SERVICE +
                " WHERE " + TABLE_NAME_BILL + "." + COLUMN_DATE_BILL + " BETWEEN '" + time1 + "' AND '" + time2 + "' " +  // Thêm dấu nháy đơn quanh ngày
                " GROUP BY " + TABLE_NAME_BILL + "." + COLUMN_ID_BILL + ", " + TABLE_NAME_STADIUM + "." + COLUMN_PRICE_STADIUM +
                " HAVING " + TABLE_NAME_BILL + "." + COLUMN_CHECK + " = 1";

        Cursor cursor = db.rawQuery(q, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                int value = cursor.getInt(1);
                pieEntries.add(new PieEntry(value, name));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return pieEntries;
    }

    public ArrayList<PieEntry> getServiceDetails(String time1, String time2) {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date date1 = inputFormat.parse(time1);
            Date date2 = inputFormat.parse(time2);

            time1 = outputFormat.format(date1);  // Chuyển sang định dạng yyyy-MM-dd
            time2 = outputFormat.format(date2);
            Log.d("aaa", "getchart: " + time1);
            Log.d("aaa", "getchart: " + time2);
        } catch (ParseException e) {
            e.printStackTrace();
            return pieEntries; // Trả về danh sách rỗng nếu có lỗi trong việc chuyển đổi ngày
        }
        // Câu truy vấn SQL
        String q = "SELECT " + TABLE_NAME_SERVICE  + "." + COLUMN_NAME_SERVICE  + ", " +
                "SUM(" +
                TABLE_NAME_BILL + "." + COLUMN_TOTAL_PRICE_BILL + " + " +
                TABLE_NAME_BILL_DETAIL + "." + COLUMN_TOTAL_PRICE_DETAIL + " + " +
                TABLE_NAME_SERVICE + "." + COLUMN_QUANTITY_SERVICE + " + " +
                TABLE_NAME_STADIUM + "." + COLUMN_PRICE_STADIUM + ") as 'total' " +
                " FROM " + TABLE_NAME_BILL +
                " INNER JOIN " + TABLE_NAME_STADIUM +
                " ON " + TABLE_NAME_BILL + "." + COLUMN_ID_STADIUM_BILL + " = " + TABLE_NAME_STADIUM + "." + COLUMN_ID_STADIUM +
                " INNER JOIN " + TABLE_NAME_BILL_DETAIL +
                " ON " + TABLE_NAME_BILL + "." + COLUMN_ID_BILL + " = " + TABLE_NAME_BILL_DETAIL + "." + COLUMN_ID_BILL_DETAIL +
                " INNER JOIN " + TABLE_NAME_SERVICE +
                " ON " + TABLE_NAME_BILL_DETAIL + "." + COLUMN_ID_BILL_DETAIL_SERVICE + " = " + TABLE_NAME_SERVICE + "." + COLUMN_ID_SERVICE +
                " WHERE " + TABLE_NAME_BILL + "." + COLUMN_DATE_BILL + " BETWEEN '" + time1 + "' AND '" + time2 + "' " +  // Thêm dấu nháy đơn quanh ngày
                " GROUP BY " + TABLE_NAME_SERVICE + "." + COLUMN_NAME_SERVICE + ", " + TABLE_NAME_BILL_DETAIL + "." + COLUMN_ID_BILL_DETAIL_SERVICE +
                " HAVING " + TABLE_NAME_BILL + "." + COLUMN_CHECK + " = 1";
        Cursor cursor = db.rawQuery(q, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                int value = cursor.getInt(1);
                pieEntries.add(new PieEntry(value, name));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return pieEntries;
    }

    public boolean forget_pass(String email_or_phone) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_USER + " WHERE " + COLUMN_EMAIL_USER + " = ?" ;
        Cursor cursor = db.rawQuery(query, new String[]{email_or_phone});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        db.close();
        return exists;
    }
    public String get_phone(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_PHONE_NUMBER_USER + " FROM " + TABLE_NAME_USER + " WHERE " + COLUMN_EMAIL_USER + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        String phone = "";
        if (cursor.moveToFirst()) {
            phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE_NUMBER_USER));
        }
        cursor.close();
        db.close();
        return phone;
    }
    //tim ho tao bang init
//    public void updateAllUserCheckColumn() {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        db.execSQL("UPDATE " + TABLE_NAME_USER + " SET " +  COLUMN_CHECK_USER + " = " +1);
//
//        Log.d("updateAllUserCheckColumn", "All rows updated with check_column = 1");
//    }

    public void check_column_all_user(){
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT " + COLUMN_EMAIL_USER + "," +COLUMN_CHECK_USER + " FROM " + TABLE_NAME_USER;
        Cursor cs = db.rawQuery(q, null);
        if (cs.moveToFirst()){
            do {
                String email = cs.getString(0);
                int check = cs.getInt(1);
                Log.d("check_column_all_user", "email: " + email + " check: " + check);
            } while (cs.moveToNext());
        }

    }
    public int check_user_forgot(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String q = "SELECT " + COLUMN_CHECK_USER +" FROM " + TABLE_NAME_USER + " WHERE " + COLUMN_EMAIL_USER + " = '" + email + "'";
        Cursor cs = db.rawQuery(q, null);
        if (cs.moveToFirst()) {
            return cs.getInt(0);
        }
        return 0;
    }

    public void updatepass(String email, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PASSWORD_USER, pass);
        db.update(TABLE_NAME_USER, cv, COLUMN_EMAIL_USER + " = " + "'" + email + "'", null);
    }

    public void update_check_bill(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CHECK, 1);
        db.update(TABLE_NAME_BILL, cv, COLUMN_ID_BILL + " = " + "'" + id + "'", null);
    }

    public int check_bill(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String q = "SELECT " + COLUMN_CHECK +" FROM " + TABLE_NAME_BILL + " WHERE " + COLUMN_ID_BILL + " = '" + id + "'";
        Cursor cs = db.rawQuery(q, null);
        if (cs.moveToFirst())  return cs.getInt(0);
        return 0;
    }

    public String getName(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String q = "SELECT " + COLUMN_NAME_USER + " FROM " + TABLE_NAME_USER + " WHERE " + COLUMN_EMAIL_USER + " = '" + email + "'";
        Cursor cs = db.rawQuery(q, null);
        if (cs.moveToFirst())  return cs.getString(0);
        return null;
    }

    public boolean checkPassword(String email, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        String q = "SELECT " + COLUMN_PASSWORD_USER + " FROM " + TABLE_NAME_USER + " WHERE " + COLUMN_EMAIL_USER + " = " + "'" + email + "' " + "AND " + COLUMN_CHECK_USER + " = " + 1;;
        Cursor cs = db.rawQuery(q, null);
        boolean result = false;
        if (cs.moveToFirst()) {
            String storedPassword = cs.getString(0); // Lấy mật khẩu từ CSDL
            if (storedPassword.equals(pass)) { // So sánh với mật khẩu đã nhập
                result = true;
                Log.d("aaa", "checkPassword: true");
            } else {
                Log.d("aaa", "checkPassword: incorrect password");
            }
        } else {
            Log.d("aaa", "checkPassword: user not found or inactive");
        }

        cs.close(); // Đóng Cursor
        db.close(); // Đóng Database
        return result;
    }
    public void updateName(String email, String name){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME_USER, name);
        db.update(TABLE_NAME_USER, cv, COLUMN_EMAIL_USER + " = " + "'" + email + "'", null);
    }

    public void updateEmail(String oldEmail, String newEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_EMAIL_USER, newEmail);
        db.update(TABLE_NAME_USER, cv, COLUMN_EMAIL_USER + " = ?", new String[]{oldEmail});
        db.close();
    }

    public void updatePhone(String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PHONE_NUMBER_USER, phone);
        db.update(TABLE_NAME_USER, cv, COLUMN_EMAIL_USER + " = " + "'" + email + "'", null);
    }
    public String getPhone(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String q = "SELECT " + COLUMN_PHONE_NUMBER_USER + " FROM " + TABLE_NAME_USER + " WHERE " + COLUMN_EMAIL_USER + " = '" + email + "'";
        Cursor cs = db.rawQuery(q, null);
        if (cs.moveToFirst())  return cs.getString(0);
        return null;
    }

    public void updatePassword(String email, String passnew){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PASSWORD_USER, passnew);
        db.update(TABLE_NAME_USER, cv, COLUMN_EMAIL_USER + " = " + "'" + email + "'", null);
    }

}
