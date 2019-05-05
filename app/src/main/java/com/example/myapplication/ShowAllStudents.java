package com.example.myapplication;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ShowAllStudents extends ListActivity {
    private static final Logger log = Logger.getLogger("MyLog");
    String[] name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getIntent().getExtras();
        name = arguments.getStringArray("arrayStudents");

        initLog();
        showAllStudent();
    }

    private void showAllStudent() {
        try {
            setListAdapter(new ArrayAdapter<String>
                    (this, android.R.layout.simple_list_item_1, name));
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    public static void initLog() {
        try {
            Handler files = new FileHandler("logfile.txt");
            log.addHandler(files);
            files.setFormatter(new SimpleFormatter());
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}
