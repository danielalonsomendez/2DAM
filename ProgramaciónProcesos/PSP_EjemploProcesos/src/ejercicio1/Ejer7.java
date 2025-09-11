package ejercicio1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Ejer7 {
	public static void main(String[] args) {
		System.out.print("Escribe algo: ");
		Scanner teclado = new Scanner(System.in);
		String texto = teclado.nextLine();
		teclado.close();
		try {
			ProcessBuilder pb = new ProcessBuilder(
				"java", "EjemploLectura.EjemploLectura"
			);
			pb.directory(new File("bin"));
			Process proces = pb.start();
			proces.getOutputStream().write((texto+"\n").getBytes());
			proces.getOutputStream().flush();

			BufferedReader reader = new BufferedReader(new InputStreamReader(proces.getInputStream()));
			String linea;
			while ((linea = reader.readLine()) != null) {
				System.out.println(linea);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}