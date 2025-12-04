package Menu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Modelo.Camion;
import Modelo.Coche;
import Modelo.Moto;
import Modelo.Vehiculo;

public class MenuTaller {

	public static void main(String[] args) {
		ArrayList<Vehiculo> vehiculos = new ArrayList<>();
		Scanner teclado = new Scanner(System.in);
		int opcion = 0;
		do {
			System.out.println("--- MENU TALLER ---\n1- Mostrar vehiculos\n2- Dar entrada\n3- Presupuesto\n4- Arreglo\n5- Pago\n6- Salida\n7- Buscar por ID\n8- Salir");
			opcion = leerEntero(teclado, "Selecciona una opcion:");
			switch (opcion) {
			case 1:
				mostrarVehiculos(vehiculos);
				break;
			case 2:
				vehiculos.add(anadirVehiculo(teclado));
				System.out.println("Vehiculo anadido.");
				break;
			case 3:
				presupuestoVehiculo(teclado, vehiculos);
				break;
			case 4:
				arregloVehiculo(teclado, vehiculos);
				break;
			case 5:
				pagoVehiculo(teclado, vehiculos);
				break;
			case 6:
				darSalida(teclado, vehiculos);
				break;
			case 7:
				buscarVehiculo(teclado, vehiculos, true);
				break;
			case 8:
				guardarArchivo(vehiculos);
				System.out.println("Has salido del taller.");
				break;
			default:
				System.out.println("Solo se admiten numeros del 1-8.");
			}
		} while (opcion != 8);
		teclado.close();
	}

	// Aqui se piden los datos base y se crea el tipo de vehiculo segun la opcion que marque el usuario
	public static Vehiculo anadirVehiculo(Scanner teclado) {
		int tipo = leerTipoVehiculo(teclado);
		int id = leerEntero(teclado, "Introduce el ID:");
		System.out.println("Introduce la marca:");
		String marca = teclado.nextLine().trim();
		System.out.println("Introduce el modelo:");
		String modelo = teclado.nextLine().trim();
		switch (tipo) {
		case 1:
			int puertas = leerEntero(teclado, "Numero de puertas:");
			String matricula = leerMatricula(teclado);
			return new Coche(id, marca, modelo, puertas, matricula);
		case 2:
			int cilindrada = leerEntero(teclado, "Cilindrada:");
			return new Moto(id, marca, modelo, cilindrada);
		case 3:
			int carga = leerEntero(teclado, "Carga en toneladas:");
			return new Camion(id, marca, modelo, carga);
		default:
			return null;
		}
	}

	// Marca un vehiculo en entrada con el presupuesto inicial
	public static void presupuestoVehiculo(Scanner teclado, ArrayList<Vehiculo> vehiculos) {
		Vehiculo vehiculo = buscarVehiculo(teclado, vehiculos, false);
		if (vehiculo == null) {
			return;
		}
		if (!"entrada".toLowerCase().equals(vehiculo.getEstado())) {
			System.out.println("Solo se puede presupuestar un vehiculo en estado entrada.");
			return;
		}
		double presupuesto = leerDouble(teclado, "Importe del presupuesto:");
		vehiculo.setPrecioPresupuesto(presupuesto);
		vehiculo.setEstado("presupuesto");
		System.out.println("Presupuesto guardado.");
	}

	// Cambia el estado a arreglo y guarda cuanto ha costado realmente
	public static void arregloVehiculo(Scanner teclado, ArrayList<Vehiculo> vehiculos) {
		Vehiculo vehiculo = buscarVehiculo(teclado, vehiculos, false);
		if (vehiculo == null) {
			return;
		}
		if (!"presupuesto".toLowerCase().equals(vehiculo.getEstado())) {
			System.out.println("El vehiculo debe estar presupuestado antes de pasar a arreglo.");
			return;
		}
		double importeFinal = leerDouble(teclado, "Precio final:");
		vehiculo.setPrecioFinal(importeFinal);
		vehiculo.setEstado("arreglo");
		System.out.println("Arreglo guardado");
	}

	// Cambia el estado a pagado
	public static void pagoVehiculo(Scanner teclado, ArrayList<Vehiculo> vehiculos) {
		Vehiculo vehiculo = buscarVehiculo(teclado, vehiculos, false);
		if (vehiculo == null) {
			return;
		}
		if (!"arreglo".toLowerCase().equals(vehiculo.getEstado())) {
			System.out.println("El vehiculo debe estar arreglado antes del pago.");
			return;
		}
		vehiculo.setPagado(true);
		vehiculo.setEstado("pago");
		System.out.println("Pago guardado.");
	}

