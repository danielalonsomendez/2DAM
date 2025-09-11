package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejer3 {
	public static void main(String[] args) {
		try {
			ProcessBuilder pb = new ProcessBuilder("cmd","/c","ipconfig/all");
			Process proces = pb.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(proces.getInputStream()));
			String linea = null;
			while ((linea = reader.readLine()) != null) {
				if(linea.contains("Direcci�n f�sica") ) {
					System.out.println(linea.substring(50));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
