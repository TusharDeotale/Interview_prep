package com.interviewprep.multithreading;

import java.util.concurrent.ArrayBlockingQueue;

public class CustomThreadPool {
    private final int numOfThreads;
    private final WorkerThread[] workers;
    private final ArrayBlockingQueue<Runnable> taskQueue;
    private volatile boolean isStopped = false;

    public CustomThreadPool(int numOfThreads, int queueCapacity) {
        this.numOfThreads = numOfThreads;
        this.workers = new WorkerThread[numOfThreads];
        this.taskQueue = new ArrayBlockingQueue<>(queueCapacity);

        for(int i = 0; i < numOfThreads; i++){
            workers[i] = new WorkerThread("Worker "+ i);
            workers[i].start();
        }
    }

    public void execute(Runnable task) {
        if (!isStopped) {
            try {
                taskQueue.put(task);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalStateException("ThreadPool is stopped.");
        }
    }

    public void shutDown() {
        isStopped = true;
        for (WorkerThread worker : workers) {
            worker.interrupt();
        }
    }

    private class WorkerThread extends Thread {

        public WorkerThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (!isStopped || !taskQueue.isEmpty()) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();

                } catch (InterruptedException e) {
                    if (isStopped) {
                        break;
                    }
                }
            }
        }
    }
}
