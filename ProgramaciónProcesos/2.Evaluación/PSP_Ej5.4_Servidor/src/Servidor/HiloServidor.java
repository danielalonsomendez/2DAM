package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import Modelo.Usuario;

public class HiloServidor extends Thread {

	private Socket cliente;
	private ArrayList<Usuario> usuarios;
	private DataOutputStream dos;
	private DataInputStream dis;

	public HiloServidor(Socket cliente, ArrayList<Usuario> usuarios) {
		super();
		this.cliente = cliente;
		this.usuarios = usuarios;
		try {
			dos = new DataOutputStream(cliente.getOutputStream());
			dis = new DataInputStream(cliente.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		int opcion = 0;
		try {
			while (opcion != 3) {
				enviarMensaje("Seleccione una opción:\n1. Registro\n2. Iniciar sesión\n3. Salir");
				opcion = Integer.parseInt(recibirMensaje());
				switch (opcion) {
				case 1:
					registro();
					break;
				case 2:
					iniciarSesion();
					break;
				case 3:
					enviarMensaje("Saliendo...");
					cliente.close();
					break;
				default:
					enviarMensaje("Opción no válida. Intente de nuevo.");
				}
			}
		} catch (Exception e) {
			System.out.println("Conexión cerrada.");
		}

	}

	private void enviarMensaje(String mensaje) throws IOException {
		dos.writeUTF(mensaje);
	}

	private String recibirMensaje() throws IOException {
		return dis.readUTF();
	}

	private String hashearContrasena(String contrasena) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			byte bytes[] = contrasena.getBytes();
			md.update(bytes);

			byte resumenBytes[] = md.digest();
			contrasena = new String(resumenBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return contrasena;
	}

	private void registro() throws IOException {
		enviarMensaje("Introducir username:");
		String username = recibirMensaje();

		enviarMensaje("Introducir nombre:");
		String nombre = recibirMensaje();

		enviarMensaje("Introducir contraseña:");
		String contrasena = recibirMensaje();
		contrasena = hashearContrasena(contrasena);
		Usuario nuevoUsuario = new Usuario(username, nombre, contrasena);
		usuarios.add(nuevoUsuario);
		enviarMensaje("Usuario registrado exitosamente.");
	}
	private void iniciarSesion() throws IOException {
		enviarMensaje("Introducir username:");
		String username = recibirMensaje();

		enviarMensaje("Introducir contraseña:");
		String contrasena = recibirMensaje();
		contrasena = hashearContrasena(contrasena);

		boolean encontrado = false;
		for (Usuario usuario : usuarios) {
			if (usuario.getUser().equals(username) && usuario.getContrasena().equals(contrasena)) {
				enviarMensaje("Bienvenido " + usuario.getNombre() + "!");
				encontrado = true;
				break;
			}
		}
		if (!encontrado) {
			enviarMensaje("Error: Usuario o contraseña incorrectos.");
		}
	}

}
