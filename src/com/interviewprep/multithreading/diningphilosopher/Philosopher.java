package com.interviewprep.multithreading.diningphilosopher;

import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable {

    private final int id;
    private final Semaphore leftFork;
    private final Semaphore rightFork;

    public Philosopher(int id, Semaphore leftFork, Semaphore rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking..");
        Thread.sleep((int) (Math.random() * 1000));
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating..");
        Thread.sleep((int) (Math.random() * 1000));
    }


    @Override
    public void run() {
        try {
//            while (true) {
                think();
                // Avoid DeadLock :: circular wait

                if (id % 2 == 0) {
                    leftFork.acquire();
                    rightFork.acquire();
                } else {
                    rightFork.acquire();
                    leftFork.acquire();
                }
                eat();
                leftFork.release();
                rightFork.release();
//            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
