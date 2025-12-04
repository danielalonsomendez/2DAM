package Modelo;

public class Tarea {
	private String texto;
	private int prioridad;
	private boolean completada;
	
	public Tarea(String texto, int prioridad, boolean completada) {
		super();
		this.texto = texto;
		this.prioridad = prioridad;
		this.completada = completada;
	}
	public Tarea() {
		// TODO Auto-generated constructor stub
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public int getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
	public boolean isCompletada() {
		return completada;
	}
	public void setCompletada(boolean completada) {
		this.completada = completada;
	}
	
	@Override
	public String toString() {
		return texto+"-  (Prioridad "+ prioridad+") - "+ ((completada)?"COMPLETADA":"PENDIENTE");
	}
	
	
}
