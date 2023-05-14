package com.example.classmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {
    ArrayList<Student> studentList;
    ListView listView;
    StudentAdapter adapter;
    ClassRepo classRepo;
    TextView tvId;
    TextView tvName;
    TextView tvStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        studentList = new ArrayList<>();

        Intent intent = getIntent();
        ClassRoom classRoom = (ClassRoom) intent.getSerializableExtra("selected_class");

        tvId = (TextView) findViewById(R.id.tv_id);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvStudents = (TextView) findViewById(R.id.tv_students);

        tvId.setText(String.valueOf(classRoom.getClassId()));
        tvName.setText(classRoom.getClassName());
        tvStudents.setText(String.valueOf(classRoom.getStudents()));

        // lay danh sach student trong DB
        classRepo = ClassRepo.getInstance(this);
        SQLiteDatabase db = classRepo.getReadableDatabase();
        String query = "SELECT * FROM STUDENT WHERE classid = ?";
        String[] selectionArgs = { String.valueOf(classRoom.classId) };

        Cursor cursor = db.rawQuery(query, selectionArgs);
        while (cursor.moveToNext()) {
            studentList.add(new Student(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3)));
        }
        cursor.close();

        // set adapter
        listView = (ListView) findViewById(R.id.lv_studentList);
        adapter = new StudentAdapter(this, R.layout.student_info_layout, studentList);
        listView.setAdapter(adapter);
    }
}