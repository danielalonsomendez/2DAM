package ejercicio1;

public class HiloEjercicio1 extends Thread {
	
	@Override
	public void run() {
		for (int i = 1; i <= 1000; i++) {
			System.out.println(getName() + " - Contador: " + i);
		}
	}
}
