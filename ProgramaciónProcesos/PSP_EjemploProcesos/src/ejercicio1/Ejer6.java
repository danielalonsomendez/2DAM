package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;

public class Ejer6 {
	public static void main(String[] args) {
		try {
			ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "hola.bat");
			pb.redirectOutput(new File("salida_ejer6.txt"));
			pb.redirectError(new File("errores_ejer6.txt"));
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
