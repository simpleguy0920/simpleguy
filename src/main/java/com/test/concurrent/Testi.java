package com.test.concurrent;

import java.time.LocalTime;
import java.util.concurrent.atomic.LongAdder;

public class Testi {
    static LongAdder i = new LongAdder();

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 100000000; j++) {
                    i.increment();
                }
                System.out.println(LocalTime.now().toString() + i.longValue());
            }

        });

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 100000000; j++) {
                    i.increment();
                }
                System.out.println(LocalTime.now().toString() + i.longValue());
            }

        });
        System.out.println(LocalTime.now().toString() + i);

        t1.start();
        t.start();
    }
}
