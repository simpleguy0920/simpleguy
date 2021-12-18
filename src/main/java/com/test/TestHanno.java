package com.test;

public class TestHanno {

    public static void main(String[] args) {
        TestHanno testHanno = new TestHanno();
        testHanno.move(40, "a", "b", "c");
    }

    public void move(int n, String start, String middle, String end) {
        if (n == 1) {
            System.out.println("form start " + start + " to " + end);
            return;
        }
        move(n - 1, start, end, middle);
        move(1, start, middle, end);
        move(n - 1, middle, start, end);


    }
}
