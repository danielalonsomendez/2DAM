package ejercicio3;

public class Escritora extends Thread {
	private boolean accion;

	public Escritora(boolean accion) {
		this.accion = accion;
	}

	@Override
	public void run() {
		while (true) {
			if (accion) {
				for (int i = 1; i <= 30; i++) {
					System.out.println(getName() + " - Contador: " + i);
				}
			} else {
				for (char letra = 'a'; letra <= 'z'; letra++) {
					System.out.println(getName() + " - Letra: " + letra);
				}
			}
		}
	}

}
