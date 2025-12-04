package Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {
		ArrayList<String> tareas = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		while (tareas.size() < 5) {
			tareas.add(pedirTarea(sc));
		}
		mostrarlista(tareas);
		int opcion = 0;
		do {
			System.out.println("MENU\n1-Mostrar lista\n2-Anadir\n3-Eliminar\n4-Modificar\n5-Salir");
		try {
			opcion =Integer.parseInt( sc.nextLine());
		} catch (Exception e) {
			System.out.println("Solo se admiten numeros del 1-5");
		}
			switch (opcion) {
			case 1:
				System.out.println("--- Lista de tareas ---");
				mostrarlista(tareas);
				break;
			case 2:
				tareas.add(pedirTarea(sc));
				System.out.println("Añadido correctamente.");
				break;
			case 3:
				eliminarTarea(sc,tareas);
				break;
			case 4:
				modificarTarea(sc,tareas);
				break;
			case 5:
				System.out.println("Has salido.");
				break;
			}
		} while (opcion != 5);
		sc.close();
		// En caso de ser una app movil, habria que mostrar un Reclyer View con un boton para cada accion,la cual tendria su respectivo activity.
	}

	public static String pedirTarea(Scanner sc) {
		System.out.println("Introduce un texto:");
		return sc.nextLine().trim();
	}

	public static void mostrarlista(ArrayList<String> tareas) {
		for (var z = 0; z < tareas.size(); z++) {
			System.out.println("- " + tareas.get(z));
		}
	}

	public static int buscarTarea(Scanner sc, ArrayList<String> tareas) {
		System.out.println("Introduce un texto a buscar:");
		String texto = sc.nextLine();
		int encontrado = -1;
		for (var z = 0; z < tareas.size(); z++) {
			if (tareas.get(z).equals(texto) && encontrado == -1) {
				encontrado = z;
			}
		}
		return encontrado;
	}
	public static void eliminarTarea(Scanner sc, ArrayList<String> tareas) {
		int busquedaTarea = buscarTarea(sc, tareas);
		if (busquedaTarea != -1) {
			tareas.remove(busquedaTarea);
			System.out.println("Tarea eliminada correctamente");
		} else {
			System.out.println("Tarea no encontrada");
		}
	}
	public static void modificarTarea(Scanner sc, ArrayList<String> tareas) {
		int busquedaTarea = buscarTarea(sc, tareas);
		if (busquedaTarea != -1) {
			tareas.remove(busquedaTarea);
			tareas.add(busquedaTarea,pedirTarea(sc));
			System.out.println("Tarea modificada correctamente");
		} else {
			System.out.println("Tarea no encontrada");
		}
	}
}
