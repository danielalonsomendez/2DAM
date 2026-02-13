package servidor;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import modelo.Peticion;

public class HiloServidor extends Thread {

	private ObjectInputStream fentrada;
	private ObjectOutputStream fsalida;

	private DataInputStream entrada;
	private DataOutputStream salida;
	private ArrayList<Peticion> peticiones;

	public HiloServidor(ObjectInputStream fentrada, ObjectOutputStream fsalida, DataOutputStream salida,
			DataInputStream entrada, ArrayList<Peticion> peticiones) {

		this.fentrada = fentrada;
		this.fsalida = fsalida;
		this.entrada = entrada;
		this.salida = salida;
		this.peticiones = peticiones;
	}

	@Override
	public void run() {

		try {
			salida.writeUTF("¿Que imagen quiere obtener? 0 para salir");
			int id = entrada.readInt();
			if (id == 0) {
				cerrar();
			} else {
				int peticion = buscarPeticionPorId(id);
				if (peticion == -1) {
					cerrar();
				} else {
					salida.writeUTF("¿Cual es la contraseña?");
					String contra = entrada.readUTF();
					if (validarPeticion(peticiones.get(peticion), contra)) {
						salida.writeUTF("CORRECTO");
						fsalida.writeObject(peticiones.get(peticion));

						Peticion peticionNueva = (Peticion) fentrada.readObject();

						File fich = new File(peticionNueva.getImagen());
						BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(fich));

						byte[] buffer = new byte[4096];
						int bytesRead;

						while ((bytesRead = fileInput.read(buffer)) != -1) {
							salida.write(buffer, 0, bytesRead);
						}
						fileInput.close();

					} else {
						cerrar();
					}

				}
			}
			fentrada.close();
			fsalida.close();
			entrada.close();
			salida.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private int buscarPeticionPorId(int id) {
		for (var i = 0; i < peticiones.size(); i++) {
			if (peticiones.get(i).getID() == id) {
				return i;
			}
		}
		return -1;
	}

	private boolean validarPeticion(Peticion peticion, String contra) {
		if (peticion.getClave().equals(contra)) {
			return true;
		}

		return false;
	}

	private void cerrar() {
		try {
			salida.writeUTF("*");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
