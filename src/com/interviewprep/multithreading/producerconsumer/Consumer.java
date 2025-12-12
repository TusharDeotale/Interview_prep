package com.interviewprep.multithreading.producerconsumer;

public class Consumer implements Runnable{
    private final SharedBuffer buffer;

    public Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try{
            while (true) {
                int value = buffer.consume();
                if(value == buffer.getPOISON_PILL()){
                    System.out.println("Consumer received poison. Exiting");
                    break;
                }
                Thread.sleep(5000);
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
