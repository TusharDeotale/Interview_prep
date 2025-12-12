package com.interviewprep.multithreading.producerconsumer;

public class Producer implements Runnable {
    private final SharedBuffer buffer;

    public Producer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int value = 0;
        try {
            for (int i = 0; i < 100; i++) {
                buffer.produce(value++);
                Thread.sleep(0);
            }

            for (int i = 0; i < buffer.getNUM_OF_CONSUMER(); i++) {
                buffer.produce(buffer.getPOISON_PILL());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
