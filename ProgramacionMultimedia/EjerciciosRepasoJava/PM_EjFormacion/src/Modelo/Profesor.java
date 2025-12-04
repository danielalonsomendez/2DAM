package Modelo;

import java.time.Year;

public class Profesor extends UsuarioPlataforma implements Notificable {
    private int cursosImpartidos;
    private String especialidad;

    public Profesor(String nombre, String email, int anioRegistro, int cursosImpartidos, String especialidad) {
        super(nombre, email, anioRegistro);
        this.cursosImpartidos = cursosImpartidos;
        this.especialidad = especialidad;
    }

    public Profesor(String nombre, String email) {
        this(nombre, email, Year.now().getValue(), 0, "Generalista");
    }

    @Override
    public String generarResumen() {
        return "Profesor " + nombre + " | cursos impartidos: " + cursosImpartidos + " | especialidad: " + especialidad;
    }

    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("Notificación al profesor " + nombre + " vía " + obtenerCanalPreferido() + ": " + mensaje);
    }

    @Override
    public String obtenerCanalPreferido() {
        return "push";
    }
}
