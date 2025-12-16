package Socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.Mensaje;

public class Servidor extends Thread {

	private int PUERTO = 5000;
	private int MAX = 10;
	private boolean activo = true;
	private ServerSocket serverSocket;
	private ArrayList<ObjectOutputStream> listaSalidas = new ArrayList<>();

	private JTextArea texto;
	private JTextField conexionesTxt;
	private ServidorRecibirThread hilo;
	private ArrayList<String> listaNicks = new ArrayList<>();

	public Servidor(JTextArea texto, JTextField conexionesTxt) {
		this.texto = texto;
		this.conexionesTxt = conexionesTxt;
	}

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(PUERTO);
			texto.append("Esperando conexiones...\n");
			int i = 0;
			while (activo || i < MAX) {
				try {
					Socket socket = serverSocket.accept();
					i++;
					ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
					salida.flush();
					ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
					String nick = (String) entrada.readObject();
				    if (listaNicks.contains(nick)) {
				    							salida.writeObject(new Mensaje("NICK_DUPLICADO", "SYSTEM"));
                        continue;
                    }
					listaNicks.add(nick);

					synchronized (listaSalidas) {
						listaSalidas.add(salida);
					}
					conexionesTxt.setText(String.valueOf(listaSalidas.size()));
					hilo = new ServidorRecibirThread(entrada, salida, listaSalidas, texto, conexionesTxt, nick);

					hilo.start();

				} catch (Exception e) {
					if (!activo) {
						texto.append("Servidor detenido correctamente.\n");
					} else {
						e.printStackTrace();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void detener() {
		try {
			hilo.enviarATodos(new Mensaje("*", "SYSTEM"));

			activo = false;
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}

			texto.append("Servidor detenido.\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
