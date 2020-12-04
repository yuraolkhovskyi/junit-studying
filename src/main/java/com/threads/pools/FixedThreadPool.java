package com.threads.pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {

    public static void main(String[] args) {
/*
    1 Java thread == 1 OS thread

    Stored in thread-safe queue. Typically it's BlockingQueue.
    All created threads will fetch the task and execute one after another.
 */

        ExecutorService service = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 100; i++) {
            service.execute(new Task());
        }
        System.out.println("Thread name: " + Thread.currentThread().getName());

    }

    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("Thread name: " + Thread.currentThread().getName());
        }
    }

}

