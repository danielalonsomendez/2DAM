package ejercicio6;

class Consumidor extends Thread {
    private Buffer buffer;
    private int tiempoPausa;

    public Consumidor(Buffer b, int tiempoPausa) {
        this.buffer = b;
        this.tiempoPausa = tiempoPausa;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            char c = buffer.recoger();
            System.out.println("Consumidor: " + c);
            try {
                Thread.sleep(tiempoPausa);
            } catch (InterruptedException e) {
				e.printStackTrace();
            }
        }
    }
}