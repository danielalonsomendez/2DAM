package Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		try {
			ServerSocket servidor = new ServerSocket(5000);
			for(int i=0;i<3;i++) {
				Socket cliente = servidor.accept();
				// Leer cliente
				DataInputStream dis = new DataInputStream(cliente.getInputStream());
				String mensaje = dis.readUTF();
				System.out.println("Recibido: " + mensaje);
				
				// Enviar cliente
				DataOutputStream dos= new DataOutputStream(cliente.getOutputStream());
				dos.writeUTF("saludos desde el servidor al cliente no: "+ (i+1));
			}
	
			servidor.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
