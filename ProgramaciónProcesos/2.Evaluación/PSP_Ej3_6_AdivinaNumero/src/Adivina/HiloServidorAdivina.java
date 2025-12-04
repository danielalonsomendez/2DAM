package Adivina;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Modelo.Datos;
import Modelo.ObjetoCompartido;

public class HiloServidorAdivina extends Thread {
	private Socket socket;
	private ObjetoCompartido compartido;
	private int id;
	
	public HiloServidorAdivina(Socket accept, ObjetoCompartido compartido, int id) {
		this.socket = accept;
		this.compartido = compartido;
		this.id = id;
	}
	
	@Override
	public void run() {

		try  {
			ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
			Datos d = new Datos("Adivina un NÚMERO ENTRE 1 Y 25", 5, id, false, true);
			salida.writeObject(d);

			int intentos = 5;

			while (intentos > 0 && !compartido.seAcabo()) {
				Datos recibido = (Datos) entrada.readObject();
				int numeroJugador = Integer.parseInt(recibido.getCadena());
				String msg = compartido.nuevaJugada(id, numeroJugador);
				intentos--;

				boolean gana = compartido.getGanador() == id;
				boolean fin = compartido.seAcabo() || intentos == 0;

				Datos respuesta = new Datos(
					msg,
					intentos,
					id,
					gana,
					!fin
				);

				salida.writeObject(respuesta);

				if (fin) break;

			}
			System.out.println("Ya no hay nada mas que leer del jugador: " + id);
			if (compartido.seAcabo() && compartido.getGanador() != id) {
				Datos fin = new Datos(
					"LO SENTIMOS, EL JUEGO HA TERMINADO, HAN ADIVINADO EL No " + compartido.getGanador(),
					0, id, false, false
				);
				salida.writeObject(fin);
			} else {
				System.out.println("EL JUEGO SE HA ACABADO...");
			}
			System.out.println("	=> Desconecta: " + id);

		} catch (Exception e) {
			try {
				ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
				salida.writeObject(new Datos("Error en el servidor: " + e.getMessage(), 0, id, false, false));
			} catch (Exception ignored) {}
		}
	}
	

}
