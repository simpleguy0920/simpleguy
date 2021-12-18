package com.test.java.solution;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TestTopN {
    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(10000);
        }
        System.out.println(Arrays.toString(array));
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i : array) {
            queue.add(i);
            if (queue.size() > 10) {
                queue.poll();
            }
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}
