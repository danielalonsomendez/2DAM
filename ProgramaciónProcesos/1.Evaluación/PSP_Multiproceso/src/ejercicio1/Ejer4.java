package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejer4 {

	public static void main(String[] args) {
		try {
			ProcessBuilder pb = new ProcessBuilder("tasklist");
			Process proces = pb.start();
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
