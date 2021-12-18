package com.test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TestDP1 {
    public static void main(String[] args) {
        TestDP1 testDP1 = new TestDP1();
        System.out.println(testDP1.canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
    }

    public int findMaxForm(String[] strs, int m, int n) {
        Arrays.sort(strs, Comparator.comparingInt(String::length));
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        int size = strs.length;
        int[] oneArray = new int[size];
        int[] zeroArray = new int[size];
        boolean[] used = new boolean[size];
        for (int j = 0; j < strs.length; j++) {
            String str = strs[j];
            int zeroCount = 0;
            int oneCount = 0;
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch == '0') {
                    zeroCount++;
                    oneCount++;
                }
                zeroArray[i] = zeroCount;
                oneArray[i] = oneCount;
                for (int x = m; x >= 0; x--) {
                    if (x - zeroCount < 0) {
                        continue;
                    }
                    for (int y = n; y >= 0; y--) {
                        if (y - oneCount < 0) {
                            continue;
                        }
                        dp[x][y] = Math.max(dp[x - zeroCount][y - oneCount] + 1, dp[x][y]);
                    }
                }

            }
        }


        return 0;
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> resultList = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        current.add(0);
        int end = graph.length - 1;
        boolean[] visited = new boolean[end];
        backtrace(resultList, current, visited, graph, end);
        return resultList;
    }

    public void backtrace(List<List<Integer>> resultList, List<Integer> current, boolean[] visited, int[][] graph, int end) {
        if (current.size() > 0 && current.get(current.size() - 1) == end) {
            resultList.add(new ArrayList<>(current));
            return;
        }
        int[] road = graph[current.get(current.size() - 1)];
        for (int i : road) {
            if (!visited[i - 1]) {
                visited[i - 1] = true;
                current.add(i);
                backtrace(resultList, current, visited, graph, end);
                visited[i - 1] = false;
                current.remove(current.size() - 1);
            }
        }
    }

    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for (char task : tasks) {
            map.merge(task, 1, Integer::sum);
        }
        List<Map.Entry<Character, Integer>> list = map.entrySet().stream().sorted((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue())).collect(Collectors.toList());
        LinkedHashMap<Character, Integer> linkedHashMap = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> characterIntegerEntry : list) {
            linkedHashMap.put(characterIntegerEntry.getKey(), characterIntegerEntry.getValue());
        }
        int current = 0;
        int total = 0;
        Queue<Character> queue = new ArrayDeque<>();
        for (int i = 0; i < tasks.length; i++) {
            Map.Entry<Character, Integer> entry = list.get(current % list.size());
            if (queue.contains(entry.getKey())) {
                queue.offer(' ');
                i--;
            } else {
                queue.offer(entry.getKey());
                int count = entry.getValue() - 1;
                if (count == 0) {
                    list.remove(entry);
                } else {
                    entry.setValue(count);
                }
                current++;
            }
            total++;
            if (queue.size() > n) {
                queue.poll();
            }
        }
        return total;


    }

    public int jump(int[] nums) {
        int current = 0;
        int dest = nums.length - 1;
        int step = 0;
        while (current < dest) {
            int choice = nums[current];
            int max = 0;
            int select = 0;
            for (int i = 1; i <= choice; i++) {
                if (current + i == nums.length - 1) {
                    return step + 1;
                }
                if (current + i < nums.length) {
                    int distance = current + i + nums[current + i];
                    if (distance > max) {
                        max = Math.max(max, distance);
                        select = current + i;
                    }
                }
            }
            current = select;
            step++;
        }
        return step;
    }

    public int longestSubstring(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.merge(ch, 1, Integer::sum);
        }
        int max = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() < k) {
                String[] array = s.split(String.valueOf(entry.getKey()));
                for (String str : array) {
                    max = Math.max(max, longestSubstring(str, k));
                }
            }
        }
        if (max > 0) {
            return max;
        }
        return s.length();
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k == 1) {
            return true;
        }
        int sum = 0;
        for (int num : nums) {
            sum = sum + num;
        }
        if (sum % k != 0) {
            return false;
        }
        int result = sum / k;
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        if (nums[nums.length - 1] > result) {
            return false;
        }
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }

        return canPartitionKSubsets(nums, 0, new int[k], result);
    }

    public boolean canPartitionKSubsets(int[] nums, int index, int[] array, int k) {
        if (index == nums.length) {
            for (int i : array) {
                if (i != k) {
                    return false;
                }
            }
            return true;
        }
        int value = nums[index];
        for (int i = 0; i < array.length; i++) {
            if (array[i] + value > k) {
                continue;
            }
            array[i] = array[i] + value;
            boolean flag = canPartitionKSubsets(nums, index + 1, array, k);
            if (flag) {
                return true;
            }
            array[i] = array[i] - value;
        }
        return false;
    }
}
