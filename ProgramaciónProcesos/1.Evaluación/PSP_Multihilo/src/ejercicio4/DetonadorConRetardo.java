package ejercicio4;

public class DetonadorConRetardo extends Thread {
	private int contador;
	private String nombre;

	public DetonadorConRetardo(int contador, String nombre) {
		this.contador = contador;
		this.nombre = nombre;
	}

	@Override
	public void run() {
		System.out.println(nombre + " - Contador: " + contador);
		for (int i = contador - 1; i >= 0; i--) {
			System.out.println(nombre + " - Contador: " + i);
		}
		System.out.println(nombre + " ha terminado.");
	}

}
