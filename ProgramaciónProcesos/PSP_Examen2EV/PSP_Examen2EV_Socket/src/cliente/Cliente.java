package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import modelo.Peticion;
import modelo.Resumen;

public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String Host = "localhost";
		int Puerto = 5000;// puerto remoto
		Socket Cliente;
		try {
			Cliente = new Socket(Host, Puerto);

			ObjectOutputStream fsalida = new ObjectOutputStream(Cliente.getOutputStream());
			ObjectInputStream fentrada = new ObjectInputStream(Cliente.getInputStream());

			DataOutputStream salida = new DataOutputStream(Cliente.getOutputStream());
			DataInputStream entrada = new DataInputStream(Cliente.getInputStream());

			Scanner sc = new Scanner(System.in);
			// ID Imagen
			System.out.println(entrada.readUTF());
			int num = sc.nextInt();
			salida.writeInt(num);
			
			String textoC = entrada.readUTF();
			if (!textoC.equals("*")) {
				System.out.println(textoC);
				sc.nextLine();
				String clave = sc.nextLine();
				clave = Resumen.generarResumen(clave);
				salida.writeUTF(clave);
				String textoD = entrada.readUTF();
				if (!textoD.equals("*")) {
					Peticion peticion = (Peticion) fentrada.readObject();
					
					System.out.println("Puedes modificar la URL. 0 para modificarla");
					String cambio = sc.nextLine();
					if(!cambio.trim().equals("0")) {
						peticion.setImagen(cambio);
					}
					fsalida.writeObject(peticion);
					System.out.println("Imagen recibida");

					File file = new File("Recibido.jpg");
					FileOutputStream fileOS = new FileOutputStream(file);

					byte[] buffer = new byte[4096];
					int bytesRead;

					while ((bytesRead = entrada.read(buffer)) != -1) {
						fileOS.write(buffer, 0, bytesRead);
					}

					fileOS.close();
					

				} else {
					System.out.println("La contraseña no es correcta. Salgo del programa");
				}
			} else {
				System.out.println("No se ha encotrado ningun peticion con ese ID. Salgo del programa");
			}
			salida.close();
			entrada.close();
			fsalida.close();
			fentrada.close();

			sc.close();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
