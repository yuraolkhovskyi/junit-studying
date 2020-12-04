package com.threads.pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor {
    public static void main(String[] args) {
/*
        The size of the pool is - 1. 1 thread which is fetching the tasks from BlockingQueue.
        If the task throws an exception and the thread is killed then thread pool recreates thread because of the task.
        Used to make sure that always task 2(for instance) will be executed before task 3 and after task 1.
        Runs tasks sequentially.

        Lifecycle: recreates thread if killed because of the task.
*/

        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100; i++) {
            service.execute(new FixedThreadPool.Task());
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
