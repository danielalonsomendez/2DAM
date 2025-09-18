package ejercicio2;

public class Ej3 implements Runnable {

	private String nombre;

	public Ej3(String nombre) {
		this.nombre = nombre;
	}

	public static void main(String[] args) {
		Thread persona1 = new Thread(new Ej3("Persona 1"));
		Thread persona2 = new Thread(new Ej3("Persona 2"));

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

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println(nombre + " - Operación " + (i + 1));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(nombre + " ha completado todas sus operaciones.");
	}

}