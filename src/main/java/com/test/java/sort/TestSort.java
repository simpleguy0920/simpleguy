package com.test.java.sort;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;


public class TestSort {

    public static int[] bubleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int value = array[i];
                    array[i] = array[j];
                    array[j] = value;
                }
            }
        }
        return array;
    }

    public static int[] selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minValue = array[i];
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < minValue) {
                    minValue = array[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                array[minIndex] = array[i];
                array[i] = minValue;
            }
        }
        return array;
    }

    public static int[] insertSort(int[] array) {
        for (int i = 1; i < array.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    int value = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = value;
                }
            }
        }
        return array;
    }

    public static void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        //最左值作为基准值
        int key = array[start];
        int i = start;
        int j = end;
        while (i < j) {
            // 尾部与基准值比较，找小于基准值
            while (i < j && array[j] >= key) {
                j--;
            }
            // 尾部与基准值比较，找大于基准值
            while (i < j && array[i] <= key) {
                i++;
            }
            //这两个值未重合
            if (i < j) {
                //交换操作
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        //最左值与分割位交换
        array[start] = array[i];
        array[i] = key;
        //比较左边
        quickSort(array, start, i - 1);
        //比较右边
        quickSort(array, j + 1, end);

    }

    public static void main(String[] args) {
        int[] array = new int[10000];
        int[] array1 = new int[10000];
        int[] array2 = new int[10000];
        int[] array3 = new int[10000];
        int[] array4 = new int[10000];

        for (int i = 0; i < array.length; i++) {
            array[i] = RandomUtils.nextInt(100000);
            array1[i] = array[i];
            array2[i] = array[i];
            array3[i] = array[i];
            array4[i] = array[i];

        }

        System.out.println(Arrays.toString(array));
        long t0 = System.currentTimeMillis();
        int[] sortedArray = bubleSort(array);
        long t1 = System.currentTimeMillis();
        int[] selectedArray = selectSort(array1);
        long t2 = System.currentTimeMillis();
        int[] insertArray = insertSort(array2);
        long t3 = System.currentTimeMillis();
        quickSort(array3, 0, array3.length - 1);
        long t4 = System.currentTimeMillis();
        Arrays.sort(array4);
        long t5 = System.currentTimeMillis();

        System.out.println(t1 - t0);
        System.out.println(t2 - t1);
        System.out.println(t3 - t2);
        System.out.println(t4 - t3);
        System.out.println(t5 - t4);

        System.out.println(ArrayUtils.toString(sortedArray));
        System.out.println(ArrayUtils.toString(selectedArray));
        System.out.println(ArrayUtils.toString(insertArray));
        System.out.println(ArrayUtils.toString(array3));
        System.out.println(ArrayUtils.toString(array4));

    }
}
