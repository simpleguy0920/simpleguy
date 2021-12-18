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
public class SolutionDP {
    public static void main(String[] args) {
        int[] coins = new int[]{2};
        SolutionDP solutionDP = new SolutionDP();
        System.out.println(solutionDP.waysToChange(10));
    }

    public int dpCoin(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        } else if (amount == 0) {
            return 0;
        }
        int result = Integer.MAX_VALUE;
        for (int coin : coins) {
            int value = dpCoin(coins, amount - coin) + 1;
            if (value <= 0) {
                continue;
            }
            if (value < result) {
                result = value;
            }
        }
        return result;
    }

    public int dpCoinArray(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int result = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                result = Math.min(result, dp[i - coin] + 1);
            }
            dp[i] = result;
        }
        System.out.println(Arrays.toString(dp));
        return dp[amount];
    }

    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int min = -1;
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                if (dp[i - coin] == -1) {
                    continue;
                }
                int count = dp[i - coin] + 1;
                if (min == -1 || min > count) {
                    min = count;
                }
            }
            dp[i] = min;
        }
        return dp[amount];
    }

    public int[] countBits(int num) {
        int[] dp = new int[num + 1];
        dp[0] = 0;
        for (int i = 1; i <= num; i++) {
            int value = i % 2;
            int value1 = i / 2;
            dp[i] = dp[value1] + value;
        }
        return dp;
    }

    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            int max = 0;
            for (int j = 1; j <= i - 1; j++) {
                int value = Math.max(dp[i - j] * j, (i - j) * j);
                if (value > max) {
                    max = value;
                }
            }
            dp[i] = max;
        }
        return dp[n];
    }

    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 10;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 10;
        for (int i = 2; i <= n; i++) {
            int result = 9;
            for (int j = 1; j < i; j++) {
                result = result * (10 - j);
            }
            dp[i] = result;
        }
        int count = 0;
        int result = 1;
        for (int i = 0; i <= n; i++) {
            count = count + dp[i];
            result = result * 10;
        }
        return count;
    }


    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> resultList = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return resultList;
        }
        List<Integer>[] dp = new List[nums.length];
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        dp[0] = list;
        for (int i = 1; i < nums.length; i++) {

        }
        return resultList;

    }

    public int calcMoneyAmount(int low, int hight, int[][] dp) {
        if (low >= hight) {
            return 0;
        }
        if (dp[low][hight] != 0) {
            return dp[low][hight];
        }
        int max = Integer.MAX_VALUE;
        for (int i = low; i <= hight; i++) {
            int result = i + Math.max(calcMoneyAmount(low, i - 1, dp), calcMoneyAmount(i + 1, hight, dp));
            if (result < max) {
                max = result;
            }
        }
        dp[low][hight] = max;
        return max;
    }

    public int getMoneyAmount(int n) {
        int[][] dp = new int[n][n];
        return calcMoneyAmount(1, n, dp);
    }

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 0;
        for (int i = 1; i <= target; i++) {
            int count = 0;
            for (Integer num : nums) {
                if (i - num == 0) {
                    count = count + 1;
                } else if (i - num > 0) {
                    count = count + dp[i - num];
                }
            }
            dp[i] = count;
        }
        return dp[target];
    }

    public boolean isSubsequence(String s, String t) {
        int[] dp = new int[t.length()];
        for (int i = 0; i < s.length(); i++) {
            int index;
            if (i == 0) {
                index = t.indexOf(s.charAt(i));
            } else {
                index = t.indexOf(s.charAt(i), dp[i - 1]);
            }
            if (index < 0) {
                return false;
            }
            dp[i] = index + 1;
        }
        return true;
    }

    public int numberOfArithmeticSlices2(int[] a) {
        if (a == null || a.length < 3) {
            return 0;
        }
        int[] dp = new int[a.length];
        dp[0] = 0;
        dp[1] = 0;
        int sum = 0;
        for (int i = 2; i < a.length; i++) {
            if (a[i] - a[i - 1] == a[i - 1] - a[i - 2]) {
                dp[i] = dp[i - 1] + 1;
                sum = sum + dp[i];
            } else {
                dp[i] = 0;
            }
        }
        return sum;
    }

    public int numberOfArithmeticSlices(int[] A) {
        int r = 0;
        for (int i = 0; i < A.length - 2; ++i) {
            int len = 2;
            while (i + 2 < A.length && A[i + 2] - A[i + 1] == A[i + 1] - A[i]) {
                len++;
                i++;
            }
            r += (len - 1) * (len - 2) / 2;
        }
        return r;
    }

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum = sum + num;
        }
        if (sum % 2 == 1) {
            return false;
        }
        Set<Integer> set = new HashSet<>();

        int half = sum / 2;
        Arrays.sort(nums);
        boolean[] result = new boolean[nums.length];
        return canPartition(nums, result, 0, half);
    }

    public boolean canPartition(int[] nums, boolean[] result, int value, int half) {
        if (value == half) {
            return true;
        } else if (value > half) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!result[i]) {
                result[i] = true;
                boolean flag = canPartition(nums, result, value + num, half);
                if (flag) {
                    return true;
                }
                result[i] = false;
            }
        }
        return false;
    }

    public int waysToStep(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else if (n == 3) {
            return 4;
        }
        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            dp[i] = dp[i] % 1000000007;
        }
        return (int) dp[n];

    }

    public int waysToChange1(int n) {
        long result = 0;
        for (int i = 0; i <= n / 25; i++) {
            for (int j = 0; j <= n / 10; j++) {
                for (int k = 0; k <= n / 5; k++) {
                    if (i * 25 + j * 10 + k * 5 <= n) {
                        result++;
                    }
                }
            }
        }
        return (int) (result % 1000000007);
    }

    public int waysToChange(int n) {
        int[] coins = new int[]{1, 5, 10, 25};
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= n; i++) {
                dp[i] = (dp[i] + dp[i - coin]) % 1000000007;
            }
        }
        return dp[n];
    }
}
