package Modelo;

import java.io.Serializable;
import java.util.Date;

public class Partido implements Serializable {
	private static final long serialVersionUID = 1L;
	String equipoLocal;
	String equipoVisitante;
	int golesLocal;
	int golesVisitante;
	String lugar;
	Date fecha;
	
	public Partido(String equipoLocal, String equipoVisitante, int golesLocal, int golesVisitante, String lugar,
			Date fecha) {
		super();
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.golesLocal = golesLocal;
		this.golesVisitante = golesVisitante;
		this.lugar = lugar;
		this.fecha = fecha;
	}
	public String getEquipoLocal() {
		return equipoLocal;
	}
	public void setEquipoLocal(String equipoLocal) {
		this.equipoLocal = equipoLocal;
	}
	public String getEquipoVisitante() {
		return equipoVisitante;
	}
	public void setEquipoVisitante(String equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}
	public int getGolesLocal() {
		return golesLocal;
	}
	public void setGolesLocal(int golesLocal) {
		this.golesLocal = golesLocal;
	}
	public int getGolesVisitante() {
		return golesVisitante;
	}
	public void setGolesVisitante(int golesVisitante) {
		this.golesVisitante = golesVisitante;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@Override
	public String toString() {
		return "Partido [equipoLocal=" + equipoLocal + ", equipoVisitante=" + equipoVisitante + ", golesLocal="
				+ golesLocal + ", golesVisitante=" + golesVisitante + ", lugar=" + lugar + ", fecha=" + fecha + "]";
	}
	
}
