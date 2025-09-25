package ejercicio4;


public class Principal {

	public static void main(String[] args) {
		DetonadorConRetardo hilo1 = new DetonadorConRetardo(10, "Hilo 1");
		DetonadorConRetardo hilo2 = new DetonadorConRetardo(20, "Hilo 2");
		DetonadorConRetardo hilo3 = new DetonadorConRetardo(30, "Hilo 3");
		DetonadorConRetardo hilo4 = new DetonadorConRetardo(40, "Hilo 4");

		hilo1.start();
		hilo2.start();
		hilo3.start();
		hilo4.start();
		
		try {
			hilo1.join();
			hilo2.join();
			hilo3.join();
			hilo4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Todos los hilos han terminado.");
	}
}
