package com.junit.demo.threads.synchronization;

public class DeadlockTest {

    static class Friend {
        private final String name;

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public synchronized void bow(Friend bower) {
            System.out.println(Thread.currentThread().getName());
            System.out.format("%s: %s" + "  has bowed to me!%n", this.name, bower.getName());
            bower.bowBack(this);
        }

        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s" + " has bowed back to me!%n", this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend alphonse = new Friend("Alphonse");
        final Friend gaston = new Friend("Gaston");

        new Thread(() -> alphonse.bow(gaston)).start();
        new Thread(() -> gaston.bow(alphonse)).start();
    }
}


class TestDeadlockExample1 {
    public static void main(String[] args) {
        final String resource1 = "ratan jaiswal";
        final String resource2 = "vimal jaiswal";

        // t1 tries to lock resource1 then resource2
        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: locked resource 1");

                try {
                    Thread.sleep(100);
                } catch (Exception ignored) {
                }

                synchronized (resource2) {
                    System.out.println("Thread 1: locked resource 2");
                }
            }
        });

        // t2 tries to lock resource2 then resource1
        Thread t2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2: locked resource 2");

                try {
                    Thread.sleep(100);
                } catch (Exception ignored) {
                }

                synchronized (resource1) {
                    System.out.println("Thread 2: locked resource 1");
                }
            }
        });


        t1.start();
        t2.start();
    }
}