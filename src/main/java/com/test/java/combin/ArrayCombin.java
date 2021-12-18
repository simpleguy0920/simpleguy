package com.test.java.combin;

import org.apache.commons.lang3.ArrayUtils;

public class ArrayCombin {
    public static void main(String[] args) {
        int size = 3;
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        int[] result = new int[0];
        combine(array, result, size);
    }

    public static void combine(int[] src, int[] result, int length) {
        if (result.length == length) {
            System.out.println(ArrayUtils.toString(result));
        } else {
            for (int i = 0; i < src.length; i++) {
                if (!ArrayUtils.contains(result, src[i])) {
                    int[] newResult = ArrayUtils.add(result, src[i]);
                    combine(src, newResult, length);
                }
            }
        }
    }
}
