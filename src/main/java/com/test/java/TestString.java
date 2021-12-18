package com.test.java;

public class TestString {

    public static void procecc(int src, int dest) {
        System.out.println("src=" + src + " dest=" + dest);
        if (src == dest) {
            return;
        } else if (src > dest) {
            src = src - 1;
            procecc(src, dest);
        } else if (src < dest) {
            int between1 = dest - src;
            int bwtween2 = Math.abs(dest - 2 * src);
            if (bwtween2 < between1) {
                src = 2 * src;
                procecc(src, dest);
            } else {
                src = src + 1;
                procecc(src, dest);
            }
        }
    }

    public static void main(String[] args) {
        procecc(1, 15);
    }

}
