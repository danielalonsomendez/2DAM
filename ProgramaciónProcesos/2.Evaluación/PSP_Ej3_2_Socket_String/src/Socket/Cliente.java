package Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) {
		try {
			Socket cliente = new Socket("localhost", 5000);
			System.out.println("Conexión realizada con el servidor");
			
			DataOutputStream dos= new DataOutputStream(cliente.getOutputStream());
			dos.writeUTF("Hola servidor, soy un cliente");
			
			DataInputStream dis = new DataInputStream(cliente.getInputStream());
			String mensaje = dis.readUTF();
			System.out.println(mensaje);
			cliente.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
