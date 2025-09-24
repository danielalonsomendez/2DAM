package ejercicio2;

public class HiloOperaciones implements Runnable {
	
	private String nombre;

	public HiloOperaciones(String nombre) {
		this.nombre = nombre;
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
