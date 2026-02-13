package com.example.examenpmdmgastos;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Gasto.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract GastoDAO gastoDAO();
    // Método abstracto para obtener el DAO

    private static AppDatabase instance;
    // Variable estática para Singleton

    public static AppDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "gastosdb"
            ).allowMainThreadQueries().build();
        }
        return instance;
    }
}
