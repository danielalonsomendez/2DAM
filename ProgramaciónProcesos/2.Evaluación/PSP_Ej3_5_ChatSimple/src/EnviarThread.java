import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EnviarThread implements Runnable {

	public Socket cliente;

	public EnviarThread(Socket cliente) {
		this.cliente = cliente;
	}

	@Override
	public void run() {
		try {
			DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
			Scanner teclado = new Scanner(System.in);

			while (true) {
				String mensaje = teclado.nextLine();
				dos.writeUTF(mensaje);

				if (mensaje.toLowerCase().equals("salir")) {
					cliente.close();
					break;
				}
			}
			teclado.close();
		} catch (IOException e) {
		}

	}

}
