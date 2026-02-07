package com.danialonso.ejerciciorepaso;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Sitio.class}, version = 1) public abstract class AppDatabase extends RoomDatabase {

    public abstract SitioDAO sitioDAO();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "sitios_repaso"
            ).allowMainThreadQueries().build();
        }
        return instance;
    }
}