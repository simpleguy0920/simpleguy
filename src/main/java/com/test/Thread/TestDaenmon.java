package com.test.Thread;

import java.util.concurrent.TimeUnit;

public class TestDaenmon {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("t1 start");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 end");

        });
        Thread t2 = new Thread(() -> {
            System.out.println("t2 start");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2 end");

        });
        t1.start();
        t2.setDaemon(true);
        t2.start();
    }
}
