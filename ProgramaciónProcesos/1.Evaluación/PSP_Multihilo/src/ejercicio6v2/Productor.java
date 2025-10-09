package ejercicio6v2;

import java.util.Random;

class Productor extends Thread {
    private Buffer buffer;

    public Productor(Buffer b) {
        this.buffer = b;
    }

    @Override
    public void run() {
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            char c = (char) ('A' + rand.nextInt(26));
            buffer.poner(c);
        }
    }
}