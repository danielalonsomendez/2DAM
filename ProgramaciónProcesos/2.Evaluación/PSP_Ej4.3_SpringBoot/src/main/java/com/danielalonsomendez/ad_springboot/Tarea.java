package com.danielalonsomendez.ad_springboot;

import java.io.Serializable;

public class Tarea implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	private boolean completada;
	public Tarea() {
	}
	public Tarea(int id, String nombre, boolean completada) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.completada = completada;
	}

	public Tarea(String nombre, boolean completada) {
		super();
		this.nombre = nombre;
		this.completada = completada;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isCompletada() {
		return completada;
	}

	public void setCompletada(boolean completada) {
		this.completada = completada;
	}

	@Override
	public String toString() {
		return "Tarea [id=" + id + ", nombre=" + nombre + ", completada=" + completada + "]";
	}

}
