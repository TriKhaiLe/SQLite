package com.example.classmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class ClassRoomActivity extends AppCompatActivity {
    ArrayList<ClassRoom> classList;
    ListView listView;
    ClassRoomAdapter adapter;
    ClassRepo classRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        classList = new ArrayList<>();

        // lay danh sach lop trong DB
        classRepo = ClassRepo.getInstance(this);
        SQLiteDatabase db = classRepo.getReadableDatabase();
        String queryClass = "SELECT * FROM CLASS";
        Cursor cursor;
        cursor = db.rawQuery(queryClass, null);

        while (cursor.moveToNext()) {
            classList.add(new ClassRoom(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2)));
        }
        cursor.close();

        // set adapter
        listView = (ListView) findViewById(R.id.lv_classList);
        adapter = new ClassRoomAdapter(this, R.layout.class_info_layout, classList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
//            Object selectedItem = parent.getItemAtPosition(position);

            // lay du lieu tren view truyen qua Intent
            TextView textView = view.findViewById(R.id.tv_id);
            String classid = textView.getText().toString();

            textView = view.findViewById(R.id.tv_name);
            String classname = textView.getText().toString();

            textView = view.findViewById(R.id.tv_students);
            String students = textView.getText().toString();

            ClassRoom classRoom = new ClassRoom(
                    Integer.parseInt(classid),
                    classname,
                    Integer.parseInt(students));

            Intent intent = new Intent(ClassRoomActivity.this, StudentActivity.class);
            intent.putExtra("selected_class", (Serializable) classRoom);
            startActivity(intent);
        });

    }
}