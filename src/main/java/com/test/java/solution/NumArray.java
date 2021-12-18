package com.test.java.solution;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class NumArray {

    private final int[] dp;

    public NumArray(int[] nums) {
        dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = dp[i] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        if (i == 0) {
            return dp[j];
        } else {
            return dp[j] - dp[i - 1];
        }
    }


}
