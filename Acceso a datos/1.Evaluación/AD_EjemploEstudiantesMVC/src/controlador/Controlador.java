package controlador;

//StudentController.java
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Estudiante;
import vista.Vista;

public class Controlador {
	private Estudiante modelo;
	private Vista vista;

	public Controlador(Estudiante modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;

		// Escuchador para el botón
		this.vista.addSubmitListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateStudent();
			}
		});
	}

	private void updateStudent() {
		modelo.setNombre(vista.getNombre());
		modelo.setId(vista.getId());
		vista.setOutput("Nombre: " + modelo.getNombre() + "\nID: " + modelo.getId());
	}
}
