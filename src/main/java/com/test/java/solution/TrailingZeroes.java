package com.test.java.solution;

public class TrailingZeroes {
    public static void main(String[] args) {
        int value = trailingZeroes(10);
        System.out.println(value);
    }

    public static int trailingZeroes(int n) {
        if (n < 5) {
            return 0;
        } else {
            return trailingZeroes(n / 5) + n / 5;
        }
    }
}
