package com.test.concurrent;

public class Test1 {

    private final Object o1 = new Object();


    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                o1.notify();
            }
        });
        t1.start();
        synchronized (o1) {
            while (true) {
                System.out.println("hehe");
                o1.wait();
                System.out.println("haha");

            }
        }
    }
}
