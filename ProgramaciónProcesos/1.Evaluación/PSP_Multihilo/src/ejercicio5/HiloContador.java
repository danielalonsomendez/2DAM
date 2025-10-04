package ejercicio5;

import javax.swing.JLabel;

public class HiloContador extends Thread {
	private JLabel contador;
	private JLabel prioridad;
	private Thread hilo;
	private boolean activo = true;

	public HiloContador(JLabel contador, JLabel prioridad, String nombre) {
		this.contador = contador;
		this.prioridad = prioridad;
		this.setName(nombre);
	}

	public void bajarPrioridad() {
		if (hilo.getPriority() == Thread.MAX_PRIORITY) {
			hilo.setPriority(Thread.NORM_PRIORITY);
		} else {
			hilo.setPriority(Thread.MIN_PRIORITY);
		}
		prioridad.setText("Pri: " + hilo.getPriority());
	}

	public void subirPrioridad() {
		if (hilo.getPriority() == Thread.MIN_PRIORITY) {
			hilo.setPriority(Thread.NORM_PRIORITY);
		} else {
			hilo.setPriority(Thread.MAX_PRIORITY);
		}
		prioridad.setText("Pri: " + hilo.getPriority());
	}

	public void finalizar() {
		activo = false;
	}

	@Override
	public void run() {
		hilo = Thread.currentThread();
		int cont = 0;
		while (activo) {
			contador.setText(getName() + ": " + cont);
			cont++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}