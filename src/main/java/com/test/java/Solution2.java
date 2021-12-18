package com.test.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class Solution2 {
    private static boolean flag = false;

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        List<List<Integer>> list = solution2.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    public static int trap(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int maxSize = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int high = Math.min(height[i], height[j]);
                int width = j - i;
                int size = width * high;
                if (size > maxSize) {
                    maxSize = size;
                }
            }
        }
        return maxSize;

    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return resultList;
        }
        Arrays.sort(candidates);
        LinkedList<Integer> result = new LinkedList<>();
        conbine(result, candidates, target, resultList, 0);
        return resultList;
    }

    public void conbine(LinkedList<Integer> result, int[] candidates, int target, List<List<Integer>> resultList, int index) {
        int sum = 0;
        for (Integer integer : result) {
            sum = sum + integer;
        }
        if (sum == target) {
            resultList.add(new ArrayList<>(result));
        } else if (sum < target) {
            for (int i = index; i < candidates.length; i++) {
                if (i > index && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                Integer candidate = candidates[i];
                if (!result.contains(candidate)) {
                    result.add(candidate);
                    conbine(result, candidates, target, resultList, i + 1);
                    result.removeLast();
                }
            }
        }
    }

    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        return flag;
    }

    public void backTrace(int[] nums, int index) {
        if (index == nums.length - 1) {
            flag = true;
        } else if (nums[index] == 0) {
            return;
        } else {
            for (int i = 1; i < nums[index] && i < nums.length - 1; i++) {
                backTrace(nums, i);
            }
        }
    }
}
