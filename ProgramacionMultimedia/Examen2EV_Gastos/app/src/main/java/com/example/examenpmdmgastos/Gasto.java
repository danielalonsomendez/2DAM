package com.example.examenpmdmgastos;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "gastos")
public class Gasto {
    @PrimaryKey(autoGenerate = true)
    private int id;                    // Clave primaria autoincremental

    private String concepto;              // Campo normal
    private double importe;              // Campo normal
    private String categoria;              // Campo normal
    private long fecha;              // Campo normal

    public Gasto(int id, String concepto, double importe, String categoria, long fecha) {
        this.id = id;
        this.concepto = concepto;
        this.importe = importe;
        this.categoria = categoria;
        this.fecha = fecha;
    }
    @Ignore
    public Gasto(String concepto, double importe, String categoria, long fecha) {
        this.concepto = concepto;
        this.importe = importe;
        this.categoria = categoria;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }
}
