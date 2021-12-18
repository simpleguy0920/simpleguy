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
public class Solution7 {
    public static void main(String[] args) {
        Solution7 solution7 = new Solution7();
        System.out.println(solution7.longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));


    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        ArrayDeque<Integer> deque = new ArrayDeque<>(k);
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                deque.addFirst(i);
            } else {
                int start = i - k + 1;
                //移除越界
                while (start >= 0 && !deque.isEmpty() && deque.getFirst() < start) {
                    deque.removeFirst();
                }
                if (nums[i] >= nums[deque.getFirst()]) {
                    deque.addFirst(i);
                    while (deque.size() > 1) {
                        deque.removeLast();
                    }
                } else {
                    deque.addLast(i);
                }
            }
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.getFirst()];
            }
        }
        return result;
    }

    public double[] medianSlidingWindow(int[] nums, int k) {

        double[] result = new double[nums.length - k + 1];
        ArrayDeque<Integer> lower = new ArrayDeque<>(k);
        ArrayDeque<Integer> greater = new ArrayDeque<>(k);

        Arrays.sort(nums, 0, k - 1);
        for (int i = 0; i < k; i++) {
            if (i < k / 2) {
                lower.addFirst(i);
            } else {
                greater.addFirst(i);
            }

        }
        for (int i = 0; i < nums.length - k; i++) {
            Integer remove = nums[i];
            int add = nums[i + k - 1];
            if (remove != add) {


            }


        }

        return result;

    }

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] array = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            array[s1.charAt(i) - 'a']++;
        }
        int validCount = 0;
        int needCount = 0;
        for (int i : array) {
            if (i > 0) {
                needCount++;
            }
        }
        int start = 0;
        int end = 0;
        int[] resultArray = new int[26];
        while (end < s2.length()) {
            char ch = s2.charAt(end);
            int indexEnd = ch - 'a';
            end++;
            resultArray[indexEnd]++;
            if (resultArray[indexEnd] == array[indexEnd]) {
                validCount++;
                if (validCount == needCount) {
                    return true;
                }
            } else {
                while (resultArray[indexEnd] > array[indexEnd]) {
                    char chStart = s2.charAt(start);
                    int indexStart = chStart - 'a';
                    resultArray[indexStart]--;
                    if (resultArray[indexStart] < array[indexStart]) {
                        validCount--;
                    }
                    start++;
                }
            }
        }
        return false;
    }

    public int maxTurbulenceSize(int[] arr) {
        int[] flag = new int[arr.length - 1];
        for (int i = 0; i < arr.length - 1; i++) {
            flag[i] = Integer.signum(arr[i + 1] - arr[i]);
        }
        int size = 0;
        int start = 0;
        int end = 1;
        int sum = 2;
        while (end < flag.length) {
            if (flag[end] == 0) {
                start = end;
                end = end + 1;
                sum = 2;
            } else if (flag[end] != flag[start]) {
                sum++;
                size = Math.max(size, sum);
                start++;
                end++;
            } else {
                start = end;
                end = end + 1;
                sum = 2;
            }
        }
        return size;


    }


    public int subarraysWithKDistinct(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        int start = 0;
        int end = 0;
        while (end < A.length) {
            map.merge(A[end], 1, Integer::sum);
            end++;
            if (map.size() == K) {
                result++;
            }
            while (map.size() > K) {
                int count = map.get(A[start]);
                if (count == 1) {
                    map.remove(A[start]);
                    result++;
                } else {
                    count--;
                    map.put(A[start], count);
                }
            }
        }
        return result;

    }

    public int longestOnes(int[] A, int K) {
        int result = 0;
        int start = 0;
        int end = 0;
        int need = 0;
        while (end < A.length) {

            if (A[end] == 1) {
                result = Math.max(result, end - start + 1);
                end++;

            } else {
                need++;
                while (need > K) {
                    if (A[start] != 1) {
                        need--;
                    }
                    start++;
                }
                result = Math.max(result, end - start + 1);
                end++;

            }
        }
        return result;
    }


    public int equalSubstring(String s, String t, int maxCost) {
        int result = 0;
        int start = 0;
        int end = 0;
        int cost = 0;
        while (end < t.length()) {
            char ch1 = s.charAt(end);
            char ch2 = t.charAt(end);
            cost = cost + Math.abs(ch1 - ch2);
            while (cost > maxCost) {
                cost = cost - Math.abs(s.charAt(start) - t.charAt(start));
                start++;
            }
            result = Math.max(result, end - start + 1);
            end++;
        }
        return result;

    }


    public int maxScore(int[] cardPoints, int k) {
        int total = 0;
        for (int cardPoint : cardPoints) {
            total = total + cardPoint;
        }
        if (k == cardPoints.length) {
            return total;
        }
        int count = cardPoints.length - k;
        int minSum = 0;
        int sum = 0;
        for (int i = 0; i < cardPoints.length; i++) {
            if (i < count) {
                sum = sum + cardPoints[i];
                minSum = sum;
            } else {
                sum = sum + cardPoints[i] - cardPoints[i - count];
                minSum = Math.min(sum, minSum);
            }
        }
        return total - minSum;

    }


    public int longestSubarray(int[] nums, int limit) {
        if (limit < 0) {
            return 0;
        }
        int start = 0;
        int end = 0;
        int min = 0;
        int max = 0;
        while (end < nums.length) {
            if (nums[end] > max) {
                max = nums[end];
            }
            if (nums[end] < min) {
                min = nums[end];
            }
            while (max - min > limit) {


                start++;
            }
            end++;

        }
        return max;
    }

    public int maxVowels(String s, int k) {
        int result = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (i < k) {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'u' || ch == 'o') {
                    count++;
                    result = count;
                }
            } else {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'u' || ch == 'o') {
                    count++;
                }
                char removeCh = s.charAt(i - k);
                if (removeCh == 'a' || removeCh == 'e' || removeCh == 'i' || removeCh == 'u' || removeCh == 'o') {
                    count--;
                }
                result = Math.max(result, count);

            }
        }
        return result;

    }


    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int result = 0;
        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i] == 0) {
                result = result + customers[i];
                customers[i] = 0;
            }
        }
        int max = 0;
        int sum = 0;
        for (int i = 0; i < customers.length; i++) {
            sum = sum + customers[i];
            if (i >= X) {
                sum = sum - customers[i - X];
            }
            max = Math.max(max, sum);
        }
        result = result + max;
        return result;
    }

    public String compressString(String S) {
        if (S.length() <= 2) {
            return S;
        }

        StringBuilder builder = new StringBuilder();
        char last = S.charAt(0);
        int count = 1;
        for (int i = 1; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (i < S.length()) {
                if (ch == last) {
                    count++;
                } else {
                    builder.append(last).append(count);
                    last = ch;
                    count = 1;
                }
            } else {
                if (ch == last) {
                    count++;
                    builder.append(last).append(count);
                } else {
                    builder.append(last).append(count);
                    builder.append(ch).append(1);
                }
            }
            if (builder.length() >= S.length()) {
                return S;
            }
        }
        return builder.toString();
    }


    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        boolean changeFlag = false;
        for (int i = nums.length - 1; i >= 1; i++) {
            if (nums[i] > nums[i - 1]) {
                int temp = nums[i];
                nums[i] = nums[i - 1];
                nums[i - 1] = temp;
                changeFlag = true;
                break;
            }
        }
        if (changeFlag) {
            return;
        } else {
            int start = 0;
            int end = nums.length - 1;
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
            return;
        }
    }

    public boolean canMakeArithmeticProgression(int[] arr) {
        if (arr == null || arr.length == 1) {
            return true;
        }
        Arrays.sort(arr);
        int value = arr[1] - arr[0];
        for (int j = 2; j < arr.length; j++) {
            if (value != (arr[j] - arr[j - 1])) {
                return false;
            }
        }
        return true;
    }

    public boolean possibleBipartition(int N, int[][] dislikes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] dislike : dislikes) {
            List<Integer> list1 = map.get(dislike[0]);
            if (list1 == null) {
                list1 = new ArrayList<>();
                list1.add(dislike[1]);
                map.put(dislike[0], list1);
            } else {
                list1.add(dislike[1]);
            }
            List<Integer> list2 = map.get(dislike[1]);
            if (list2 == null) {
                list2 = new ArrayList<>();
                list2.add(dislike[0]);
                map.put(dislike[1], list2);
            } else {
                list2.add(dislike[0]);
            }
        }
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        int i = 0;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if (i == 0) {
                setA.add(entry.getKey());
                setB.addAll(entry.getValue());
            } else {

            }
            i++;
        }

        return false;
    }

}
