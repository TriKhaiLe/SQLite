package com.example.classmanager;

import java.io.Serializable;

public class ClassRoom implements Serializable {
    int classId;
    String className;
    int students;

    public ClassRoom(int classId, String className, int students) {
        this.classId = classId;
        this.className = className;
        this.students = students;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getStudents() {
        return students;
    }

    public void setStudents(int students) {
        this.students = students;
    }
}
