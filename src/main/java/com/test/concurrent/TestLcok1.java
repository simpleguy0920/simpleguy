package com.test.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLcok1 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock(false);

        Thread t1 = new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    System.out.println("1 sleep");
                    Thread.sleep(1000);
                    System.out.println("1 wake");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    System.out.println("2 sleep");
                    Thread.sleep(1000);
                    System.out.println("2 wake");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }

        });
        t1.start();
        t2.start();

    }
}
