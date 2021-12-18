package com.test;

import org.apache.commons.lang.math.RandomUtils;

public class TestRandom {
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(RandomUtils.nextInt(10));
        }
    }
}
