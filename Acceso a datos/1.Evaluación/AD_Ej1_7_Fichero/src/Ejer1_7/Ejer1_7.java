package Ejer1_7;

import java.awt.EventQueue;

import Controlador.Controlador;
import Vista.Menu;

public class Ejer1_7 {

	private static Controlador controlador = new Controlador();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu(controlador);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
