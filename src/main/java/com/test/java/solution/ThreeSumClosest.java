package com.test.java.solution;

public class ThreeSumClosest {
    public static int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < length - 2; i++) {
            for (int j = i + 1; j < length - 1; j++) {
                for (int k = j + 1; k < length; k++) {
                    int value = nums[i] + nums[j] + nums[k] - target;
                    if (value < 0) {
                        value = 0 - value;
                    }
                    if (value < min) {
                        min = value;
                        sum = nums[i] + nums[j] + nums[k];
                    }
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        System.out.println(threeSumClosest(nums, target));
    }
}
