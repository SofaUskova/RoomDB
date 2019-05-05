package com.example.myapplication;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Student.class}, version = 3)
public abstract class MyAppDatabase extends RoomDatabase {
    public abstract DaoStudent daoStudent();

    /*public static final Migration MIGRATION_1_2 = new Migration(2, 3) {
        @Override
        public void migrate(final SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE students MODIFY COLUMN id INTEGER AUTO_INCREMENT PRIMARY KEY;");
        }
    };*/
}


