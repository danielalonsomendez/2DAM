package com.example.examenpmdmgastos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GastoDAO {
    @Insert
    void insertar(Gasto entidad);

    @Query("SELECT * from gastos")
    List<Gasto> consultaSelect();

    @Delete
    void eliminar(Gasto entidad);

    @Query("SELECT sum(g.importe) from gastos g")
    double sumatotal();

}
