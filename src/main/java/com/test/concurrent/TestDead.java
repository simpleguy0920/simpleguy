package com.test.concurrent;

import java.util.concurrent.TimeUnit;

public class TestDead {


    public static void main(String[] args) {

        Object a = new Object();
        Object b = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (a) {
                    System.out.println("1 lock a");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (b) {
                        System.out.println("1 lock b");
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (b) {
                    System.out.println("2 lock b");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (a) {
                        System.out.println("2 lock a");
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}
