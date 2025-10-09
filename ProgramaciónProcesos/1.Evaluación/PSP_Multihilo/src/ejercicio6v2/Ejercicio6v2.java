package ejercicio6v2;

public class Ejercicio6v2 {

	public static void main(String[] args) {
		Buffer b = new Buffer();
		Productor p = new Productor(b); 
		Consumidor c = new Consumidor(b); 

		p.start();
		c.start();
	}
}
