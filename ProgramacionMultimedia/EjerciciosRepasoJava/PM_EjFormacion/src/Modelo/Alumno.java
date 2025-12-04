package Modelo;

import java.time.Year;

public class Alumno extends UsuarioPlataforma implements Notificable {
    private int cursosMatriculado;
    private boolean premium;

    public Alumno(String nombre, String email, int anioRegistro, int cursosMatriculado, boolean premium) {
        super(nombre, email, anioRegistro);
        this.cursosMatriculado = cursosMatriculado;
        this.premium = premium;
    }

    public Alumno(String nombre, String email) {
        this(nombre, email, Year.now().getValue(), 0, false);
    }

    @Override
    public String generarResumen() {
        return "Alumno " + nombre + " | cursos: " + cursosMatriculado + (premium ? " | Plan premium" : " | Plan básico");
    }

    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("Notificación al alumno " + nombre + " vía " + obtenerCanalPreferido() + ": " + mensaje);
    }

    @Override
    public String obtenerCanalPreferido() {
        return premium ? "email" : "sms";
    }
}
