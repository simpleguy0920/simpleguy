package com.test.java;

import java.time.LocalDateTime;

public class TestFib {
    public static void main(String[] args) {
        TestFib testFib = new TestFib();
        System.out.println(LocalDateTime.now());
        System.out.println(testFib.fib1(20));
        System.out.println(LocalDateTime.now());
        System.out.println(testFib.fib2(20));
        System.out.println(LocalDateTime.now());
        System.out.println(testFib.fib3(20));
        System.out.println(LocalDateTime.now());
    }

    public int fib1(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return fib1(n - 2) + fib1(n - 1);
        }
    }

    public int fib2(int n) {
        if (n <= 1) {
            return 1;
        }
        int result = 1;
        int last = 1;
        int beforeLast = 1;
        for (int i = 2; i <= n; i++) {
            result = beforeLast + last;
            beforeLast = last;
            last = result;
        }
        return result;
    }

    public int fib3(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] array = new int[n + 1];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i <= n; i++) {
            array[i] = array[i - 2] + array[i - 1];
        }
        return array[n];
    }

}
