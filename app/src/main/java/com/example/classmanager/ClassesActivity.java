package com.example.classmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dao.UserDao;
import com.example.entities.User;
import com.example.repositories.ClassRoomRepository;
import com.example.repositories.UserRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.Flowable;
import io.reactivex.Single;

@AndroidEntryPoint
public class ClassesActivity extends AppCompatActivity {
    ArrayList<ClassRoom> classList;
    ListView listView;
    CLassesAdapter adapter;
    ClassRepo classRepo;

    @Inject
    UserRepository userRepository;

    @Inject
    ClassRoomRepository classRoomRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        classList = new ArrayList<>();

        userRepository.getAllUsers()
                .subscribe(result -> {
                    Log.i("abc",String.valueOf(result.size()));
                }, throwable -> {});


        classRoomRepository.getAllClassRoom().subscribe(result -> {

        }, throwable -> {});
        
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
        adapter = new CLassesAdapter(this, R.layout.class_info_layout, classList);
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

            ClassRoom classRoom = new ClassRoom(Integer.parseInt(classid), classname, Integer.parseInt(students));

            Intent intent = new Intent(ClassesActivity.this, StudentsActivity.class);
            intent.putExtra("selected_class", (Serializable) classRoom);
            startActivity(intent);
        });

    }
}