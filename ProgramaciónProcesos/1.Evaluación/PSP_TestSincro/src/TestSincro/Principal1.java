package TestSincro;

public class Principal1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cuenta c = new Cuenta(40);
		Persona h1 = new Persona(c, "Aitor", 40);
		Persona h2 = new Persona(c, "Ana", 30);
	
		h1.start();
		h2.start();

	}

}
