package com.example.classmanager;

public class Student {
    int studentId;
    String name;
    String dob;
    int classId;

    public Student(int studentId, String name, String dob, int classId) {
        this.studentId = studentId;
        this.name = name;
        this.dob = dob;
        this.classId = classId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
