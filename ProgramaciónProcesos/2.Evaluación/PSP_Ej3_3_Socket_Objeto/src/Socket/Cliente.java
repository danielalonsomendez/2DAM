package Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Modelo.Persona;

public class Cliente {

	public static void main(String[] args) {
		try {
			Socket cliente = new Socket("localhost", 5000);
			System.out.println("Conexión realizada con el servidor");
			
			// Leer servidor
			ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
			Persona p = (Persona) ois.readObject();
			p.setNombre("Carmen");
			p.setFechaNacimiento(1983, 1, 1);
			// Enviar servidor
			ObjectOutputStream  dos= new ObjectOutputStream(cliente.getOutputStream());
			dos.writeObject(p);
			dos.flush();
			cliente.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
