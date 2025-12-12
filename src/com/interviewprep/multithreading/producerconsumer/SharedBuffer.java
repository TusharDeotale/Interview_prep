package com.interviewprep.multithreading.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

public class SharedBuffer {

    private Queue<Integer> queue = new LinkedList<>();
    private final int capacity = 5;
    private final int POISON_PILL = -1;
    private final Object lock = new Object();
    private final int NUM_OF_CONSUMER = 10;

    public int getNUM_OF_CONSUMER() {
        return NUM_OF_CONSUMER;
    }

    public int getPOISON_PILL() {
        return POISON_PILL;
    }

    public void produce(int value) throws InterruptedException {
        synchronized (lock) {
            while (queue.size() == capacity) {
                System.out.println("Buffer is full. " + Thread.currentThread().getName() + " is waiting");
                lock.wait();
            }
            queue.offer(value);
            System.out.println(Thread.currentThread().getName() + " Produced - " + value);
            lock.notifyAll();
        }
    }

    public int consume() throws InterruptedException {
        synchronized (lock) {
            while (queue.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " Buffer is empty. Consumer is waiting");
                lock.wait();
            }
            int value = queue.poll();
            System.out.println(Thread.currentThread().getName() + " Consumed - " + value);
            lock.notifyAll();
            return value;
        }
    }
}
