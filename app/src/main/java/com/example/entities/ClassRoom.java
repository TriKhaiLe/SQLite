package com.example.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "class")
public class ClassRoom {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "classid")
    public int classId;

    @ColumnInfo(name = "classname")
    public String className;

    @ColumnInfo(name = "students")
    public int numberOfStudents;

    public ClassRoom(String className, int numberOfStudents) {
        this.className = className;
        this.numberOfStudents = numberOfStudents;
    }
}
