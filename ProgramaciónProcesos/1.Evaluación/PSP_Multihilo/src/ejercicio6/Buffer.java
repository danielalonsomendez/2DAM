package ejercicio6;

public class Buffer {
	private char contenido;
	private boolean bufferLleno = false;

	public synchronized void poner(char c) {
		contenido = c;
		bufferLleno = true;
	}
	public synchronized char recoger() {
		if(bufferLleno) {
			bufferLleno = false;
			return contenido;
		} else {
			return ' ';
		}
	}
	
}
