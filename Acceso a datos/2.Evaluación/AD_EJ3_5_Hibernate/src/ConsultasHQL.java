import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import modelo.Departamentos;
import modelo.Empleados;

public class ConsultasHQL {

	public static void main(String[] args) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		
		mostrarEmpleadosDep10(session);
		mostrarEmpleadoMaxSalario(session);
		mostrarDepartamentosContabilidadInvestigacion(session);
		mostrarEmpleadoContabilidadDirector(session);
		mostrarEmpleadoFechaAlta(session);
		mostrarEmpleadosSueldoMadridOrden(session);
		mostrarSalarioDirectorEmpleadoMaxComision(session);
		mostrarFechaAltaEmpleadoMaxSalarioBarcelona(session);
		
		session.close();
		sesion.close();

	}
	/* 2. Mostrar los siguientes campos de los empleados del departamento 10
	 - Apellido
	 - Oficio
	 - Salario*/
	private static void mostrarEmpleadosDep10(Session session) {
		String hql = "from Empleados where departamentos.deptNo= '10' ";
		Query<Empleados> q = session.createQuery(hql, Empleados.class);
		List<Empleados> filas = q.list();
		for (int i = 0; i < filas.size(); i++) {
			Empleados emp = (Empleados) filas.get(i);
			System.out.println(emp.getApellido()+" - "+emp.getOficio()+" - "+emp.getSalario());

		}
	}
	/*3. Mostrar los siguientes campos del empleado con el máximo salario
	 - Apellido
	 - Salario
	 - Departamento*/
	private static void mostrarEmpleadoMaxSalario(Session session) {
		String hql = "from Empleados where salario= (select max(salario) from Empleados)";
		Query<Empleados> q = session.createQuery(hql, Empleados.class);
		List<Empleados> filas = q.list();
		for (int i = 0; i < filas.size(); i++) {
			Empleados emp = (Empleados) filas.get(i);
			System.out.println("Empleado max salario: "+emp.getApellido()+" - "+emp.getSalario()+" - "+emp.getDepartamentos().getDnombre());

		}
	}
	/*4. Visualiza los datos del departamento Contabilidad y Investigación*/

	private static void mostrarDepartamentosContabilidadInvestigacion(Session session) {
		String hql = "from Departamentos where dnombre= 'CONTABILIDAD' or dnombre= 'INVESTIGACION' ";
		Query<Departamentos> q = session.createQuery(hql, Departamentos.class);
		List<Departamentos> filas = q.list();
		for (int i = 0; i < filas.size(); i++) {
			Departamentos dep = (Departamentos) filas.get(i);
			System.out.println("Departamento: "+dep.getDnombre()+" - "+dep.getLoc());
		}
	}
	/*5. Empleados cuyo número de departamento sea de Contabilidad y el oficio DIRECTOR*/

	private static void mostrarEmpleadoContabilidadDirector(Session session) {
		String hql = "from Empleados where departamentos.dnombre= 'CONTABILIDAD' and oficio= 'DIRECTOR' ";
		Query<Empleados> q = session.createQuery(hql, Empleados.class);
		List<Empleados> filas = q.list();
		for (int i = 0; i < filas.size(); i++) {
			Empleados emp = (Empleados) filas.get(i);
			System.out.println("Empleado de Contabilidad y Director: "+emp.getApellido()+" - "+emp.getOficio());
		}
	}
	/*6. Empleados cuya fecha de alta es 1990-12-17*/
	private static void mostrarEmpleadoFechaAlta(Session session) {
		String hql = "from Empleados where fechaAlt= '1990-12-17' ";
		Query<Empleados> q = session.createQuery(hql, Empleados.class);
		List<Empleados> filas = q.list();
		for (int i = 0; i < filas.size(); i++) {
			Empleados emp = (Empleados) filas.get(i);
			System.out.println("Empleado con fecha de alta 1990-12-17: "+emp.getApellido()+" - "+emp.getFechaAlt());
		}
	}
	/*7. Empleados que tengan mejor sueldo y que sean del departamento Madrid.*/
	private static void mostrarEmpleadosSueldoMadridOrden(Session session) {
		String hql = "from Empleados where salario = (select max(salario) from Empleados where departamentos.loc= 'MADRID') and departamentos.loc= 'MADRID' ";
		Query<Empleados> q = session.createQuery(hql, Empleados.class);
		List<Empleados> filas = q.list();
		for (int i = 0; i < filas.size(); i++) {
			Empleados emp = (Empleados) filas.get(i);
			System.out.println("Empleado max salario en Madrid: "+emp.getApellido()+" - "+emp.getSalario()+" - "+emp.getDepartamentos().getLoc());

		}
	}
	/*8. Salario del director del empleado que más gana en comisión.*/
	private static void mostrarSalarioDirectorEmpleadoMaxComision(Session session) {
		String hql = "select dir from Empleados emp join Empleados dir on dir.empNo = emp.dir where emp.comision = ( select max(e2.comision) from Empleados e2 )";
		Query<Empleados> q = session.createQuery(hql, Empleados.class);
		List<Empleados> filas = q.list();
		for (int i = 0; i < filas.size(); i++) {
			Empleados dir = (Empleados) filas.get(i);
			System.out.println("Salario del director del empleado que más gana en comisión: "+dir.getApellido()+" - "+dir.getSalario());

		}
	}
	
	/*9. Fecha de alta del empleado que más salario tiene en Barcelona*/
	private static void mostrarFechaAltaEmpleadoMaxSalarioBarcelona(Session session) {
		String hql = "from Empleados where salario= (select max(salario) from Empleados where departamentos.loc= 'BARCELONA') and departamentos.loc= 'BARCELONA' ";
		Query<Empleados> q = session.createQuery(hql, Empleados.class);
		List<Empleados> filas = q.list();
		for (int i = 0; i < filas.size(); i++) {
			Empleados emp = (Empleados) filas.get(i);
			System.out.println("Fecha de alta del empleado que más salario tiene en Barcelona: "+emp.getApellido()+" - "+emp.getFechaAlt());

		}
	}
}