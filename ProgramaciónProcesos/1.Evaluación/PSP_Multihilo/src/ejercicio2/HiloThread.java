package ejercicio2;

public class HiloThread extends Thread {   
    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            System.out.println(getName() + " - Contador: " + i);
        }
    }
    
}