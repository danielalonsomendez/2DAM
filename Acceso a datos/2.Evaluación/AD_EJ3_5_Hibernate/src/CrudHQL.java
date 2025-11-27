import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import modelo.Departamentos;
import modelo.Empleados;

class CrudHQL {

	public static void main(String[] args) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		
		insertarDepartamento(session);
		insertarEmpleadoInformatica(session);
		modificarEmpleadoGil(session);
		eliminarEmpleadosMadrid(session);
		eliminarEmpleadoMaxSalarioContabilidad(session);
		
		session.close();
	}

	/*
	 * 1. Insertar un nuevo departamento con nombre de INFORMÁTICA y localización de
	 * Bilbao.
	 */
	public static void insertarDepartamento(Session session) {
		Transaction tx = session.beginTransaction();
		Departamentos dept = new Departamentos();
		dept.setDnombre("INFORMÁTICA");
		dept.setLoc("Bilbao");
		// Resto de datos
		dept.setDeptNo((byte) 100);
		session.persist(dept);
		tx.commit();
	}

	/*
	 * 2. Insertar un empleado de informática que sea DIRECTOR y con salario de
	 * 2300, su fecha de incorporación la actual y tenga como una comisión de 1000.
	 */
	public static void insertarEmpleadoInformatica(Session session) {
		Transaction tx = session.beginTransaction();
		Empleados emp = new Empleados();
		emp.setOficio("DIRECTOR");
		emp.setSalario((float) 2300.0);
		emp.setFechaAlt(new Date(System.currentTimeMillis()));
		emp.setComision((float) 1000.0);
		// Resto de datos
		emp.setApellido("RODRIGUEZ");
		emp.setEmpNo((short) 99);
		Departamentos dept = session.get(Departamentos.class, (byte) 100);
		emp.setDepartamentos(dept);
		session.persist(emp);
		tx.commit();
	}

	/* 3. Modificar el salario de GIL a 1300 y su fecha de alta a ayer. */
	public static void modificarEmpleadoGil(Session session) {
		Transaction tx = session.beginTransaction();
		String hql = "from Empleados where apellido= 'GIL' ";
		Empleados emp = session.createQuery(hql, Empleados.class).uniqueResult();
		emp.setSalario((float) 1300.0);
		int diaAnterior = 24 * 60 * 60 * 1000;
		emp.setFechaAlt(new Date(System.currentTimeMillis() - diaAnterior));
		session.merge(emp);
		tx.commit();
	}

	/* 4. Eliminar los empleados del departamento situado en MADRID. */
	public static void eliminarEmpleadosMadrid(Session session) {
		Transaction tx = session.beginTransaction();
		String hql = "from Empleados where departamentos.loc= 'MADRID'";
		List<Empleados> empleadosMadrid = session.createQuery(hql, Empleados.class).list();
		for (Empleados emp : empleadosMadrid) {
			session.remove(emp);
		}
		tx.commit();
	} 
	/*
	 * 5. Eliminar un empleado del departamento de CONTABILIDAD cuyo salario sea el mayor de ese departamento.
	 * */
	public static void eliminarEmpleadoMaxSalarioContabilidad(Session session) {
		Transaction tx = session.beginTransaction();
		String hql = "from Empleados where departamentos.dnombre= 'CONTABILIDAD' and salario= (select max(salario) from Empleados where departamentos.dnombre= 'CONTABILIDAD')";
		Empleados emp = session.createQuery(hql, Empleados.class).uniqueResult();
		session.remove(emp);
		tx.commit();
	}
}
