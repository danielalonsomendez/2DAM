package Modelo;

import java.sql.Time;
import java.util.Date;

public class Mensaje {
	private Date fecha;
	private String de;
	private String para;

	private String asunto;
	private String contenido;

	public Mensaje() {
		super();
	}

	public Mensaje(Date fecha, String de, String para, String asunto, String contenido) {
		super();
		this.fecha = fecha;
		this.de = de;
		this.para = para;
		this.asunto = asunto;
		this.contenido = contenido;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	@Override
	public String toString() {
		return "Mensaje [fecha=" + fecha + ", de=" + de + ", para=" + para + ", asunto=" + asunto + ", contenido="
				+ contenido + "]";
	}

}
