package com.test.al;


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TestQueue {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(10, Comparator.reverseOrder());
        for (int i = 0; i < 100; i++) {
            queue.offer(1);
            if (queue.size() > 10) {
                queue.poll();
            }
        }
        System.out.println(Arrays.toString(queue.toArray()));
    }
}
