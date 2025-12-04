package Modelo;

public interface Notificable {
    void enviarNotificacion(String mensaje);
    String obtenerCanalPreferido();
}
