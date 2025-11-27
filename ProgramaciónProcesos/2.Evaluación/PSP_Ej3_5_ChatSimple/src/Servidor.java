import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
	    try {
            ServerSocket serverSocket = new ServerSocket(5000);

            Socket socket = serverSocket.accept();
            Thread enviar = new Thread(new EnviarThread(socket));
            Thread recibir = new Thread(new RecibirThread(socket));

            enviar.start();
            recibir.start();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
