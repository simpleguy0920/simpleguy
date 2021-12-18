package com.test.java;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.math.RandomUtils;

public class TestPack {

    public static void main(String[] args) {
        int[] weightArray = getPakage(10);
        int[] priceArray = getPakage(10);
        System.out.println("weight");
        System.out.println(ArrayUtils.toString(weightArray));
        System.out.println("price");
        System.out.println(ArrayUtils.toString(priceArray));

    }


    static int[] getPakage(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = RandomUtils.nextInt(20);
        }
        return array;
    }
}
