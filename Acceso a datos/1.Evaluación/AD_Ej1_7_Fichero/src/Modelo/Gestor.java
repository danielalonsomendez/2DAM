package Modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Gestor {

	public ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();

	public void leerArchivo() throws FileNotFoundException, IOException, ParseException {
		mensajes = new ArrayList<Mensaje>();

		BufferedReader fichero = new BufferedReader(new FileReader("archivos/mensajes.txt"));
		String linea;
		Mensaje mensaje = new Mensaje();
		while ((linea = fichero.readLine()) != null) {
			if (linea.equals("")) {
				linea = fichero.readLine(); // Saltar linea espacio
			}
			if (linea.equals("******************")) {
				mensajes.add(mensaje);
				mensaje = new Mensaje(); // Vaciar objeto mensaje
				linea = fichero.readLine(); // Saltar linea espacio
				if (linea == null) {
					break;
				}
			}
			if (linea.startsWith("fecha")) {
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // Formato fecha+hora+minuto
				String fechatxt = linea.split("fecha:")[1];
				linea = fichero.readLine(); // Saltar linea espacio
				linea = fichero.readLine(); // Llegar a la linea de la hora
				String horatxt = linea.split("hora:")[1];
				mensaje.setFecha(format.parse(fechatxt + " " + horatxt));
			} else if (linea.startsWith("de")) {
				mensaje.setDe(linea.split("de:")[1]);
			} else if (linea.startsWith("para")) {
				mensaje.setPara(linea.split("para:")[1]);
			} else if (linea.startsWith("asunto")) {
				mensaje.setAsunto(linea.split("asunto:")[1]);
			} else if (linea.startsWith("contenido")) {
				mensaje.setContenido(linea.split("contenido:")[1]);
			}

		}
		fichero.close();
	}

	public void guardarArchivo() throws IOException {
		FileWriter fichero = new FileWriter("archivos/mensajes.txt");
		PrintWriter pw = new PrintWriter(fichero);

		DateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd"); // Formato fecha
		DateFormat formatHora = new SimpleDateFormat("HH:mm"); // Formato hora

		for (int i = 0; i < mensajes.size(); i++) {
			Mensaje mensaje = mensajes.get(i);
			pw.println(String.format(
					"fecha:%s\n\nhora:%s\n\npara:%s\n\nde:%s\n\nasunto:%s\n\ncontenido:%s\n\n******************\n",
					formatFecha.format(mensaje.getFecha()), formatHora.format(mensaje.getFecha()), mensaje.getPara(),
					mensaje.getDe(), mensaje.getAsunto(), mensaje.getContenido()));

		}
		fichero.close();

	}

	public void añadirMensaje(int dia, String mes, int año, int hora, int minuto, String de, String para, String asunto,
			String contenido) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MMMM-dd HH:mm", Locale.forLanguageTag("es-ES")); // Formato
																										// fecha+hora+minuto
		Date fechaCompleta = format.parse(String.format("%04d-%s-%02d %02d:%02d", año, mes, dia, hora, minuto));
		mensajes.add(new Mensaje(fechaCompleta, de, para, asunto, contenido));
	}

}
