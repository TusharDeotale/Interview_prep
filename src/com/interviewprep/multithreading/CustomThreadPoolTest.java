package com.interviewprep.multithreading;

public class CustomThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        CustomThreadPool pool = new CustomThreadPool(24, 16);

        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println(availableProcessors);

        for(int i = 0; i < 1000; i++){

            final int taskId = i;

            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " executing " + taskId);
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e){

                }
            });
        }

        Thread.sleep(5000);
        pool.shutDown();
    }
}
