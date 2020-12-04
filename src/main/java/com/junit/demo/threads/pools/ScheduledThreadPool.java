package com.junit.demo.threads.pools;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool {

    public static void main(String[] args) {
/*
    Schedule the tasks to run based on time delay(and retrigger for fixedRate / fixedDelay)

    Delay queue.

    Life Cycle: More threads are created if required.
 */

        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);

        //task to run after 10 seconds delay
        service.schedule(new Task(), 10, TimeUnit.SECONDS);

        //task to repeatedly every 10 seconds
        service.scheduleAtFixedRate(new Task(), 15, 10, TimeUnit.SECONDS);

        //task to run repeatedly 10 seconds after previous task completes
        service.scheduleWithFixedDelay(new Task(), 15, 10, TimeUnit.SECONDS);


        for (int i = 0; i < 100; i++) {
            System.out.println("task number - " + i + "     ");
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

