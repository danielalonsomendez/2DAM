package Controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import Modelo.Gestor;
import Modelo.Mensaje;

public class Controlador {

	private Gestor gestor = new Gestor();

	public ArrayList<Mensaje> todoslosMensajes() {
		return gestor.mensajes;
	}

	public void leerArchivo() throws FileNotFoundException, IOException, ParseException {
		gestor.leerArchivo();
	}

	public void guardarArchivo() throws IOException {
		gestor.guardarArchivo();
	}

	public void añadirMensaje(int dia, String mes, int año, int hora, int minuto, String de, String para, String asunto,
			String contenido) throws ParseException {
		gestor.añadirMensaje(dia, mes, año, hora, minuto, de, para, asunto, contenido);

	}
}
