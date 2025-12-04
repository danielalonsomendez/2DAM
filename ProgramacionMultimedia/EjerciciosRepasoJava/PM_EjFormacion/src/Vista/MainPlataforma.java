package Vista;

import java.util.ArrayList;
import java.util.List;

import Modelo.Administrador;
import Modelo.Alumno;
import Modelo.Notificable;
import Modelo.Profesor;
import Modelo.UsuarioPlataforma;

public class MainPlataforma {
    public static void main(String[] args) {
        ArrayList<UsuarioPlataforma> usuarios = crearUsuarios();
        mostrarResumenes(usuarios);
        ArrayList<Notificable> notificables = extraerNotificables(usuarios);
        imprimirNotificaciones(notificables);
    }

    private static ArrayList<UsuarioPlataforma> crearUsuarios() {
        ArrayList<UsuarioPlataforma> usuarios = new ArrayList<>();
        usuarios.add(new Alumno("Nerea", "nerea@gmail.com", 2023, 5, true));
        usuarios.add(new Alumno("Luis", "luis@gmail.com"));
        usuarios.add(new Profesor("Carla", "carla@gmail.com", 2020, 12, "Android"));
        usuarios.add(new Profesor("Iker", "iker@gmail.com"));
        usuarios.add(new Administrador("Asier", "asier@gmail.com", 2019, 3, true));
        usuarios.add(new Administrador("Carlos", "carlos@gmail.com"));
        return usuarios;
    }

    private static void mostrarResumenes(List<UsuarioPlataforma> usuarios) {
        for (UsuarioPlataforma usuario : usuarios) {
            System.out.println(usuario.generarResumen());
        }
    }

    private static ArrayList<Notificable> extraerNotificables(List<UsuarioPlataforma> usuarios) {
        ArrayList<Notificable> notificables = new ArrayList<>();
        for (UsuarioPlataforma usuario : usuarios) {
            if (usuario instanceof Notificable) {
                notificables.add((Notificable) usuario);
            }
        }
        return notificables;
    }

    private static void imprimirNotificaciones(List<Notificable> notificables) {
        for (Notificable notificable : notificables) {
            System.out.println("Canal preferido: " + notificable.obtenerCanalPreferido());
            notificable.enviarNotificacion("Mensaje de prueba");
        }
    }
}
