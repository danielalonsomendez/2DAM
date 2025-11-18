package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Conexion.Conexion;

public class Empleado {
	private String emp_no;
	private double comision;
	private Empleado dir;
	private String apellido;
	private Date fecha_alt;
	private String oficio;
	private double salario;
	private Departamento departamento;


	public Empleado(String apellido, String oficio, double salario) {
		super();
		this.apellido = apellido;
		this.oficio = oficio;
		this.salario = salario;
	}
	public Empleado() {
		super();
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public double getComision() {
		return comision;
	}

	public void setComision(double comision) {
		this.comision = comision;
	}

	public Empleado getDir() {
		return dir;
	}

	public void setDir(Empleado dir) {
		this.dir = dir;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFecha_alt() {
		return fecha_alt;
	}

	public void setFecha_alt(Date fecha_alt) {
		this.fecha_alt = fecha_alt;
	}

	public String getOficio() {
		return oficio;
	}

	public void setOficio(String oficio) {
		this.oficio = oficio;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	

	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public String toStringej2() {
		return "Empleado [apellido=" + apellido + ", oficio=" + oficio + ", salario=" + salario + "]";
	}

	public String toStringej3() {
		return "Empleado [apellido=" + apellido + ", salario=" + salario + ", departamento=" + departamento.toString() + "]";
	}

	public static ArrayList<Empleado> listarEmpleadosPorDepartamento(int dept_no) {
		Connection con = Conexion.conectar();
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();

		try {
			var query = con.prepareStatement("SELECT apellido,oficio,salario FROM empleados WHERE dept_no =?");
			query.setInt(1, dept_no);
			var result = query.executeQuery();

			while (result.next()) {
				Empleado e = new Empleado(result.getString("apellido"), result.getString("oficio"),
						result.getDouble("salario"));
				empleados.add(e);
			}

			query.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return empleados;
	}

	public void cargarEmpleadoSalarioMax() {
		Connection con = Conexion.conectar();

		try {
			var query = con.prepareStatement(
					"SELECT apellido,oficio,dept_no FROM empleados WHERE salario=(SELECT MAX(salario) FROM empleados)")
					.executeQuery();
			if (query.next()) {
				setApellido(query.getString("apellido"));
				setOficio(query.getString("oficio"));
				Departamento d = new Departamento();
				d.cargarDepartamentoPorId(query.getInt("dept_no"));
				setDepartamento(d);
			}

			query.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
