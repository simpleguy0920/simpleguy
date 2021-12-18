package com.test.Thread;

import java.time.LocalTime;
import java.util.stream.LongStream;

public class Test1Para {
    public static void main(String[] args) {
        System.out.println(LocalTime.now().toString());
        LongStream.range(0, 10000000000l).parallel().sum();
        System.out.println(LocalTime.now().toString());
        long sum = 0;
        for (long i = 0; i < 10000000000l; i++) {
            sum += i;
        }
        System.out.println(LocalTime.now().toString());

    }
}
