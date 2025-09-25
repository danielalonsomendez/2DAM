package ejercicio5;

import javax.swing.JLabel;

public class HiloContador extends Thread {
	private JLabel contador;
	private JLabel prioridad;
	private Thread hilo;
	private String nombre;
	private boolean activo = true;

	public HiloContador(JLabel contador, JLabel prioridad, String nombre) {
		this.contador = contador;
		this.prioridad = prioridad;
		this.nombre = nombre;
	}

	public void bajarPrioridad() {
		hilo.setPriority(Thread.MIN_PRIORITY);
		prioridad.setText("Pri: -1");
	}

	public void subirPrioridad() {
		hilo.setPriority(Thread.MAX_PRIORITY);
		prioridad.setText("Pri: 10");
	}

	public void finalizar() {
		activo = false;
	}

	@Override
	public void run() {
		hilo = Thread.currentThread();
		int cont = 0;
		while (activo) {
			contador.setText(nombre + ": " + cont);
			cont++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
