package com.interviewprep.multithreading.producerconsumer;

public class ProducerConsumerTest {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer();

        Thread producer = new Thread(new Producer(buffer));
        producer.start();

        for(int i = 0; i < buffer.getNUM_OF_CONSUMER(); i++){
            Thread consumer = new Thread(new Consumer(buffer));
            consumer.start();
        }
    }
}
