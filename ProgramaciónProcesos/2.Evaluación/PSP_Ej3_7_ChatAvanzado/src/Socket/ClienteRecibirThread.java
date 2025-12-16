package Socket;

import java.io.ObjectInputStream;
import javax.swing.JButton;
import javax.swing.JTextArea;

import modelo.Mensaje;
import vista.VentanaChat;
import vista.VentanaNick;

public class ClienteRecibirThread extends Thread {

    private ObjectInputStream entrada;
    private JTextArea texto;
    private JButton enviarBtn;
    private VentanaChat frame;
    private boolean activo = true;

    public ClienteRecibirThread(ObjectInputStream entrada, JTextArea texto, JButton enviarBtn, VentanaChat frame) {
        this.entrada = entrada;
        this.texto = texto;
        this.enviarBtn = enviarBtn;
        this.frame = frame;
    }

    @Override
    public void run() {
        try {
            enviarBtn.setEnabled(true);

            while (activo) {
                Mensaje mensaje = (Mensaje) entrada.readObject();
               
                if (mensaje.getContenido().equals("*")) {
                    texto.append("\nEl servidor se ha cerrado.\n");
                    enviarBtn.setEnabled(false);
                    activo = false;
                    return;
                } else if (mensaje.getContenido().equals("NICK_DUPLICADO")) {
                    frame.dispose();
                    VentanaNick ventanaNick = new VentanaNick();
                    ventanaNick.setVisible(true);
                    ventanaNick.setError("El nick ya está en uso. Elige otro.");
                    activo = false;
                    return;
                }
                texto.append(mensaje.toString() + "\n");
            }

        } catch (Exception e) {
            texto.append("\nConexión perdida.\n");
        }
    }

    public void detener() {
        activo = false;
    }
    
}
