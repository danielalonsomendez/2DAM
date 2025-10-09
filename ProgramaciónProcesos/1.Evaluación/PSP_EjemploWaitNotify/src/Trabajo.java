
public class Trabajo {
	
	private boolean trabajoHecho = false;

    // Este método será llamado por un hilo para hacer el trabajo
    public synchronized void doJob() {
        System.out.println("Haciendo el trabajo...");
        try {
            Thread.sleep(100);  // Simula el tiempo que toma completar el trabajo
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        trabajoHecho = true;
        notify();  // Notifica al hilo que está esperando que el trabajo ha sido completado
    }

    // Este método será llamado por otro hilo para esperar hasta que el trabajo esté hecho
    public synchronized void waitForJob() {
        while (!trabajoHecho) {
            try {
                System.out.println("Esperando a que el trabajo esté hecho...");
                wait();  // Espera a que se complete el trabajo
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
        }
        System.out.println("El trabajo está hecho. Continuando...");
        trabajoHecho = false;
    }

}
