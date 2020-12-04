package com.threads.threadsobjects;

public class JoinTest implements Runnable {
    @Override
    public void run() {
        final String[] strings = {"one", "two", "three", "four"};
        for (String string : strings) {
            try {
                System.out.println(string);
                Thread.sleep(1000);
            } catch (final InterruptedException e) {
                System.out.println("I've been interrupted (form try catch)");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Thread thread = new Thread(new JoinTest());
        thread.start();

        thread.join();

        System.out.println("MAIN THREAD");
        thread.start();

    }
}
