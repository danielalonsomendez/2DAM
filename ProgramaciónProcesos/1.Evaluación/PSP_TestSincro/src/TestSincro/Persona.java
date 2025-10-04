package TestSincro;

public class Persona extends Thread {
	private Cuenta c;
	String nombre;
	int cuantia;
	
	public Persona(Cuenta c, String nombre,int cuantia)
	{
		this.c = c;
		this.nombre = nombre;
		this.cuantia=cuantia;
	}
	
	public void run()
	{
		//synchronized (c) {
			c.retirarDinero(cuantia, nombre);
		//}
	}
	
}
