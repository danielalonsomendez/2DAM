package BuscarPorCategoria;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Modelo.Libro;

public class BuscarPorCategoria {

	public static void main(String[] args) {
		ArrayList<Libro> libros = Libro.obtenerLibrosWeb();
		try {
			FileWriter fichero;

			fichero = new FileWriter("Web.txt");

			PrintWriter pw = new PrintWriter(fichero);
			pw.println(
					"===========================================\n\nISBN	TITLE	YEAR\n\n------------------------------------------");
			for (int i = 0; i < libros.size(); i++) {
				Libro libro = libros.get(i);
				pw.println(String.format("%s	%s	%s\n", libro.getIsbn(), libro.getTitle(), libro.getYear()));
			}
			pw.print("--------------\n\nTOTAL: " + libros.size() + " libros");
			fichero.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
