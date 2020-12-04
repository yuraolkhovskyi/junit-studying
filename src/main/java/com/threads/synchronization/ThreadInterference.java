package com.threads.synchronization;

public class ThreadInterference implements Runnable{

    @Override
    public void run() {
        Counter.increment();
        Counter.decrement();
    }

    public static class Counter {

        private static int value = 0;

        public static void increment() {
            value++;
            System.out.println("increment value is: "+value+" Thread name: "+ Thread.currentThread().getName());
        }

        public static void decrement() {
            value--;
            System.out.println("decrement value is: "+ value+" Thread name: "+ Thread.currentThread().getName());
        }

        public static int getValue() {
            return value;
        }

    }

    public static void main(String[] args) throws InterruptedException {
//        Thread thread1 = new Thread(new ThreadInterference());
//        Thread thread2 = new Thread(new ThreadInterference());
//
//        thread1.setName("THREAD 1");
//        thread2.setName("THREAD 2");
//
//        thread1.start();
//        thread2.start();
//
//        thread1.join();
//        thread2.join();
//
//        System.out.println(Thread.currentThread().getName());
//        System.out.println("final value " + Counter.value);


        while (true) {
            Thread thread = new Thread(new ThreadInterference());
            Thread thread1 = new Thread(new ThreadInterference());

            thread.start();
            thread1.start();

            thread.join();
            thread1.join();

            System.out.println("final value " + Counter.value);
            if (Counter.value != 0) {
                return;
            }
            System.out.println();
        }
    }




}
