package Resumen;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		try {
			ServerSocket servidor = new ServerSocket(4000);
			Socket cliente = servidor.accept();

			DataInputStream dis = new DataInputStream(cliente.getInputStream());
			DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());

			// Pedir frase
			dos.writeUTF("¿Cual es la frase?");
			String frase = dis.readUTF();
			System.out.println("Frase: \n"+frase);
			
			// Pedir funcion resumen	
			dos.writeUTF("¿Cual es la funcion resumen?");
			String funcionResumen = dis.readUTF();
			System.out.println("F.Resumen: \n"+funcionResumen);

			// Generar resumen
			String resumen = Resumen.generarResumen(frase);
			System.out.println("Resumen: \n"+resumen);

			dos.writeUTF((funcionResumen.equals(resumen)) ? "Correcto" : "Incorrecto");

			dos.close();
			dis.close();
			cliente.close();
			servidor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
