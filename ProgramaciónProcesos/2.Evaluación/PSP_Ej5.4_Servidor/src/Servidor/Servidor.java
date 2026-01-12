package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import Modelo.Usuario;

public class Servidor {

	public static void main(String[] args) {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>(); // El ArrayList será de usuarios (clase Usuario)
		// Se usará el algoritmo SHA
		usuarios.add(new Usuario("XXXX", "YYYY", "���D*ƅ�A���M=2�{�5D/i��N")); // password1
		usuarios.add(new Usuario("XXXX", "YYYY", ":�Y0n��`σ-4���؍ao�©��-�o�g"));// pass3

		try (ServerSocket serverSocket = new ServerSocket(5000);) {
			System.out.println("Servidor iniciado.");
			while (true) {
				try {
					new HiloServidor(serverSocket.accept(), usuarios).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();

		}

	}
}
