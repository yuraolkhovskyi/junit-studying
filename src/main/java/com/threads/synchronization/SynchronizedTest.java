package com.threads.synchronization;

public class SynchronizedTest implements Runnable{

    static class SynchronizedCounter {
        private int value = 0;

        public synchronized void increment() {
            value++;
        }

        public synchronized void decrement() {
            value--;
        }

        public synchronized int getValue() {
            return value;
        }
    }

    @Override
    public void run() {
        Counter.increment();
        Counter.decrement();
    }

    public static class Counter {

        public static int value = 0;

        public synchronized static void increment() {
            value++;
            System.out.println("increment value is: "+value+" Thread name: "+ Thread.currentThread().getName());
        }

        public synchronized static void decrement() {
            value--;
            System.out.println("decrement value is: "+ value+" Thread name: "+ Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Thread threadd = new Thread(new SynchronizedTest());
            Thread threadd1 = new Thread(new SynchronizedTest());

            threadd.start();
            threadd1.start();

            threadd.join();
            threadd1.join();

            System.out.println("final value " + Counter.value);
            if (Counter.value != 0) {
                return;
            }
            System.out.println();
        }
    }
}
