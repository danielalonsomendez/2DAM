package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteUsuario {

	private static final String HOST = "localhost";
	private static final int PUERTO = 5000;

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);

		try {
			Socket cliente = new Socket(HOST, PUERTO);
			DataInputStream dis = new DataInputStream(cliente.getInputStream());
			DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
			boolean salir = false;

			while (!salir) {
				String mensajeServidor = dis.readUTF();
				System.out.println(mensajeServidor);

				String opcion = teclado.nextLine();
				dos.writeUTF(opcion);

				switch (opcion) {
				case "1":
					for (int i = 0; i < 3; i++) {
						System.out.println(dis.readUTF());
						String respuesta = teclado.nextLine();
						dos.writeUTF(respuesta);
					}
					System.out.println(dis.readUTF());
					break;
				case "2":
					for (int i = 0; i < 2; i++) {
						System.out.println(dis.readUTF());
						String respuesta = teclado.nextLine();
						dos.writeUTF(respuesta);
					}
					System.out.println(dis.readUTF());
					break;
				case "3":
					System.out.println(dis.readUTF());
					salir = true;
					break;
				default:
					System.out.println(dis.readUTF());
					break;
				}
			}

			cliente.close();
			dis.close();
			dos.close();
		} catch (IOException e) {
			System.out.println("No se pudo conectar con el servidor.");
		}
		teclado.close();
	}
}
