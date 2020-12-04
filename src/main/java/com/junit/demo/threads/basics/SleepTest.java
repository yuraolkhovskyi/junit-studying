package com.junit.demo.threads.basics;

public class SleepTest {

    public static void main(String[] args) throws InterruptedException {
        final String[] someInfo = {"one", "two", "three"};

        for (final String word : someInfo) {
            System.out.println("Sleeping 4 seconds");
            Thread.sleep(4000);
            System.out.println(word);
        }
    }


}
