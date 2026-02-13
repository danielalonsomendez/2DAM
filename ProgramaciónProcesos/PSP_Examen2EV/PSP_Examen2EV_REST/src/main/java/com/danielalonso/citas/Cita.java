package com.danielalonso.citas;

import java.io.Serializable;
import java.util.Date;

public class Cita implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	private String estado;
	private Date fecha;

	public Cita(String nombre, String estado, Date fecha) {
		super();
		this.nombre = nombre;
		this.estado = estado;
		this.fecha = fecha;
	}

	public Cita() {
		super();
	}

	public Cita(int id, String nombre, String estado, Date fecha) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
		this.fecha = fecha;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Cita [id=" + id + ", nombre=" + nombre + ", estado=" + estado + ", fecha=" + fecha + "]";
	}

}
