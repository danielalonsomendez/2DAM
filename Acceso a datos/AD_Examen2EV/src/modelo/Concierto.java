package modelo;

import java.util.Date;

public class Concierto {
	
	public int id;
	public String artista;
	public Date fecha;
	public double precio_entrada;
	public Concierto(int id, String artista, Date fecha, double precio_entrada) {
		super();
		this.id = id;
		this.artista = artista;
		this.fecha = fecha;
		this.precio_entrada = precio_entrada;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getArtista() {
		return artista;
	}
	public void setArtista(String artista) {
		this.artista = artista;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public double getPrecio_entrada() {
		return precio_entrada;
	}
	public void setPrecio_entrada(double precio_entrada) {
		this.precio_entrada = precio_entrada;
	}
	@Override
	public String toString() {
		return "Concierto [id=" + id + ", artista=" + artista + ", fecha=" + fecha + ", precio_entrada="
				+ precio_entrada + "]";
	}
	
	

}
