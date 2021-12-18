package com.test.Thread;

public class TestSyn {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        Runnable target;
        Thread t1 = new Thread(new LockTask(o));
        Thread t2 = new Thread(new LockTask(o));
        t1.start();
        t2.start();
    }
}
