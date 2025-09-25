package ejercicio3;

public class Principal {

	public static void main(String[] args) {
		Escritora hilo1 = new Escritora(true);
		Escritora hilo2 = new Escritora(false);
		hilo1.start();
		hilo2.start();
	}

}
