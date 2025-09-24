package ejercicio2;

public class Ej2 {

	public static void main(String[] args) {
		HiloThread hilo1 = new HiloThread();
		HiloThread hilo2 = new HiloThread();

		hilo1.start();
		hilo2.start();

		while (hilo1.isAlive() || hilo2.isAlive()) {
			System.out.println("1 está vivo: " + hilo1.isAlive());
			System.out.println("2 está vivo: " + hilo2.isAlive());
		}

		System.out.println("Todos los hilos han terminado");
	}
}
