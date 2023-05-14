package com.example.infrastructure.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dao.ClassRoomDao;
import com.example.dao.UserDao;
import com.example.entities.ClassRoom;
import com.example.entities.User;

@Database(entities = {User.class, ClassRoom.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract ClassRoomDao classRoomDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "ClassManager.db")
                            .fallbackToDestructiveMigration()
                            .addCallback(new SeedDatabaseCallback(INSTANCE.classRoomDao()))
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

