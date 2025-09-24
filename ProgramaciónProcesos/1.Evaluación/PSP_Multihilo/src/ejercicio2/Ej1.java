package ejercicio2;

public class Ej1 {
	
	public static void main(String[] args) {
        HiloRunnable runnable1 = new HiloRunnable("Hilo-1");
        HiloRunnable runnable2 = new HiloRunnable("Hilo-2");
        
        Thread hilo1 = new Thread(runnable1);
        Thread hilo2 = new Thread(runnable2);
        
        hilo1.start();
        hilo2.start();
        
        while (hilo1.isAlive() || hilo2.isAlive()) {
            System.out.println("1 está vivo: " + hilo1.isAlive());
            System.out.println("2 está vivo: " + hilo2.isAlive());
        }
        
        System.out.println("Todos los hilos han terminado");
    }
}
