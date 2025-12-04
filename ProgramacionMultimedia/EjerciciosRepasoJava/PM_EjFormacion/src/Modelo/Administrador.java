package Modelo;

import java.time.Year;

public class Administrador extends UsuarioPlataforma {
    private int nivelAcceso;
    private boolean puedeSuspenderCuentas;

    public Administrador(String nombre, String email, int anioRegistro, int nivelAcceso, boolean puedeSuspenderCuentas) {
        super(nombre, email, anioRegistro);
        this.nivelAcceso = nivelAcceso;
        this.puedeSuspenderCuentas = puedeSuspenderCuentas;
    }

    public Administrador(String nombre, String email) {
        this(nombre, email, Year.now().getValue(), 1, true);
    }

    @Override
    public String generarResumen() {
        return "Administrador " + nombre + " | nivel acceso: " + nivelAcceso + (puedeSuspenderCuentas ? " | con facultad de suspensión" : " | sin facultad de suspensión");
    }
}
