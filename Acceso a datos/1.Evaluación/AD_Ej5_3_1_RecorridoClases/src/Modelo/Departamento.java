package Modelo;

import java.util.ArrayList;

public class Departamento {
	private String documentId;
	private String dpto_loc;
	private String dpto_nom;
	private ArrayList<Empleado> empleados;
	public Departamento(String documentId, String dpto_loc, String dpto_nom) {
		super();
		this.documentId = documentId;
		this.dpto_loc = dpto_loc;
		this.dpto_nom = dpto_nom;
		this.empleados = new ArrayList<Empleado>();
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public String getDpto_loc() {
		return dpto_loc;
	}
	public void setDpto_loc(String dpto_loc) {
		this.dpto_loc = dpto_loc;
	}
	public String getDpto_nom() {
		return dpto_nom;
	}
	public void setDpto_nom(String dpto_nom) {
		this.dpto_nom = dpto_nom;
	}
	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(ArrayList<Empleado> empleados) {
		this.empleados = empleados;
	}
	public void addEmpleado(Empleado e) {
		empleados.add(e);
	}
	@Override
	public String toString() {
		return "Departamento: documentId=" + documentId + ", dpto_loc=" + dpto_loc + ", dpto_nom=" + dpto_nom;
			
	}
	
}
