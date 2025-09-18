package ejercicio1;

public class Ejercicio1 extends Thread {
	
	@Override
	public void run() {
		for (int i = 1; i <= 1000; i++) {
			System.out.println(getName() + " - Contador: " + i);
		}
	}

	public static void main(String[] args) {
		Ejercicio1 hilo1 = new Ejercicio1();
		Ejercicio1 hilo2 = new Ejercicio1();
		hilo1.start();
		hilo2.start();
	}
}