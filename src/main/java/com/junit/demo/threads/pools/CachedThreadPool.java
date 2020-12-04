package com.junit.demo.threads.pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {

    public static void main(String[] args) {
/*




    The pool will keep the task in the thread pool and it will search for free threads which are already created.
    If there is no such thread is available it will create a new thread, add this to the pool and will be asked to execute the task.

    The queue is synchronous -- can hold only 1 task

   Lifecycle: If thread is idle for 60 seconds (no tasks to execute) then kill the thread.
 */


        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            System.out.println("task number - " + i+"     ");
            service.execute(new Task());
        }
        System.out.println("Thread name: " + Thread.currentThread().getName());

    }

    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.print("Thread name: " + Thread.currentThread().getName());
        }
    }

}

