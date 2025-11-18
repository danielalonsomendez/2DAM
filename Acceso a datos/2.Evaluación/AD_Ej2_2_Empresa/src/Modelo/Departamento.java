package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;

public class Departamento {
	private String dept_no;
	private String loc;
	private String dnombre;
	
	public Departamento(String dept_no, String loc, String dnombre) {
		super();
		this.dept_no = dept_no;
		this.loc = loc;
		this.dnombre = dnombre;
	}
	public Departamento() {
		super();
	}
	public String getDept_no() {
		return dept_no;
	}

	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getDnombre() {
		return dnombre;
	}

	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}

	@Override
	public String toString() {
		return "Departamento [dept_no=" + dept_no + ", loc=" + loc + ", dnombre=" + dnombre + "]";
	}

	public static ArrayList<Departamento> listarDepartamentos() {

		Connection con = Conexion.conectar();
		ArrayList<Departamento> departamentos = new ArrayList<Departamento>();

		try {
			var query = con.prepareStatement("SELECT * FROM departamentos").executeQuery();
			while (query.next()) {
				Departamento d = new Departamento(query.getString("dept_no"), query.getString("loc"),
						query.getString("dnombre"));
				departamentos.add(d);
			}
			
			query.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return departamentos;
	}
	public void cargarDepartamentoPorId(int dept_no) {
		Connection con = Conexion.conectar();

		try {
			var query = con.prepareStatement("SELECT * FROM departamentos WHERE dept_no=?");
			query.setInt(1, dept_no);
			var result = query.executeQuery();
			if (result.next()) {
				setDept_no(result.getString("dept_no"));
				setDnombre(result.getString("dnombre"));
				setLoc(result.getString("loc"));
			}
			result.close();
			query.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
