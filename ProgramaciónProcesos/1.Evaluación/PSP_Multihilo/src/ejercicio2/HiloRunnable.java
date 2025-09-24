package ejercicio2;

public class HiloRunnable implements Runnable {

    private String nombre;
    
    public HiloRunnable(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public void run() {
    	for (int i = 1; i <= 1000; i++) {
			System.out.println(nombre+ " - Contador: " + i);
		}
    }
    

}