package ejemplo;

public class ProcesoSecundario {

	public static void main(String[] args) {
		System.out.println("Proceso secundario en marcha...");
		// -- El proceso hace cosas pero genera error y devuelve un código de error 103 //
		System.exit(103);

	}

}
