package principal;

import controlador.Controlador;
import modelo.Estudiante;
import vista.Vista;

//MVCPatternDemo.java
public class Principal {
 public static void main(String[] args) {
     // Crear el modelo
     Estudiante modelo = new Estudiante();

     // Crear la vista
     Vista vista = new Vista();

     // Crear el controlador
     new Controlador(modelo, vista);
 }
}
