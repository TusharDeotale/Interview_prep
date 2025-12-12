package com.interviewprep.multithreading;

class Printer {
    private int number = 1;
    private final int limit;

    public Printer(int limit) {
        this.limit = limit;
    }

    public synchronized void printOdd() {
        while (number <= limit) {
            if (number % 2 == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                System.out.print(number + " ");
                number++;
                notify();
            }
        }
    }

    public synchronized void printEven() {
        while (number <= limit) {
            if (number % 2 != 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                System.out.print(number + " ");
                number++;
                notify();
            }
        }
    }
}

