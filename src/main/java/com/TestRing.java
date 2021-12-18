package com;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class TestRing {
    public static void main(String[] args) {
        int[] array = new int[41];
        Arrays.fill(array, 1);
        int index = 0;
        int length = 41;
        int count = 1;
        while (length >= 3) {
            if (array[index] == 1) {
                count++;
                if (count == 3) {
                    count = 1;
                    array[index] = 0;
                    length--;
                }
            }
            index++;
            if (index == 40) {
                index = 0;
            }
        }
        System.out.println(ArrayUtils.toString(array));
    }
}
