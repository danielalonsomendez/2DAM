package Socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.Mensaje;

public class ServidorRecibirThread extends Thread {

	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private JTextArea texto;
	private JTextField conexionesTxt;
	private ArrayList<ObjectOutputStream> listaSalidas;
	private String nick;

	public ServidorRecibirThread(ObjectInputStream entrada, ObjectOutputStream salida,
			ArrayList<ObjectOutputStream> listaSalidas, JTextArea texto, JTextField conexionesTxt, String nick) {

		this.entrada = entrada;
		this.salida = salida;
		this.listaSalidas = listaSalidas;
		this.texto = texto;
		this.conexionesTxt = conexionesTxt;
		this.nick = nick;
	}

	@Override
	public void run() {
		try {
			enviarATodos(Mensaje.servidor(nick + " entra en el chat"));
			texto.append(nick + " entra en el chat.\n");

			while (true) {
				Mensaje mensaje = (Mensaje) entrada.readObject();
				if (mensaje.getContenido().equals("*")) {
					enviarATodos(Mensaje.servidor(nick + " ha abandonado el chat"));
					texto.append(nick + " ha abandonado el chat.\n");
					break;
				}
				texto.append(mensaje.toString() + "\n");
				enviarATodos(mensaje);
			}

		} catch (Exception e) {
			texto.append("Cliente " + nick + " desconectado inesperadamente.\n");
		} finally {
			listaSalidas.remove(salida);
			conexionesTxt.setText(String.valueOf(listaSalidas.size()));
			try {
				salida.close();
			} catch (Exception ignore) {
			}
		}
	}

	public void enviarATodos(Mensaje mensaje) {
		synchronized (listaSalidas) {
			for (ObjectOutputStream salida : listaSalidas) {
				try {
					salida.writeObject(mensaje);
					salida.flush();
				} catch (Exception ignore) {
				}
			}
		}
	}
}
