package com.test.java.solution;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TwoSum {
    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        twoSum.test(new ArrayDeque<>());
        twoSum.test(new LinkedList<>());
        twoSum.test(new ConcurrentLinkedQueue<>());

    }

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int value = target - nums[i];
            if (map.containsKey(value)) {
                return new int[]{i, map.get(value)};

            }
            map.put(nums[i], i);
        }
        return null;
    }

    public void test(Queue<Integer> queue) {
        queue.offer(1);
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            queue.offer(1);
        }
        long t2 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            queue.poll();
        }
        long t3 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            queue.offer(1);
            queue.poll();
        }
        long t4 = System.currentTimeMillis();
        System.out.println(t4 - t3 + "|" + (t3 - t2) + "|" + (t2 - t1));
    }
}
