package com.danialonso.room01;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Nota.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract NotaDAO notaDao();

}
