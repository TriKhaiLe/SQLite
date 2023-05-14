package com.example.infrastructure.di_modules;

import android.content.Context;

import androidx.room.Room;

import com.example.dao.ClassRoomDao;
import com.example.dao.UserDao;
import com.example.entities.ClassRoom;
import com.example.infrastructure.database.AppDatabase;
import com.example.infrastructure.database.Migration3to1;
import com.example.repositories.ClassRoomRepository;
import com.example.repositories.UserRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

@Module
@InstallIn(ActivityComponent.class)
public abstract class AppDatabaseModule {
    @Provides
    public static AppDatabase provideMyDatabase(@ApplicationContext Context context) {
        return AppDatabase.getInstance(context);
    }

    @Provides
    public static UserDao provideUserDao(AppDatabase database) {
        return database.userDao();
    }

    @Provides
    public static ClassRoomDao provideClassRoomDao(AppDatabase database) {
        return database.classRoomDao();
    }

    @Provides
    public static UserRepository provideUserRepo(UserDao userDao) {
        return new UserRepository(userDao);
    }

    @Provides
    public static ClassRoomRepository provideClassRoomRepo(ClassRoomDao classRoomDao) {
        return new ClassRoomRepository(classRoomDao);
    }
}
