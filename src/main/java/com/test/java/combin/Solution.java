package com.test.java.combin;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[][] arrayParam = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(-1));
        list.add(Arrays.asList(-2, -3));

        int result = solution.lengthOfLIS(new int[]{4, 10, 4, 3, 8, 9});
        System.out.println(StringUtils.join("result=" + result));
    }

    public int longestValidParentheses(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        char[] array = s.toCharArray();
        int[] result = new int[array.length];
        result[0] = 0;
        // 数组结束位置
        for (int i = 1; i < array.length; i++) {
            int max = result[i - 1];
            if (array[i] == '(') {
                result[i] = max;
            } else {
                int start = i - max - 1;
                if (isValidParenthesesProxy(array, start, i)) {
                    max = max + 2;
                }
                result[i] = max;
            }
        }
        return result[array.length - 1];
    }

    public boolean isValidParentheses(char[] array, int start, int end) {
        if (start < 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = start; i <= end; i++) {
            char ch = array[i];
            if (ch == '(') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    char element = stack.pop();
                    if (ch == ')' && element != '(') {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    public boolean isValidParenthesesProxy(char[] array, int start, int end) {
        boolean flag = isValidParentheses(array, start, end);
        StringBuilder builder = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (i >= 0) {
                builder.append(array[i]);
            } else {
                System.out.println("error" + start + "-" + end);
            }
        }
        System.out.println(builder + ":" + flag);
        return flag;

    }

    public String longestPalindrome(String s) {
        if (s == null) {
            return "";
        }
        if (s.length() < 2) {
            return s;
        }
        int start = 0;
        int end = 0;
        char[] array = s.toCharArray();
        int[] result = new int[array.length + 1];
        result[1] = 1;
        for (int i = 2; i <= array.length; i++) {
            int max = result[i - 1];
            if (isPalindrome(array, i - max - 2, i - 1)) {
                start = i - max - 2;
                end = i - 1;
                max = end - start + 1;
            } else if (isPalindrome(array, i - max - 1, i - 1)) {
                start = i - max - 1;
                end = i - 1;
                max = end - start + 1;
            }
            result[i] = max;
        }
        return s.substring(start, end + 1);
    }

    boolean isPalindrome(char[] array, int start, int end) {
        if (start < 0) {
            return false;
        }
        System.out.println(Arrays.copyOfRange(array, start, end));
        int i = start;
        int j = end;
        while (i < j) {
            if (array[i] != array[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String s = String.valueOf(x);
        char[] array = s.toCharArray();
        if (array.length == 1) {
            return true;
        }
        int i = 0;
        int j = array.length - 1;
        while (i < j) {
            if (array[i] != array[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }


    public String longestCommonPrefix(String[] strs) {
        if (strs == null) {
            return null;
        }
        if (strs.length == 1) {
            return strs[0];
        }
        char[] src = strs[0].toCharArray();
        int length = src.length;
        for (int i = 1; i < strs.length; i++) {
            char[] array = strs[i].toCharArray();
            int max = Math.min(src.length, array.length);
            if (max < length) {
                length = max;
            }
            for (int j = 0; j < max; j++) {
                if (src[j] != array[j]) {
                    if (j == 0) {
                        return "";
                    } else if (j < length) {
                        length = j;
                    }
                }
            }
        }
        return new String(src, 0, length);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            map.put(num, i);
        }
        Set<String> resultSet = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                int value = 0 - nums[i] - nums[j];
                if (map.containsKey(value)) {
                    int position = map.get(value);
                    if (position > j) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(value);
                        Collections.sort(list);
                        String resultString = list.get(0).toString() + list.get(1) + list.get(2);
                        if (resultSet.contains(resultString)) {
                            continue;
                        }
                        resultSet.add(resultString);
                        resultList.add(list);
                    }
                }
            }
        }
        return resultList;
    }


    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4) {
            return new ArrayList<>();
        }
        List<List<Integer>> resultList = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>(nums.length * 4 / 3);
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    continue;
                }
                for (int k = j + 1; k < nums.length - 1; k++) {
                    if (k > j + 1 && nums[k - 1] == nums[k]) {
                        continue;
                    }
                    int value = target - nums[i] - nums[j] - nums[k];
                    if (nums[i] == nums[j] && nums[i] == nums[k] && nums[i] == value) {
                        continue;
                    }
                    if (map.containsKey(value) && map.get(value) > k) {
                        List<Integer> integerList = new ArrayList<>();
                        integerList.add(nums[i]);
                        integerList.add(nums[j]);
                        integerList.add(nums[k]);
                        integerList.add(value);
                        resultList.add(integerList);
                    }
                }
            }
        }
        return resultList;

    }

    public int uniquePaths(int m, int n) {
        int[][] array = new int[m + 1][n + 1];
        return uniquePaths(m, n, array);
    }

    public int uniquePaths(int m, int n, int[][] array) {
        if (m <= 1) {
            return 1;
        } else if (n <= 1) {
            return 1;
        } else {
            if (array[m][n] != 0) {
                return array[m][n];
            } else {
                int result = uniquePaths(m - 1, n, array) + uniquePaths(m, n - 1, array);
                array[m][n] = result;
                return result;
            }
        }
    }

    public boolean isValid(String s) {
        if (s == null && s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        char[] array = s.toCharArray();
        for (char ch : array) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    char element = stack.pop();
                    if (ch == ')' && element != '(') {
                        return false;
                    } else if (ch == ']' && element != '[') {
                        return false;
                    } else if (ch == '}' && element != '{') {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] array = new int[obstacleGrid.length][obstacleGrid[0].length];
        return uniquePathsWithObstacles(obstacleGrid, array, obstacleGrid.length - 1, obstacleGrid[0].length - 1);
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid, int[][] array, int m, int n) {
        if (m < 0 || n < 0) {
            return 0;
        } else if (obstacleGrid[m][n] == 1) {
            return 0;
        } else if (array[m][n] != 0) {
            return array[m][n];
        } else if (m == 0 && n == 0) {
            return 1;
        } else {
            int result = uniquePathsWithObstacles(obstacleGrid, array, m - 1, n) + uniquePathsWithObstacles(obstacleGrid, array, m, n - 1);
            array[m][n] = result;
            return result;
        }
    }

    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];

        return minPathSum(grid, dp, grid.length - 1, grid[0].length - 1);
    }

    public int minPathSum(int[][] grid, int[][] dp, int m, int n) {
        if (dp[m][n] != 0) {
            return dp[m][n];
        }
        int result;
        if (m == 0 && n == 0) {
            result = grid[0][0];
        } else if (m == 0) {
            result = minPathSum(grid, dp, m, n - 1) + grid[m][n];
        } else if (n == 0) {
            result = minPathSum(grid, dp, m - 1, n) + grid[m][n];
        } else {
            result = Math.min(minPathSum(grid, dp, m, n - 1), minPathSum(grid, dp, m - 1, n)) + grid[m][n];
        }
        dp[m][n] = result;
        return result;
    }

    public int climbStairs(int n) {
        int[] array = new int[n + 1];
        array[0] = 0;
        array[1] = 1;
        for (int i = 2; i <= n; i++) {
            array[i] = array[i - 1] + array[1];
        }
        return array[n];
    }

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length()];
        if (s.charAt(0) != '0') {
            dp[0] = 1;
        }
        for (int i = 1; i < s.length(); i++) {
            char ch = s.charAt(i);
            char before = s.charAt(i - 1);
            if (ch == '0') {
                if (before == '1' || before == '2') {
                    if (i == 1) {
                        dp[i] = 1;
                    } else {
                        dp[i] = dp[i - 2];
                    }
                } else {
                    return 0;
                }
            } else if (before == '1' || before == '2' && (ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6')) {
                if (i == 1) {
                    dp[i] = dp[i - 1] + 1;
                } else {
                    dp[i] = dp[i - 1] + dp[i - 2];
                }
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[s.length() - 1];
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int[][] dp = new int[triangle.size()][triangle.size()];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                } else if (j < i) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                }
            }
        }
        int min = dp[triangle.size() - 1][0];
        for (int i = 0; i < triangle.size(); i++) {
            min = Math.min(min, dp[triangle.size() - 1][i]);
        }
        return min;

    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[] result = new int[prices.length];
        result[0] = 0;
        int minIndex = 0;
        for (int i = 1; i < result.length; i++) {
            if (prices[i] - prices[minIndex] > result[i - 1]) {
                result[i] = prices[i] - prices[minIndex];
            } else {
                result[i] = result[i - 1];
                if (prices[i] < prices[minIndex]) {
                    minIndex = i;
                }
            }
        }
        return result[result.length - 1];
    }


    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (i == 0) {
                dp[i] = set.contains(s.substring(0, 1));
            } else {
                for (int j = 0; j <= i; j++) {
                    if (j == 0) {
                        if (set.contains(s.substring(0, i + 1))) {
                            dp[i] = true;
                            break;
                        }
                    } else if (dp[j - 1] && set.contains(s.substring(j, i + 1))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }

        return dp[s.length() - 1];
    }

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int max = nums[i];
            int result = nums[i];
            for (int j = i - 1; j > 0; j--) {
                result = result * nums[j];
                if (result > max) {
                    max = result;
                }
            }
            dp[i] = Math.max(dp[i - 1], max);
        }
        return dp[nums.length - 1];
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length + 1];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (i == 1) {
                dp[i] = Math.max(dp[i - 1], nums[i]);
            } else {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }
        }
        return dp[nums.length - 1];
    }

    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //0 ->n-1
        int[] dp = new int[nums.length + 1];
        //1->n
        int[] dp2 = new int[nums.length + 1];

        dp[0] = 0;
        dp[1] = nums[0];

        dp2[0] = 0;
        dp2[1] = nums[1];

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + nums[i]);
        }
        return Math.max(dp[nums.length - 1], dp2[nums.length - 1]);
    }


    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int[][] dp = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                    }
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max * max;
    }

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int result = 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            result = Math.max(result, dp[i]);
        }
        return dp[nums.length - 1];

    }
}
