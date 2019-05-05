package com.example.myapplication;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "students")
public class Student {

    private @PrimaryKey(autoGenerate = true) int id;
    private String fio;
    private String timeAdd;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getTimeAdd() {
        return timeAdd;
    }

    public void setTimeAdd(String timeAdd) {
        this.timeAdd = timeAdd;
    }

    @Override
    public String toString() {
        return "Student: " +
                "id = " + id +
                ", fio = " + fio +
                ", timeAdd = " + timeAdd;
    }
}

