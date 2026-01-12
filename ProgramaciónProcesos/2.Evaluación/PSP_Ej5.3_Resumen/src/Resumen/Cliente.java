package Resumen;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		try {
			Socket cliente = new Socket("localhost", 4000);
			DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
			DataInputStream dis = new DataInputStream(cliente.getInputStream());

			// Recibir pregunta frase
			String mensaje = dis.readUTF();
			System.out.println(mensaje);
			
			// Enviar frase
			String frase = teclado.nextLine();
			dos.writeUTF(frase);

			// Recibir pregunta funcion resumen
			String mensaje2 = dis.readUTF();
			System.out.println(mensaje2);
			
			// Enviar funcion resumen
			String resumen = Resumen.generarResumen(frase);
			System.out.println(resumen);
			dos.writeUTF(resumen);
			
			// Recibir resultado
			String resultado = dis.readUTF();
			System.out.println("Resultado: \n"+resultado);

			dos.close();
			dis.close();
			cliente.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		teclado.close();

	}

}
