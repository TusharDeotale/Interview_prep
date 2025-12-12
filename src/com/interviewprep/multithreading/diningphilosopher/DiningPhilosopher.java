package com.interviewprep.multithreading.diningphilosopher;

import java.util.concurrent.Semaphore;

public class DiningPhilosopher {
    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[5];
        Semaphore[] fork = new Semaphore[philosophers.length];

        for (int i = 0; i < philosophers.length; i++) {
            fork[i] = new Semaphore(1); // 1 permit access per fork.
        }

        for (int i = 0; i < philosophers.length; i++) {

            Semaphore leftFork = fork[i];
            Semaphore rightFork = fork[(i + 1) % philosophers.length];

            philosophers[i] = new Philosopher(i, leftFork, rightFork);

            Thread thread = new Thread(philosophers[i]);
            thread.start();
        }
    }
}
