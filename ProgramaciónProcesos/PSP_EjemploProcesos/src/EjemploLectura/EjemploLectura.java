package EjemploLectura;

import java.util.Scanner;

public class EjemploLectura {
	public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
		String texto = teclado.nextLine();
		System.out.println("Desde EjemploLectura: " +texto);
		teclado.close();
	}
}
