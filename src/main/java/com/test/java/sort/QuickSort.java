package com.test.java.sort;

import org.apache.commons.lang.math.RandomUtils;

import java.util.Arrays;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class QuickSort {

    public static void sort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int value = array[start];
        int left = start;
        int right = end;
        while (left < right) {
            while (left < right && array[right] >= value) {
                right--;
            }
            while (left < right && array[left] <= value) {
                left++;
            }
            if (left < right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }
        array[start] = array[left];
        array[left] = value;
        System.out.println("start=" + start + " end=" + end + " value=" + value + " array=" + Arrays.toString(array));
        sort(array, start, left - 1);
        sort(array, right + 1, end);
    }

    public static void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int key = (start + end) / 2;
        while (start <= end) {
            while (array[end] >= key) {
                end--;
            }
            while (array[start] >= key) {
                start++;
            }
            if (start < end) {
                int temp = array[start];
                array[start] = end;
                array[end] = temp;
                start++;
                end--;
            }
        }

    }


    public static void main(String[] args) {
        int[] array = new int[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = RandomUtils.nextInt(100);
        }
        System.out.println(Arrays.toString(array));
        sort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));

    }
}
