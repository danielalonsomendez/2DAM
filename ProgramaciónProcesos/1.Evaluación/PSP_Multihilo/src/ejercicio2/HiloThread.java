package ejercicio2;

public class HiloThread extends Thread {
    
    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            System.out.println(getName() + " - Contador: " + i);
        }
    }
    
    public static void main(String[] args) {
        HiloThread hilo1 = new HiloThread();
        HiloThread hilo2 = new HiloThread();
        
        hilo1.start();
        hilo2.start();
        
        while (hilo1.isAlive() || hilo2.isAlive()) {
            System.out.println("1 está vivo: " + hilo1.isAlive());
            System.out.println("2 está vivo: " + hilo2.isAlive());
        }
        
        System.out.println("Todos los hilos han terminado");
    }
}