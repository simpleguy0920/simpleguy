package com.test.java.solution;

import java.util.Arrays;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TestArray {
    public static void main(String[] args) {
        TestArray testArray = new TestArray();

        int[] array = new int[]{1, 1, 1, 2, 2, 3};

        int[][] result = testArray.insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5});
        System.out.println(Arrays.deepToString(result));
    }

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return nums.length;
        }
        int num = nums[0];
        int count = 1;
        int index = 1;
        int[] result = new int[nums.length];
        result[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == num) {
                count++;
                if (count > 2) {
                    continue;
                } else {
                    num = nums[i];
                    result[index] = nums[i];
                    index++;
                }
            } else {
                count = 1;
                num = nums[i];
                result[index] = num;
                index++;
            }
        }
        for (int i = 0; i < index; i++) {
            nums[i] = result[i];
        }
        return index;
    }

    public int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return nums.length;
        }
        int num = nums[0];
        int count = 0;
        int start = 0;
        int end = 0;
        while (end < nums.length) {
            if (nums[end] == num) {
                count++;
                if (count > 2) {
                    end++;
                    continue;
                } else {
                    nums[start] = nums[end];
                    start++;
                    end++;
                }
            } else {
                count = 1;
                num = nums[end];
                nums[start] = num;
                start++;
                end++;
            }
        }
        return start;
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 1) {
            return new int[][]{newInterval};
        }
        int[] beginArray = new int[intervals.length];
        int beginIndex = 0;
        int endIndex = 0;
        int[] endArray = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            beginArray[i] = interval[0];
            endArray[i] = interval[1];
            if (interval[0] <= newInterval[0]) {
                beginIndex = i;
            }
            if (interval[1] < newInterval[1]) {
                endIndex = i;
            }
        }
        if (endIndex != intervals.length - 1) {
            endIndex++;
        }
        int[][] result = new int[beginIndex + intervals.length - endIndex][2];
        int index = 0;

        for (int i = 0; i < beginIndex; i++) {
            result[i] = intervals[i];
            index++;
        }
        int min = Math.min(intervals[beginIndex][0], newInterval[0]);
        int max = Math.max(intervals[endIndex][1], newInterval[1]);
        result[index] = new int[]{min, max};
        index++;
        for (int i = endIndex + 1; i < intervals.length; i++) {
            result[index] = intervals[i];
            index++;
        }
        return result;
    }
}
