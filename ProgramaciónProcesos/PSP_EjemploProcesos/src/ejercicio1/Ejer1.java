package ejercicio1;

import java.io.IOException;

public class Ejer1 {

	public static void main(String[] args) {
		try {
			ProcessBuilder pb = new ProcessBuilder("notepad");
			pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
