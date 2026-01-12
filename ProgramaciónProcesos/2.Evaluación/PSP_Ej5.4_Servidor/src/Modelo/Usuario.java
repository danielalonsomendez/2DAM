package Modelo;

public class Usuario {

	private String user;
	private String nombre;
	private String contrasena;
	
	public Usuario(String user, String nombre, String contrasena) {
		super();
		this.user = user;
		this.nombre = nombre;
		this.contrasena = contrasena;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	@Override
	public String toString() {
		return "Usuario [user=" + user + ", nombre=" + nombre + ", contrasena=" + contrasena + "]";
	}
	
	
	
}
