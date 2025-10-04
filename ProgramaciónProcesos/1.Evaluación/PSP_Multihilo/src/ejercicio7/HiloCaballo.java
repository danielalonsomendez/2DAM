package ejercicio7;

import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class HiloCaballo extends Thread {
	private JProgressBar barra;
	private int multiplicador;
	public static volatile boolean carreraFinalizada = false;
	public static JButton btnEmpezar;
	public static JLabel ganador;

	public HiloCaballo(String caballo, JProgressBar barra, int prioridad) {
		this.setName(caballo);
		this.barra = barra;
		this.setPriority(prioridad);
		Random random = new Random();
		multiplicador = random.nextInt(20) + 1;
	}

	@Override
	public void run() {
		int avance = 0;
		barra.setValue(avance);

		while (!carreraFinalizada) {
			avance += multiplicador;
			if (avance >= 100) {
				synchronized (HiloCaballo.class) {
					if (!carreraFinalizada) {
						avance = 100;
						terminar();
					} else {
						avance = 99;
					}
				}
			}
			barra.setValue(avance);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void terminar() {
		ganador.setText("Ganador: " + getName());
		btnEmpezar.setEnabled(true);
		carreraFinalizada = true;
	}

}