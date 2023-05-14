package com.example.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.entities.User;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    Flowable<List<User>> getAll();
}
