package ejercicio6;

import java.util.Random;

class Productor extends Thread {
    private Buffer buffer;
    private int tiempoPausa; 

    public Productor(Buffer b, int tiempoPausa) {
        this.buffer = b;
        this.tiempoPausa = tiempoPausa;
    }

    @Override
    public void run() {
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            char c = (char) ('A' + rand.nextInt(26));
            buffer.poner(c);
            System.out.println("Productor: " + c);
            try {
                Thread.sleep(tiempoPausa); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}