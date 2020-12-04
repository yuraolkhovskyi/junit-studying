package com.junit.demo.threads.threadsobjects;

public class SimpleThreads {

    private static class MessageLoop implements Runnable {

        @Override
        public void run() {
            final String[] importantInfo = {"Mares eat oats", "Does eat oats", "Little lambs eat ivy", "A kid will eat ivy too"};

            try {
                for (String s : importantInfo) {
                    // Pause for 4 seconds
                    Thread.sleep(4000);

                    // Print a message
                    threadMessage(s);
                }
            } catch (InterruptedException e) {
                threadMessage("I wasn't done!");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // Delay, in milliseconds before we interrupt MessageLoop thread (default one hour).
        long patience = 1000 * 60 * 60;

        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        final Thread thread = new Thread(new MessageLoop());
        thread.start();

        threadMessage("Waiting for MessageLoop thread to finish");
        // loop until MessageLoop thread exits
        while (thread.isAlive()) {
            threadMessage("Still waiting...");
            // Wait maximum of 1 second for MessageLoop thread to finish.

            thread.join(1000);

            if (((System.currentTimeMillis() - startTime) > patience) && thread.isAlive()) {

                threadMessage("Tired of waiting!");

                thread.interrupt();
                // Shouldn't thread be long now -- wait indefinitely
                thread.join();
            }
        }
        threadMessage("Finally!");
    }

    // Display a message, preceded by the name of the current thread
    static void threadMessage(final String message) {
        final String threadName = Thread.currentThread().getName();

        System.out.format("%s: %s%n", threadName, message);
    }
}
