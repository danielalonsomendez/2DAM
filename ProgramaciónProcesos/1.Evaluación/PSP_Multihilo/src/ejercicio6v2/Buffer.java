package ejercicio6v2;

public class Buffer {
	private char contenido;
	private boolean bufferLleno = false;

	public synchronized void poner(char c) {
		while (bufferLleno) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		contenido = c;
		bufferLleno = true;
        System.out.println("Productor: " + c);
		notify();
	}
	
	public synchronized void recoger() {
		while (!bufferLleno) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
        System.out.println("Consumidor: " + contenido);
		bufferLleno = false;
        notify();
	}
}