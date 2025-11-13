package Precios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import Modelo.Libro;

public class GuardarPrecio {

	public static void main(String[] args) {
		ArrayList<Libro> libros = Libro.obtenerLibros();
			RandomAccessFile file = null;
			try {
				file = new RandomAccessFile(new File("Precios.dat"),"rw");
				file.seek(0);
				for (int i = 0; i < libros.size(); i++) {
					Libro libro = libros.get(i);
					file.writeUTF(libro.getTitle());
					file.writeFloat(Float.parseFloat(libro.getPrice()+""));
				}
			} catch (FileNotFoundException e) {
				System.out.println("Error al encontrar el fichero");
			} catch (IOException e) {
				System.out.println("Error al crear el fichero");
			} finally {
				try {
					file.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar el fichero");
				}
			}
		
	}
}
