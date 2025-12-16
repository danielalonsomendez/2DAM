package Socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JTextArea;

import modelo.Mensaje;
import vista.VentanaChat;

public class Cliente extends Thread {

	private Socket cliente;
	private JTextArea textArea;
	private JButton btnEnviar;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private String nick;
	private VentanaChat frame;
	public Cliente(Socket cliente, JTextArea textArea, JButton btnEnviar,String nick,VentanaChat frame) {

		this.cliente = cliente;
		this.textArea = textArea;
		this.btnEnviar = btnEnviar;
		this.nick = nick;
		this.frame=frame;
	}

	@Override
	public void run() {
		try {
		//	 socket = new Socket(HOST, PUERTO);
			 salida = new ObjectOutputStream(cliente.getOutputStream());
			 salida.flush();
			entrada = new ObjectInputStream(cliente.getInputStream());
			
		       salida.writeObject(nick);
	            salida.flush();
	            
			ClienteRecibirThread recibir = new ClienteRecibirThread(entrada, textArea, btnEnviar,frame);
			recibir.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void enviarMensaje(String contenido, String nick) {
	    try {
	        Mensaje mensaje = new Mensaje(contenido, nick);
	        salida.writeObject(mensaje);
	        salida.flush();
	    } catch (Exception e) {
	        System.out.println("Error enviando mensaje: " + e.getMessage());
	    }
	}
}