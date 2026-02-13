package modelo;

import java.io.Serializable;

public class Peticion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ID;
	private String clave;
	private String imagen;
	public Peticion(int iD, String clave, String imagen) {
		super();
		ID = iD;
		this.clave = clave;
		this.imagen = imagen;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	@Override
	public String toString() {
		return "Peticion [ID=" + ID + ", clave=" + clave + ", imagen=" + imagen + "]";
	}
	
	
}
