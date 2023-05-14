package com.example.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "username")
    public String userName;

    @ColumnInfo(name = "password")
    public String password;
}
