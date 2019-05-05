package com.example.myapplication;

import android.annotation.TargetApi;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private static final Logger log = Logger.getLogger("MyLog");
    private static MyAppDatabase myAppDatabase;
    private EditText name;
    private EditText timeAdd;
    private EditText findID;
    private EditText findIDUpdate;
    private EditText updateName;
    private EditText updateTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAppDatabase = Room.databaseBuilder(this,
                MyAppDatabase.class, "students_database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        initView();
    }

    private void initView() {
        name = findViewById(R.id.name);
        timeAdd = findViewById(R.id.timeAdd);
        findID = findViewById(R.id.findId);
        findIDUpdate = findViewById(R.id.findIdUpdate);
        updateName = findViewById(R.id.updateName);
        updateTime = findViewById(R.id.updateTime);
    }

    public void addStudent(View view) {
        try {
            Student student = new Student();
            String studentName = name.getText().toString();
            String studentTimeAdd = timeAdd.getText().toString();
            student.setFio(studentName);
            student.setTimeAdd(studentTimeAdd);

            myAppDatabase.daoStudent().insert(student);

            name.setText("");
            timeAdd.setText("");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    public void deleteStudent(View view) {
        try {
            int studentFindID = Integer.parseInt(findID.getText().toString());

            List<Student> allStudentConvert = myAppDatabase.daoStudent().getAllStudents();
            for (Student student : allStudentConvert) {
                if (student.getId() == studentFindID) {
                    myAppDatabase.daoStudent().delete(student);
                }
            }
            findID.setText("");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    public void updateStudent(View view) {
        try {
            int studentFindID = Integer.parseInt(findIDUpdate.getText().toString());
            String newName = updateName.getText().toString();
            String newTime = updateTime.getText().toString();

            List<Student> allStudentConvert = myAppDatabase.daoStudent().getAllStudents();
            for (Student student : allStudentConvert) {
                if (student.getId() == studentFindID) {
                    student.setFio(newName);
                    student.setTimeAdd(newTime);
                    myAppDatabase.daoStudent().update(student);
                }
            }
            findIDUpdate.setText("");
            updateName.setText("");
            updateTime.setText("");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    public void deleteStudentAll(View view) {
        try{
            myAppDatabase.daoStudent().deleteAll();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    public void showAllStudent(View view) {
        try {
            List<Student> allStudentConvert = myAppDatabase.daoStudent().getAllStudents();
            List<String> strings = allStudentConvert.stream()
                    .map(object -> Objects.toString(object, null))
                    .collect(Collectors.toList());

            String[] allStudents = new String[strings.size()];
            allStudents = strings.toArray(allStudents);

            Intent intent = new Intent(this, ShowAllStudents.class);
            intent.putExtra("arrayStudents", allStudents); //передаем стринговый массив объектов
            startActivity(intent);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

}
