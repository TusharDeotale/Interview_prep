package com.interviewprep.multithreading;

public class EvenOddSequential {
    public static void main(String[] args) {
        int limit = 10;
        Printer printer = new Printer(limit);

        Thread oddThread = new Thread(printer::printOdd);
        Thread evenThread = new Thread(printer::printEven);

        oddThread.start();
        evenThread.start();
    }
}
