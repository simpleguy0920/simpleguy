package com.test.Thread;

import java.util.concurrent.TimeUnit;

public class TestThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
                System.out.println("end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t1.getState());
        t1.start();
        for (int i = 0; i < 3000; i++) {
            System.out.println(t1.getState());
        }


    }
}
