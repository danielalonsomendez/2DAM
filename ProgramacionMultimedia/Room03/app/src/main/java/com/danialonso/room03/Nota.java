package com.danialonso.room03;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Nota {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String modulo;
    public double calificacion;

    public Nota( String modulo, double calificacion) {
        this.modulo = modulo;
        this.calificacion = calificacion;
    }

    public String getModulo() {
        return modulo;
    }

    public double getCalificacion() {
        return calificacion;
    }


}
