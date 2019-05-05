package com.example.myapplication;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DaoStudent {

    @Insert
    public void insert(Student student);

    @Delete
    public void delete(Student student);

    @Query("DELETE FROM students")
    public void deleteAll();

    @Update
    public void update(Student student);

    @Query("SELECT * FROM students")
    public List<Student> getAllStudents();

    @Query("SELECT * FROM students WHERE id=:id")
    public Student getById(int id);
}

