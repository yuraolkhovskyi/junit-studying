package com.junit.demo.threads.threadsobjects;

public class HelloRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("testing Runnable: msg from a new thread");
    }

    public static void main(String[] args) {
        new Thread(new HelloRunnable()).start();
    }
}

class HelloThread extends Thread {
    @Override
    public void run() {
        System.out.println("testing Thread: msg from a new thread");
    }

    public static void main(String[] args) {
        new HelloThread().start();
    }
}
