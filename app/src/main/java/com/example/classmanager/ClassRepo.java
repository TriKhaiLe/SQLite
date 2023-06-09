package com.example.classmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class ClassRepo extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ClassRoom.db";
    public static final String SQL_CREATE_TABLE_USER
            = "CREATE TABLE IF NOT EXISTS USER (username varchar(50) primary key, " +
            "password varchar(50)" +
            ")";

    public static final String SQL_CREATE_TABLE_CLASS
            = "CREATE TABLE IF NOT EXISTS CLASS (" +
            "classid integer primary key autoincrement, " +
            "classname varchar(50)," +
            "students integer" +
            ")";

    public static final String SQL_CREATE_TABLE_STUDENT
            = "CREATE TABLE IF NOT EXISTS STUDENT (" +
            "studentid integer primary key autoincrement, " +
            "name varchar(50)," +
            "dob varchar(50)," +
            "classid integer," +
            "foreign key (classid) references CLASS (classid) " +
            ")";
    private static ClassRepo instance;
    private final Context context;
    ArrayList<User> userList;

    public ClassRepo(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static synchronized ClassRepo getInstance(Context context) {
        if (instance == null) {
            instance = new ClassRepo(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        userList = new ArrayList<>();
        db.execSQL(SQL_CREATE_TABLE_USER);
        db.execSQL(SQL_CREATE_TABLE_CLASS);
        db.execSQL(SQL_CREATE_TABLE_STUDENT);

        generateUsers(db);
        generateClasses(db);
        generateStudents(db);
        updateClassStudentCount(db);
    }

    private void generateStudents(SQLiteDatabase db) {
        if(!isTableEmpty(db, "STUDENT"))
            return;

        ContentValues values = new ContentValues();
        for (int i = 0; i < 15; i++){
            values.clear();
            values.putNull("studentid");
            values.put("name", generateName());
            values.put("dob", "31/12/2003");
            values.put("classid", new Random().nextInt(4) + 1);
            db.insert("STUDENT", null, values);
        }
    }

    public void updateClassStudentCount(SQLiteDatabase db) {
        String queryClass = "SELECT * FROM CLASS";
        Cursor cursorClass = db.rawQuery(queryClass, null);

        // Kiểm tra tồn tại & lấy index của classid
        int idIndex = cursorClass.getColumnIndex("classid");
        if (idIndex < 0){
            Toast.makeText(context, "Column 'name' not found in cursor", Toast.LENGTH_SHORT).show();
            return;
        }

        // Duyệt qua từng CLASS và update số lượng student
        while (cursorClass.moveToNext()) {
            int classId = cursorClass.getInt(idIndex);

            // Tính toán số lượng sinh viên của lớp học
            String queryStudent = "SELECT COUNT(*) FROM STUDENT WHERE classid = ?";
            String[] selectionArgs = { String.valueOf(classId) };
            Cursor cursorStudent = db.rawQuery(queryStudent, selectionArgs);
            int studentCount = 0;
            if (cursorStudent.moveToFirst()) {
                studentCount = cursorStudent.getInt(0);
            }
            cursorStudent.close();

            // Cập nhật số lượng sinh viên của lớp học trong bảng CLASS
            ContentValues values = new ContentValues();
            values.put("students", studentCount);
            String whereClause = "classid = ?";
            String[] whereArgs = { String.valueOf(classId) };
            db.update("CLASS", values, whereClause, whereArgs);
        }
        cursorClass.close();
    }

    private void generateClasses(SQLiteDatabase db) {
        if(!isTableEmpty(db, "CLASS")){
            return;
        }

        ContentValues values = new ContentValues();
        values.putNull("classid");
        values.put("classname", "KTPM2021");
        values.put("students", 0);
        db.insert("CLASS", null, values);

        values.clear();
        values.putNull("classid");
        values.put("classname", "KHMT2021");
        values.put("students", 0);
        db.insert("CLASS", null, values);

        values.clear();
        values.putNull("classid");
        values.put("classname", "KTMT2021");
        values.put("students", 0);
        db.insert("CLASS", null, values);

        values.clear();
        values.putNull("classid");
        values.put("classname", "TMDT2021");
        values.put("students", 0);
        db.insert("CLASS", null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
//        context.deleteDatabase(DATABASE_NAME);
        onCreate(db);
    }

    public void generateUsers(SQLiteDatabase db) {
        if(!isTableEmpty(db, "USER"))
            return;

        userList.add(new User("admin", "121212"));
        userList.add(new User("student", "121212"));

        ContentValues values = new ContentValues();

        for (int i = 0; i < userList.size(); i++) {
            values.clear();
            values.put("username", userList.get(i).getUsername());
            values.put("password", userList.get(i).getPassword());
            db.insert("USER", null, values);
        }
    }
    public User getUserByUsername(String username){
        SQLiteDatabase mDatabase = getReadableDatabase();

        String[] projection = {
                "username",
                "password",
        };

        //query(ten bang, thuoc tinh select, dk ket, truyen data vao dk ket)
        Cursor cursor = mDatabase.query("USER", projection,
                "username = ?", new String[]{username}, null, null, null);

        if(cursor.moveToFirst()){
            String password = cursor.getString(1);
            mDatabase.close();
            cursor.close();
            return new User(username, password);
        }
        mDatabase.close();
        cursor.close();
        return null;
    }

    public boolean isTableEmpty(SQLiteDatabase db, String tableName) {
        String query = "SELECT COUNT(*) FROM " + tableName;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count == 0;
    }

    public String generateName() {
        String[] ho = {"Lê", "Nguyễn", "Trần", "Vũ"};
        String[] tenLot = {"Văn", "Thị", "Đức"};
        String[] ten = {"Tèo", "Tí", "Trường"};
        return
                ho[new Random().nextInt(ho.length)] + " " +
                tenLot[new Random().nextInt(tenLot.length)] + " " +
                ten[new Random().nextInt(ten.length)];
    }
}


