package com.test.java.solution;

public class IsUgly {
    public static void main(String[] args) {
        IsUgly isUgly = new IsUgly();
        System.out.println(isUgly.isUgly(6));
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
