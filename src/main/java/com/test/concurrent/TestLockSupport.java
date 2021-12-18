package com.test.concurrent;

import java.util.concurrent.locks.LockSupport;

public class TestLockSupport {
    public static void main(String[] args) {
        Object object = new Object();
        LockSupport.parkUntil(object, System.currentTimeMillis() + 1000000);
    }
}
