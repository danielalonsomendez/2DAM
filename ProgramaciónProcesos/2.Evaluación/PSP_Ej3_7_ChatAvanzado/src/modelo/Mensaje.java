package modelo;

import java.io.Serializable;

public class Mensaje implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String contenido;
	private String nick;

	public Mensaje(String contenido, String nick) {
		super();
		this.contenido = contenido;
		this.nick = nick;
	}

	public static Mensaje servidor(String contenido) {
		return new Mensaje(contenido, "sistema");
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@Override
	public String toString() {
		if (nick.equals("sistema")) {
			return contenido;
		}
		return nick + ": " + contenido;
	}

}
