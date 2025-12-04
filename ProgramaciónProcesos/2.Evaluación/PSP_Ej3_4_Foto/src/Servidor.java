import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		try {
			ServerSocket servidor = new ServerSocket(5000);
			Socket cliente = servidor.accept();
			DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
			File fich = new File("foto.png");
			BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(fich));

			byte[] buffer = new byte[4096];
			int bytesRead;

			while ((bytesRead = fileInput.read(buffer)) != -1) {
				salida.write(buffer, 0, bytesRead);
			}
			fileInput.close();
			salida.close();

			cliente.close();
			servidor.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
