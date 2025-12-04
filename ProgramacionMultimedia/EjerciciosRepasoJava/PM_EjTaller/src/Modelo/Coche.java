package Modelo;

public class Coche  extends Vehiculo {
	private int numeroPuertas;
	private String matricula;

	public Coche(int iD, String marca, String modelo, int numeroPuertas, String matricula) {
		super(iD, marca, modelo);
		this.numeroPuertas = numeroPuertas;
		setMatricula(matricula);
	}

	public int getNumeroPuertas() {
		return numeroPuertas;
	}

	public void setNumeroPuertas(int numeroPuertas) {
		this.numeroPuertas = numeroPuertas;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula.trim().toUpperCase();
	}

	@Override
	public String toString() {
		return super.toString() + "Puertas: " + numeroPuertas + '\n' + "Matricula: " + matricula + '\n';
	}

}
