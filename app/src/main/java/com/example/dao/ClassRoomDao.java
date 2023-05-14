package com.example.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.entities.ClassRoom;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ClassRoomDao {
    @Query("SELECT * FROM class")
    Flowable<List<ClassRoom>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ClassRoom classRoom);
}
