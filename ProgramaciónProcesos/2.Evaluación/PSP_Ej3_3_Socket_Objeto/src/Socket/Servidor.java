package Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Modelo.Persona;

public class Servidor {

	public static void main(String[] args) {
		try {
			ServerSocket servidor = new ServerSocket(5000);
				Socket cliente = servidor.accept();
				
				// Enviar cliente
				ObjectOutputStream  dos= new ObjectOutputStream(cliente.getOutputStream());
				Persona p = new Persona();
				p.setNif("16078123X");
				p.setNombre("Pepito");
				p.setApellido("Grillo");
				p.setFechaNacimiento(1990, 12, 12);
				p.setGenero('M');
				dos.writeObject(p);
				dos.flush();
				
				// Leer cliente
				ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
				Persona pCambiado = (Persona) ois.readObject();
				System.out.println(pCambiado);
				
				cliente.close();
			servidor.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
