package com.danialonso.room03;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Nota.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract NotaDAO notaDao();

}
