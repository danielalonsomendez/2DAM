
public class Trabajador extends Thread {

	Trabajo t = null;
	boolean hace = false;

	public Trabajador(Trabajo tr, boolean hace) {
		t = tr;
		this.hace = hace;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			if (hace)
				t.doJob();
			else
				t.waitForJob();
		}

	}
}
