package Menu;

import java.util.ArrayList;
import java.util.Scanner;

import Modelo.Tarea;

public class Menu {

	public static void main(String[] args) {
		ArrayList<Tarea> tareas = new ArrayList<Tarea>();
		Scanner sc = new Scanner(System.in);
		while (tareas.size() < 5) {
			tareas.add(pedirTarea(sc));
		}
		mostrarlista(tareas);
		int opcion = 0;
		do {
			System.out.println(
					"MENU\n1-Mostrar lista\n2-Anadir\n3-Eliminar\n4-Modificar\n5-Cambiar prioridad\n6-Marcar como completado\n7-Salir");
			try {
				opcion = Integer.parseInt(sc.nextLine());
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
				eliminarTarea(sc, tareas);
				break;
			case 4:
				modificarTarea(sc, tareas);
				break;
			case 5:
				cambiarPrioridad(sc, tareas);
				break;
			case 6:
				marcarCompletado(sc, tareas);
			case 7:
				System.out.println("Has salido.");
				break;
			}
		} while (opcion != 7);
		sc.close();
		// En caso de ser una app movil, habria que mostrar un Reclyer View con
		// diferentes labels, y un icono si esta completado.

	}

	public static Tarea pedirTarea(Scanner sc) {
		Tarea tarea = new Tarea();
		System.out.println("Introduce un texto:");
		tarea.setTexto(sc.nextLine().trim());
		tarea.setPrioridad(pedirPrioridad(sc));
		tarea.setCompletada((pedirCompletado(sc) == "T") ? true : false);
		return tarea;
	}

	public static int pedirPrioridad(Scanner sc) {
		int prioridad = -1;
		do {
			System.out.println("Introduce un prioridad:");
			try {
				prioridad = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Solo se admiten prioridades del 1-3");
			}
			sc.nextLine();
		} while (prioridad != 1 && prioridad != 2 && prioridad != 3);
		return prioridad;
	}

	public static String pedirCompletado(Scanner sc) {
		String completado = "";
		do {
			System.out.println("Introduce T/F para completar:");
			completado = sc.nextLine().trim().toUpperCase();
		} while (completado.equals("T") == false && completado.equals("F")== false);
		return completado;
	}

	public static void mostrarlista(ArrayList<Tarea> tareas) {
		for (var z = 0; z < tareas.size(); z++) {
			System.out.println("- " + tareas.get(z));
		}
	}

	public static int buscarTarea(Scanner sc, ArrayList<Tarea> tareas) {
		System.out.println("Introduce un texto a buscar:");
		String texto = sc.nextLine();
		int encontrado = -1;
		for (var z = 0; z < tareas.size(); z++) {
			if (tareas.get(z).getTexto().equals(texto) && encontrado == -1) {
				encontrado = z;
			}
		}
		return encontrado;
	}

	public static void eliminarTarea(Scanner sc, ArrayList<Tarea> tareas) {
		int busquedaTarea = buscarTarea(sc, tareas);
		if (busquedaTarea != -1) {
			tareas.remove(busquedaTarea);
			System.out.println("Tarea eliminada correctamente");
		} else {
			System.out.println("Tarea no encontrada");
		}
	}

	public static void modificarTarea(Scanner sc, ArrayList<Tarea> tareas) {
		int busquedaTarea = buscarTarea(sc, tareas);
		if (busquedaTarea != -1) {
			tareas.remove(busquedaTarea);
			tareas.add(busquedaTarea, pedirTarea(sc));
			System.out.println("Tarea modificada correctamente");
		} else {
			System.out.println("Tarea no encontrada");
		}
	}

	public static void cambiarPrioridad(Scanner sc, ArrayList<Tarea> tareas) {
		int busquedaTarea = buscarTarea(sc, tareas);
		if (busquedaTarea != -1) {
			tareas.get(busquedaTarea).setPrioridad(pedirPrioridad(sc));
			System.out.println("Tarea cambiada de prioridad correctamente");
		} else {
			System.out.println("Tarea no encontrada");
		}
	}

	public static void marcarCompletado(Scanner sc, ArrayList<Tarea> tareas) {
		int busquedaTarea = buscarTarea(sc, tareas);
		if (busquedaTarea != -1) {
			tareas.get(busquedaTarea).setCompletada((pedirCompletado(sc) == "T") ? true : false);
			System.out.println("Tarea cambiada de prioridad correctamente");
		} else {
			System.out.println("Tarea no encontrada");
		}
	}
}
