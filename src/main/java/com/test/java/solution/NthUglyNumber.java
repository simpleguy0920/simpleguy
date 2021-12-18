package com.test.java.solution;

public class NthUglyNumber {
    public static void main(String[] args) {
        NthUglyNumber nthUglyNumber = new NthUglyNumber();
        System.out.println(nthUglyNumber.nthUglyNumber(1432));
    }

    public int nthUglyNumber(int n) {
        int sum = 0;
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            if (isUgly(i)) {
                n--;
                if (n == 0) {
                    return i;
                }
            }
        }
        return 1;
    }

    public boolean isUgly(int num) {
        if (num == 0) {
            return false;
        }
        while (num % 2 == 0) {
            num = num / 2;
        }
        while (num % 3 == 0) {
            num = num / 3;
        }
        while (num % 5 == 0) {
            num = num / 5;
        }
        return num == 1;
    }
}
