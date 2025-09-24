package ejercicio1;

public class Ejercicio1 {

	public static void main(String[] args) {
		HiloEjercicio1 hilo1 = new HiloEjercicio1();
		HiloEjercicio1 hilo2 = new HiloEjercicio1();
		hilo1.start();
		hilo2.start();
	}
}