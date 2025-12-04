package Adivina;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import Modelo.Datos;

public class JugadorAdivina {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			Socket socket = new Socket("localhost", 5000);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

			Datos dato = (Datos) ois.readObject();

			System.out.println("Id jugador: " + dato.getIdentificador());
			System.out.println(dato.getCadena());
			
	        while (true) {

	            if (!dato.isJuega()) {
	                if (dato.getIntentos() == 0) {
	                    System.out.println("JUEGO FINALIZADO, NO HAY MÁS INTENTOS");
	                } else if (dato.isGana()) {
	                    System.out.println("HAS GANADO!! EL JUEGO HA TERMINADO");
	                } else {
	                    System.out.println("EL JUEGO HA TERMINADO, HAN ADIVINADO EL NUMERO");
	                }
	                sc.close();
                    socket.close();
	                break;
	            }
	            System.out.print("Intento: " + ((5-dato.getIntentos())+1) + " => Introduce numero:");

	            String numero = sc.nextLine();
	            dato.setCadena(numero);
	            oos.writeObject(dato);
	            oos.flush();

	            dato = (Datos) ois.readObject();
	            System.out.println(dato.getCadena());
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
