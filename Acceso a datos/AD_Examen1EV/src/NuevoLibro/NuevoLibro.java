package NuevoLibro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Modelo.Libro;

public class NuevoLibro {
	public static void main(String[] args) {
		BufferedReader fichero;
		try {
			fichero = new BufferedReader(new FileReader("NuevaFicha.txt"));

			String linea;
			Libro libro = new Libro();

			while ((linea = fichero.readLine()) != null) {
				if (linea.startsWith("date:")) {
					DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
					String fechatxt = linea.split("date:")[1];
					libro.setDate(format.parse(fechatxt));
				} else if (linea.startsWith("category:")) {
					libro.setCategory(linea.split("category:")[1]);
				} else if (linea.startsWith("isbn:")) {
					libro.setIsbn(linea.split("isbn:")[1]);
				} else if (linea.startsWith("title:")) {
					libro.setTitle(linea.split("title:")[1]);
				} else if (linea.startsWith("lang:")) {
					libro.setLang(linea.split("lang:")[1]);
				} else if (linea.startsWith("author:")) {
					libro.setAuthor(linea.split("author:")[1]);
				} else if (linea.startsWith("year:")) {
					libro.setYear(Integer.parseInt(linea.split("year:")[1]));
				} else if (linea.startsWith("price:")) {
					libro.setPrice(Double.parseDouble(linea.split("price:")[1]));
				}
			}
			
			libro.anadir();
			System.out.println("Libro añadido a la base de datos");
			fichero.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
