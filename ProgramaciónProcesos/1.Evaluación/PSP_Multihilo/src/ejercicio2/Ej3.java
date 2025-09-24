package ejercicio2;

public class Ej3 {

	public static void main(String[] args) {
		Thread persona1 = new Thread(new HiloOperaciones("Persona 1"));
		Thread persona2 = new Thread(new HiloOperaciones("Persona 2"));

		persona1.start();
		persona2.start();

		try {
			persona1.join();
			persona2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Todas las operaciones han finalizado.");
	}

}