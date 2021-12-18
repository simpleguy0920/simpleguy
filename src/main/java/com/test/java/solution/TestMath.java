package com.test.java.solution;


import java.util.*;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TestMath {

    public static void main(String[] args) {
        TestMath testMath = new TestMath();
        System.out.println(testMath.numWays(3, new int[][]{{0, 1}, {0, 2}, {2, 1}, {1, 2}, {1, 0}, {2, 0}}, 5));
    }

    public int countPrimes(int n) {
        boolean[] isPrimes = new boolean[n + 1];
        for (int i = 0; i < isPrimes.length; i++) {
            isPrimes[i] = true;
        }
        isPrimes[0] = false;
        isPrimes[1] = false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (isPrimes[i]) {
                for (int j = 2; j <= n / i; j++) {
                    int index = i * j;
                    if (index <= n) {
                        isPrimes[index] = false;
                    }

                }
            }
        }
        int sum = 0;
        for (boolean isPrime : isPrimes) {
            if (isPrime) {
                sum++;
            }
        }
        return sum;
    }

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int width1 = Math.abs(A - C);
        int height1 = Math.abs(B - D);
        int width2 = Math.abs(E - G);
        int height2 = Math.abs(F - H);
        int bothLeft = Math.max(A, E);
        int bothRight = Math.min(C, G);
        int bothTop = Math.min(D, H);
        int bothBottom = Math.max(B, F);
        int area = width1 * height1 + width2 * height2;
        if (bothLeft < bothRight && bothTop > bothBottom) {
            area = area - (bothRight - bothLeft) * (bothTop - bothBottom);
        }
        return area;
    }

    public int longestConsecutive(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>(nums.length);
        list.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != list.get(list.size() - 1)) {
                list.add(nums[i]);
            }
        }
        nums = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }
        int max = 1;
        int currentLength = 1;
        int start = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                if (i == nums.length - 1) {
                    currentLength = i - start + 1;
                    max = Math.max(currentLength, max);
                }
            } else {
                currentLength = i - 1 - start + 1;
                max = Math.max(currentLength, max);
                start = i;
            }
        }
        return max;
    }

    public int[] getTriggerTime(int[][] increase, int[][] requirements) {

        int[] xArray = new int[increase.length + 1];
        int[] yArray = new int[increase.length + 1];
        int[] zArray = new int[increase.length + 1];
        int[] result = new int[requirements.length];
        int x = 0, y = 0, z = 0;
        xArray[0] = 0;
        yArray[0] = 0;
        zArray[0] = 0;
        for (int i = 0; i < increase.length; i++) {
            int[] array = increase[i];
            x = x + array[0];
            y = y + array[1];
            z = z + array[2];
            xArray[i + 1] = x;
            yArray[i + 1] = y;
            zArray[i + 1] = z;
        }
        for (int i = 0; i < requirements.length; i++) {
            int[] requirement = requirements[i];
            int indexX = search(xArray, requirement[0]);
            if (indexX == -1) {
                result[i] = -1;
            }
            int indexY = search(yArray, requirement[1]);
            if (indexY == -1) {
                result[i] = -1;
            }

            int indexZ = search(zArray, requirement[2]);
            if (indexZ == -1) {
                result[i] = -1;
            }
            int min = Math.max(Math.max(indexX, indexY), indexZ);
            result[i] = min;
        }
        return result;

    }

    public int search(int[] array, int key) {
        if (key > array[array.length - 1]) {
            return -1;
        }
        if (key <= array[0]) {
            return 0;
        }
        int low = 0;
        int high = array.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (array[mid] == key) {
                high = mid;
            } else if (array[mid] < key) {
                low = mid + 1;
            } else if (array[mid] > key) {
                high = mid;
            }
        }
        return low;
    }

    public int numWays(int n, int[][] relation, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] ints : relation) {
            List<Integer> list = map.get(ints[0]);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(ints[1]);
            map.put(ints[0], list);
        }
        int count = 0;
        Deque<List<Integer>> deque = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        deque.offer(list);
        int time = 0;
        while (!deque.isEmpty()) {
            time++;
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                List<Integer> current = deque.poll();
                List<Integer> nextArray = map.get(current.get(current.size() - 1));
                if (nextArray != null) {
                    for (int next : nextArray) {
                        if (next == n - 1) {
                            if (time == k) {
                                count++;
                            } else {
                                List<Integer> nextList = new ArrayList<>(current);
                                nextList.add(next);
                                deque.offer(nextList);
                            }
                        } else {
                            List<Integer> nextList = new ArrayList<>(current);
                            nextList.add(next);
                            deque.offer(nextList);
                        }
                    }
                }
            }
            if (time > k) {
                break;
            }
        }
        return count;

    }
}
