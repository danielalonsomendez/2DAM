package com.danialonso.ejerciciorepaso;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SitioDAO {

    @Insert
    void insertar(Sitio sitio);

    @Query("SELECT * FROM sitios")
    List<Sitio> obtenerTodos();

    @Update
    void actualizar(Sitio sitio);

    @Delete
    void eliminar(Sitio sitio);


}
