import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);

            Thread enviar = new Thread(new EnviarThread(socket));
            Thread recibir = new Thread(new RecibirThread(socket));

            enviar.start();
            recibir.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}