import java.io.DataInputStream;
import java.net.Socket;

public class RecibirThread implements Runnable {

	public Socket cliente;

	public RecibirThread(Socket cliente) {
		this.cliente = cliente;
	}

	@Override
	public void run() {
		try {
			DataInputStream dis = new DataInputStream(cliente.getInputStream());

			while (true) {
				String mensaje = dis.readUTF();
				System.out.println(mensaje);

				if (mensaje.toLowerCase().equals("salir")) {
					cliente.close();
					break;
				}
			}

		} catch (Exception e) {
		}
	}

}
