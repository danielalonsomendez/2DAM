package ejercicio4;

public class DetonadorConRetardo extends Thread {
	private int contador;

	public DetonadorConRetardo(int contador, String nombre) {
		this.contador = contador;
		this.setName(nombre);
	}

	@Override
	public void run() {
		System.out.println(getName() + " - Contador: " + contador);
		for (int i = contador - 1; i >= 0; i--) {
			System.out.println(getName() + " - Contador: " + i);
		}
		System.out.println(getName() + " ha terminado.");
	}

}
