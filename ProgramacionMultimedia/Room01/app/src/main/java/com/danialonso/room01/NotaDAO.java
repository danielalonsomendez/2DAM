package com.danialonso.room01;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotaDAO {

    @Insert
    void insertar(Nota nota);

    @Query("SELECT * FROM nota")
    List<Nota> obtenerTodas();

    @Delete
    void borrar(Nota nota);

}
