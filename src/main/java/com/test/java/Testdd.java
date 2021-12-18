package com.test.java;

public class Testdd {
    public static void main(String[] args) {
        for (int i = 1; i <= 4; i++) {

            boolean flagA = (i != 1);
            boolean flagB = (i == 3);
            boolean flagc = (i == 4);
            boolean flagD = !flagc;
            int sum = 0;
            if (flagA) {
                sum++;
            }
            if (flagB) {
                sum++;
            }
            if (flagc) {
                sum++;
            }
            if (flagD) {
                sum++;
            }
            if (sum == 3) {
                System.out.println(i);
            }
        }
    }

}
