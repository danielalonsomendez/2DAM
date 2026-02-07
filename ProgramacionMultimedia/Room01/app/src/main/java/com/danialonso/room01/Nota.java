package com.danialonso.room01;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Nota {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String modulo;
    public String calificacion;

    public Nota( String modulo, String calificacion) {
        this.modulo = modulo;
        this.calificacion = calificacion;
    }

    public String getModulo() {
        return modulo;
    }

    public String getCalificacion() {
        return calificacion;
    }


}
