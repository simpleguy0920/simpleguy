package com.test.java.solution;


import org.apache.commons.lang.math.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class SecondBigNumber {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int value = RandomUtils.nextInt(100);
            list.add(value);
            System.out.println(value);
        }
        int biggestNumber = 0;
        int secondBiggestNumber = 0;
        for (int i = 0; i < list.size(); i++) {
            int value = list.get(i);
            if (i == 0) {
                biggestNumber = value;
            } else if (i == 1) {
                if (value >= biggestNumber) {
                    secondBiggestNumber = biggestNumber;
                    biggestNumber = value;
                } else {
                    secondBiggestNumber = value;
                }
            } else {
                if (value > biggestNumber) {
                    secondBiggestNumber = biggestNumber;
                    biggestNumber = value;
                } else if (value > secondBiggestNumber) {
                    secondBiggestNumber = value;
                }
            }
        }
        System.out.println("biggestNumber=" + biggestNumber);
        System.out.println("secondBiggestNumber=" + secondBiggestNumber);

    }
}
