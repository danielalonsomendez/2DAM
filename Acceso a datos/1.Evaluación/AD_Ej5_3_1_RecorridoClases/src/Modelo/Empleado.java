package Modelo;

import java.util.Date;

public class Empleado {
	private String documentId;
	private double comision;
	private Empleado dir;
	private String emple_ap1;
	private Date fecha_alt;
	private String oficio;
	private double salario;
	public Empleado(String documentId, String emple_ap1, Date fecha_alt, String oficio,
			double salario) {
		super();
		this.documentId = documentId;
		this.emple_ap1 = emple_ap1;
		this.fecha_alt = fecha_alt;
		this.oficio = oficio;
		this.salario = salario;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
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
	public String getEmple_ap1() {
		return emple_ap1;
	}
	public void setEmple_ap1(String emple_ap1) {
		this.emple_ap1 = emple_ap1;
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
	@Override
	public String toString() {
		String t = "Empleado: documentId=" + documentId + ", comision=" + comision + ", emple_ap1="
				+ emple_ap1 + ", fecha_alt=" + fecha_alt + ", oficio=" + oficio + ", salario=" + salario ;
		if (dir != null) {
			t += ", dir=" + dir.getDocumentId();
		}
		return t;
	}
	

	
	

}
