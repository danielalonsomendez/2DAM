package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Peticion;
import modelo.Resumen;

public class Servidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Peticion> peticiones = new ArrayList<>();
		Scanner sc = new Scanner(System.in);

		String res = "";
		do {
			System.out.println("¿Desea dar de alta una peticion? S/N");
			res = sc.nextLine();
			if (res.equalsIgnoreCase("S")) {

				System.out.println("ID: ");
				int iD = sc.nextInt();
				sc.nextLine();

				System.out.println("Clave: ");
				String clave = sc.nextLine();
				clave = Resumen.generarResumen(clave);

				System.out.println("URL: ");
				String url = sc.nextLine();
				peticiones.add(new Peticion(iD, clave, url));
			}

		} while (!res.equalsIgnoreCase("N"));

		sc.close();
		try (ServerSocket serverSocket = new ServerSocket(5000)) {
			System.out.println("Servidor iniciado");
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					ObjectOutputStream fsalida = new ObjectOutputStream(socket.getOutputStream());
					fsalida.flush();
					ObjectInputStream fentrada = new ObjectInputStream(socket.getInputStream());

					DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
					DataInputStream entrada = new DataInputStream(socket.getInputStream());

					HiloServidor hilo = new HiloServidor(fentrada, fsalida, salida, entrada, peticiones);
					hilo.start();
				} catch (SocketException e) {
					System.out.println("Cliente desconectado");
				} catch (Exception e) {

					e.printStackTrace();

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Servidor iniciado...");

	}

}