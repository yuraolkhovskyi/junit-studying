package com.junit.demo.threads.threadsobjects;

import lombok.SneakyThrows;

public class InterraptedTest implements Runnable{
    @SneakyThrows
    @Override
    public void run() {
        final String[] strings = {"one", "two", "three"};
        for (String string : strings) {
            try {
                System.out.println(string);
                Thread.sleep(2000);
            } catch (final InterruptedException e) {
                System.out.println("I've been interrupted (form try catch)");
                e.printStackTrace();
            }

//            System.out.println(string);
//            if (Thread.interrupted()) {
//                System.out.println("**** I've been interrupted (using Thread.interrupted check) ****");
//            }
        }
    }

    public static void main(String[] args) {
        final Thread thread = new Thread(new InterraptedTest());
        thread.start();
        System.out.println("main class");
        thread.interrupt();
    }
}
