package com.test.java.solution;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class SolutionHeap {

    public static void main(String[] args) {
        SolutionHeap solutionHeap = new SolutionHeap();
        System.out.println(solutionHeap.eatenApples1(new int[]{1}, new int[]{2}));
    }

    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int sum = 0;
        for (int i = 0; i < apples.length; i++) {
            int num = apples[i];
            int badTime = i + days[i];
            for (int j = 0; j < num; j++) {
                queue.add(badTime);
            }
            while (!queue.isEmpty() && queue.peek() <= i) {
                queue.poll();
            }
            if (!queue.isEmpty()) {
                queue.poll();
                sum++;
            }
        }
        int size = queue.size();
        for (int i = apples.length; i < apples.length + size; i++) {
            while (!queue.isEmpty() && queue.peek() <= i) {
                queue.poll();
            }
            if (!queue.isEmpty()) {
                queue.poll();
                sum++;
            } else {
                break;
            }
        }
        return sum;
    }

    public int eatenApples1(int[] apples, int[] days) {
        int sum = 0;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < apples.length; i++) {
            int num = apples[i];
            int badTime = i + days[i];
            map.merge(badTime, num, Integer::sum);
            while (!map.isEmpty() && map.firstKey() <= i) {
                map.remove(map.firstKey());
            }
            if (!map.isEmpty()) {
                Map.Entry<Integer, Integer> entry = map.firstEntry();
                sum++;
                if (entry.getValue() == 1) {
                    map.remove(entry.getKey());
                } else {
                    map.put(entry.getKey(), entry.getValue() - 1);
                }
            }
        }
        if (map.isEmpty()) {
            return sum;
        }

        int size = map.lastKey();
        for (int i = apples.length; i < apples.length + size; i++) {
            while (!map.isEmpty() && map.firstKey() <= i) {
                map.remove(map.firstKey());
            }
            if (!map.isEmpty()) {
                Map.Entry<Integer, Integer> entry = map.firstEntry();
                sum++;

                if (entry.getValue() == 1) {
                    map.remove(entry.getKey());
                } else {
                    map.put(entry.getKey(), entry.getValue() - 1);
                }
            } else {
                break;
            }
        }
        return sum;

    }


}
