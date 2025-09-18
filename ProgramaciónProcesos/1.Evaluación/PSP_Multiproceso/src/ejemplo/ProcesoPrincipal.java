package ejemplo;

import java.io.IOException;
import java.io.File;
public class ProcesoPrincipal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// Process Builder
			ProcessBuilder pb = new ProcessBuilder("cmd","/C" ,"tasklist");
			pb.redirectOutput(new File("salida.txt"));
			pb.redirectError(new File("errores.txt"));
			Process proces = pb.start();
			long id = proces.pid();
			System.out.println("ID: " + id);
			
			// Runtime
			//Process rn = Runtime.getRuntime().exec("notepad");
			//int valorSalida = rn.waitFor();
			//System.out.println("Valor: " + valorSalida);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		System.exit(103);

	}

}
