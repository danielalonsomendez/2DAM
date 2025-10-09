package ejercicio6v2;

class Consumidor extends Thread {
    private Buffer buffer;
    public Consumidor(Buffer b) {
        this.buffer = b;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            buffer.recoger();
        }
    }
}