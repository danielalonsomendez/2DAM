package Modelo;

import java.io.Serializable;

public class Datos implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cadena;
	private int intentos;
	private int identificador;
	private boolean gana;
	private boolean juega;
	public Datos(String cadena, int intentos, int identificador, boolean gana, boolean juega) {
		super();
		this.cadena = cadena;
		this.intentos = intentos;
		this.identificador = identificador;
		this.gana = gana;
		this.juega = juega;
	}
	public String getCadena() {
		return cadena;
	}
	public void setCadena(String cadena) {
		this.cadena = cadena;
	}
	public int getIntentos() {
		return intentos;
	}
	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public boolean isGana() {
		return gana;
	}
	public void setGana(boolean gana) {
		this.gana = gana;
	}
	public boolean isJuega() {
		return juega;
	}
	public void setJuega(boolean juega) {
		this.juega = juega;
	}
	@Override
	public String toString() {
		return "Datos [cadena=" + cadena + ", intentos=" + intentos + ", identificador=" + identificador + ", gana="
				+ gana + ", juega=" + juega + "]";
	}
	
	
}
