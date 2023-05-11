package com.example.classmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    Button button;
    EditText input1;
    EditText input2;
    ClassRepo classRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        classRepo = ClassRepo.getInstance(this);

        button = findViewById(R.id.button_login);
        button.setOnClickListener(view -> {
            input1 = findViewById(R.id.input1);
            input2 = findViewById(R.id.input2);

            // tim user trong DB bang username nhan vao tu innput1
            User user = classRepo.getUserByUsername(input1.getText().toString());

            // mat khau hop le
            if(user != null && Objects.equals(user.getPassword(), input2.getText().toString())){

                // hien thi ClassesActivity
                Intent intent = new Intent(LoginActivity.this, ClassesActivity.class);
                startActivity(intent);

            }
            else {
                Toast.makeText(this, "Tai khoan hoac mat khau khong hop le!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}