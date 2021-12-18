package com.test.Thread;

import java.time.LocalTime;

public class LockTask implements Runnable {

    private final Object lock;

    public LockTask(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread() + "=" + LocalTime.now().toString() + " begin ");
        synchronized (lock) {
            System.out.println(Thread.currentThread() + "=" + LocalTime.now().toString() + " get lock");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "=" + LocalTime.now().toString() + " end lock ");
        }
    }
}