	// Entrega coches a los que ya pagaron
	public static void darSalida(Scanner teclado, ArrayList<Vehiculo> vehiculos) {
		int posicion = posicionArray(teclado, vehiculos);
		if (posicion == -1) {
			return;
		}
		Vehiculo vehiculo = vehiculos.get(posicion);
		if (!vehiculo.isPagado()) {
			System.out.println("No se puede dar salida sin registrar el pago.");
			return;
		}
		vehiculo.setEstado("salida");
		vehiculos.remove(posicion);
		System.out.println("Vehiculo entregado al cliente.");
	}

	// Busca un vehiculo por ID
	public static Vehiculo buscarVehiculo(Scanner teclado, ArrayList<Vehiculo> vehiculos, boolean mostrar) {
		int posicion = posicionArray(teclado, vehiculos);
		if (posicion == -1) {
			return null;
		}
		Vehiculo vehiculo = vehiculos.get(posicion);
		if (mostrar) {
			System.out.println(vehiculo);
		}
		return vehiculo;
	}

	// Devuelve la posicion del vehiculo 
	public static int posicionArray(Scanner teclado, ArrayList<Vehiculo> vehiculos) {
		if (vehiculos.isEmpty()) {
			System.out.println("No hay vehiculos.");
			return -1;
		}
		int id = leerEntero(teclado, "Introduce el ID a buscar:");
		for (int i = 0; i < vehiculos.size(); i++) {
			if (vehiculos.get(i).getID() == id) {
				return i;
			}
		}
		System.out.println("Vehiculo no encontrado.");
		return -1;
	}

	public static void mostrarVehiculos(ArrayList<Vehiculo> vehiculos) {
		System.out.println("---  Vehiculos ---");
		if (vehiculos.isEmpty()) {
			System.out.println("Sin vehiculos.");
			return;
		}
		for (Vehiculo vehiculo : vehiculos) {
			System.out.println("- " + vehiculo);
		}
	}

	public static void guardarArchivo(ArrayList<Vehiculo> vehiculos) {
		File archivo = new File("historicos.txt");
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
			for (Vehiculo vehiculo : vehiculos) {
				bw.write(vehiculo.toString());
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("No se pudo guardar el historico: " + e.getMessage());
		}
	}

	public static String leerMatricula(Scanner teclado) {
		while (true) {
			System.out.println("Introduce la matricula (1111AAA):");
			String valor = teclado.nextLine().trim().toUpperCase();
			if (valor.length() != 7) {
				System.out.println("La matricula debe tener 4 numeros y 3 letras.");
				continue;
			}
			boolean valida = true;
			for (int i = 0; i < 4; i++) {
				if (!Character.isDigit(valor.charAt(i))) {
					valida = false;
					break;
				}
			}
			if (!valida) {
				System.out.println("Los primeros 4 caracteres deben ser numeros.");
				continue;
			}
			for (int i = 4; i < 7; i++) {
				char c = valor.charAt(i);
				if (!Character.isLetter(c) || !Character.isUpperCase(c)) {
					valida = false;
					break;
				}
			}
			if (!valida) {
				System.out.println("Los ultimos 3 caracteres deben ser letras.");
				continue;
			}
			return valor;
		}
	}

	public static int leerTipoVehiculo(Scanner teclado) {
		int tipo;
		do {
			System.out.println("Tipo de vehiculo (1-Coche, 2-Moto, 3-Camion):");
			tipo = leerEntero(teclado, "");
		} while (tipo < 1 || tipo > 3);
		return tipo;
	}

	public static int leerEntero(Scanner teclado, String mensaje) {
		while (true) {
			if (!mensaje.isEmpty()) {
				System.out.println(mensaje);
			}
			String linea = teclado.nextLine().trim();
			try {
				return Integer.parseInt(linea);
			} catch (NumberFormatException e) {
				System.out.println("Introduce un numero valido.");
			}
		}
	}

	public static double leerDouble(Scanner teclado, String mensaje) {
		while (true) {
			System.out.println(mensaje);
			String linea = teclado.nextLine().trim();
			try {
				return Double.parseDouble(linea);
			} catch (NumberFormatException e) {
				System.out.println("Introduce un numero decimal valido.");
			}
		}
	}
}
