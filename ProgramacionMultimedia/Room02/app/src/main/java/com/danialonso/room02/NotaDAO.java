package com.danialonso.room02;

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

    @Query("SELECT * FROM nota WHERE modulo LIKE :texto")
    List<Nota> buscarPorModulo(String texto);

    @Delete
    void borrar(Nota nota);

}
