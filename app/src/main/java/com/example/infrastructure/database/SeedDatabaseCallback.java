package com.example.infrastructure.database;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.dao.ClassRoomDao;
import com.example.entities.ClassRoom;

import java.util.concurrent.Executors;

public class SeedDatabaseCallback extends RoomDatabase.Callback {

    private final ClassRoomDao classRoomDao;

    public SeedDatabaseCallback(ClassRoomDao classRoomDao) {
        this.classRoomDao = classRoomDao;
    }

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);
        Executors.newSingleThreadExecutor().execute(() -> {
            classRoomDao.insert(new ClassRoom("English", 1));
            classRoomDao.insert(new ClassRoom("Mathematics", 2));
            classRoomDao.insert(new ClassRoom("Science", 3));
        });
    }
}

