package Modelo;

import java.time.Year;
// Usable en app Android
public abstract class UsuarioPlataforma {
    protected String nombre;
    protected String email;
    protected int anioRegistro;

    public UsuarioPlataforma(String nombre, String email, int anioRegistro) {
        this.nombre = nombre;
        this.email = email;
        this.anioRegistro = anioRegistro;
    }

    public UsuarioPlataforma(String nombre, String email) {
        this(nombre, email, Year.now().getValue());
    }

    public String generarResumen() {
        return "Usuario genérico: " + nombre + ", registrado en " + anioRegistro;
    }
}
