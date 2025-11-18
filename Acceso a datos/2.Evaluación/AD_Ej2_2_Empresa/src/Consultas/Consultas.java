package Consultas;

import java.util.ArrayList;

import Modelo.Departamento;
import Modelo.Empleado;

public class Consultas {

	public static void main(String[] args) {
		System.out.println("1:");
		ArrayList<Departamento> departamentos = Departamento.listarDepartamentos();
		for (Departamento depto : departamentos) {
			System.out.println(depto);
		}
		System.out.println("\n2:");
		ArrayList<Empleado> empleados = Empleado.listarEmpleadosPorDepartamento(10);
		for (Empleado emp : empleados) {
			System.out.println(emp.toStringej2());
		}
		Empleado empSalarioMax = new Empleado();
		empSalarioMax.cargarEmpleadoSalarioMax();
		System.out.println("\n3: " + empSalarioMax.toStringej3());
		
	}

}
