package com.test.java.solution;

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
public class Solution4 {
    static int result = 0;
    static String value = null;

    public static void main(String[] args) {
        Solution4 solution4 = new Solution4();
        System.out.println(solution4.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
    }

    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        } else if (shorter == longer) {
            return new int[]{shorter * k};
        }
        int[] result = new int[k + 1];
        for (int i = k; i >= 0; i--) {
            result[k - i] = shorter * i + (k - i) * longer;
        }
        return result;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        if (nums.length < k) {
            return false;
        }
        Arrays.sort(nums);
        int sum = 0;
        for (int num : nums) {
            sum = sum + num;
            Integer count = map.get(num);
            if (count == null) {
                count = 0;
            }
            count++;
            map.put(num, count);
        }
        if (sum % k != 0) {
            return false;
        }
        sum = sum / k;
        if (nums[nums.length - 1] > sum) {
            return false;
        }
        return false;
    }

    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        } else if (nums.length == 2) {
            return nums[0] > nums[1] ? 0 : 1;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] > nums[mid + 1])
                start = mid;
            else
                end = mid + 1;
        }
        return start;
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> resultList = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            Integer count = map.get(word);
            if (count == null) {
                count = 0;
            }
            count++;
            map.put(word, count);
        }
        int size = words.length;
        int length = words[0].length();
        int total = size * length;
        if (s.length() < total) {
            return resultList;
        }
        for (int i = 0; i < s.length() - total + 1; i++) {
            boolean flag = true;
            HashMap<String, Integer> newMap = (HashMap<String, Integer>) map.clone();
            for (int j = 0; j < size; j++) {
                String str = s.substring(i + j * length, i + (j + 1) * length);
                Integer count = newMap.get(str);
                if (count == null || count == 0) {
                    flag = false;
                    break;
                } else {
                    count--;
                    newMap.put(str, count);
                }
            }
            if (flag) {
                resultList.add(i);
            }
        }
        return resultList;

    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> param = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            Integer count = param.get(ch);
            if (count == null) {
                count = 0;
            }
            count++;
            param.put(ch, count);
        }
        String result = null;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = start;
        while (end < s.length()) {
            char ch = s.charAt(end);
            if (param.containsKey(ch)) {
                Integer count = map.get(ch);
                if (count == null) {
                    count = 0;
                }
                count++;
                map.put(ch, count);
                //是子串
                if (checkMap(param, map)) {
                    String str = s.substring(start, end + 1);
                    if (result == null) {
                        result = str;
                    } else if (str.length() < result.length()) {
                        result = str;
                    }
                    while (checkMap(param, map)) {
                        String newStr = s.substring(start, end + 1);
                        if (newStr.length() < result.length()) {
                            result = newStr;
                        }
                        char startCh = s.charAt(start);
                        Integer count1 = map.get(startCh);
                        if (count1 != null) {
                            if (count1 == 1) {
                                map.remove(startCh);
                            } else {
                                count1--;
                                map.put(startCh, count1);
                            }
                        }
                        start++;
                    }
                    end++;
                } else {
                    end++;
                }
                //不是子串，start++
            } else {
                end++;

            }
        }
        if (result == null) {
            return "";
        } else {
            return result;
        }
    }

    public boolean checkMap(Map<Character, Integer> param, Map<Character, Integer> map) {
        if (param.size() != map.size()) {
            return false;
        }
        for (Map.Entry<Character, Integer> entry : param.entrySet()) {
            Integer count = map.get(entry.getKey());
            if (count == null) {
                return false;
            } else if (count < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    //    public int[] maxSlidingWindow(int[] nums, int k) {
//        int[] result = new int[nums.length - k + 1];
//
//        int max = nums[0];
//        int maxIndex = 0;
//        for (int i = 0; i < nums.length - k; i++) {
//            if (i == 0) {
//                for (int j = i; j < i + k; j++) {
//                    if (nums[j] > max) {
//                        max = nums[i];
//                        maxIndex = j;
//                    }
//                }
//                result[i] = max;
//            } else {
//                int value = nums[i + k];
//                if (value >= max) {
//                    result[i] = value;
//                } else {
//
//                }
//            }
//        }
//        return result;
//    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }
        List<Integer> result = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < nums.length - k; i++) {
            int value = nums[i];
            while (!list.isEmpty() && nums[list.getLast()] <= value) {
                list.removeLast();
            }
            list.addFirst(i);
            if (list.getLast() <= i - k) {
                list.removeLast();
            }
            if (i >= k) {
                result.add(nums[list.getFirst()]);
            }

        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }


    public String convertToTitle(int n) {
        int value = n / 26;
        if (value == 0) {
            return String.valueOf((char) ('A' + n));
        } else {
            int value2 = n % 26;
            return String.valueOf(new char[]{(char) ('A' + value), (char) ('A' + value2)});
        }
    }

    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        Map<String, Integer> resultMap = new HashMap<>();
        Set<Integer> friendSet = new HashSet<>();
        int[] friendArray = friends[id];
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int friend : friendArray) {
            if (id != friend && !friendSet.contains(friend)) {
                linkedList.add(friend);
                friendSet.add(friend);
            }
        }
        int num = 0;
        while (!linkedList.isEmpty()) {
            if (num == level) {
                break;
            }
            int size = linkedList.size();
            for (int i = 0; i < size; i++) {
                int friend = linkedList.pop();
                if (num == level - 1) {
                    List<String> watchedVideoArray = watchedVideos.get(friend);
                    for (String watchedVideo : watchedVideoArray) {
                        Integer count = resultMap.get(watchedVideo);
                        if (count == null) {
                            count = 0;
                        }
                        count++;
                        resultMap.put(watchedVideo, count);
                    }
                }
                int[] childFriends = friends[friend];
                for (int childFriend : childFriends) {
                    if (id != childFriend && !friendSet.contains(childFriend)) {
                        friendSet.add(childFriend);
                        linkedList.add(childFriend);
                    }
                }
            }
            num++;
        }
        return resultMap.entrySet().stream().sorted((o1, o2) -> {
            if (!o1.getValue().equals(o2.getValue())) {
                return o2.getValue().compareTo(o1.getValue());
            } else {
                return o1.getKey().compareTo(o2.getKey());
            }
        }).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int sum = 0;
        Set<Integer> keySet = new HashSet<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int initialBox : initialBoxes) {
            linkedList.offer(initialBox);
        }
        while (!linkedList.isEmpty()) {
            int size = linkedList.size();
            boolean hasFlag = false;
            for (int i = 0; i < size; i++) {
                int box = linkedList.pop();
                if (status[box] == 1 || keySet.contains(box)) {
                    hasFlag = true;
                    //糖果数量累加
                    sum = sum + candies[box];
                    //钥匙放入钥匙set
                    int[] keyArray = keys[box];
                    for (int key : keyArray) {
                        keySet.add(key);
                    }
                    int[] containedBoxeArray = containedBoxes[box];
                    for (int containedBox : containedBoxeArray) {
                        linkedList.offer(containedBox);
                    }
                } else {
                    linkedList.offer(box);
                }
            }
            if (!hasFlag) {
                break;
            }
        }
        return sum;
    }

    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) {
            return 0;
        }
        List<Set<Integer>> routeList = new ArrayList<>();
        for (int[] route : routes) {
            Set<Integer> routeSet = new HashSet<>();
            for (int i : route) {
                routeSet.add(i);
            }
            routeList.add(routeSet);
        }
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.offer(S);
        Set<Integer> set = new HashSet<>();
        set.add(S);
        int time = 1;
        boolean find = false;
        l1:
        while (!linkedList.isEmpty()) {
            int size = linkedList.size();
            for (int i = 0; i < size; i++) {
                int current = linkedList.pop();
                for (Set<Integer> routeSet : routeList) {
                    if (!routeSet.contains(current)) {
                        continue;
                    }
                    boolean hasDest = routeSet.contains(T);
                    if (hasDest) {
                        find = true;
                        break l1;
                    } else {
                        for (Integer station : routeSet) {
                            if (!set.contains(station)) {
                                set.add(station);
                                linkedList.offer(station);
                            }
                        }
                    }
                }
            }
            time++;
        }
        return find ? time : -1;

    }

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            boolean[] visited = new boolean[N];
            visited[i] = true;
            int sum = sumOfDistancesInTree(map, 0, i, visited);
            result[i] = sum;
        }
        return result;
    }

    public int sumOfDistancesInTree(Map<Integer, List<Integer>> map, int sum, int current, boolean[] visited) {

        int value = 0;
        List<Integer> list = map.get(current);
        for (Integer dest : list) {
            if (!visited[dest]) {
                visited[dest] = true;
                value = value + sumOfDistancesInTree(map, sum + 1, dest, visited);
            }
        }
        return value + sum;
    }

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int max = 0;
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int current = longestIncreasingPath(matrix, i, j, 1, visited, result);
                System.out.println("i=" + i + " j=" + j + " current=" + current);
                max = Math.max(max, current);
            }
        }

        return max;
    }

    public int longestIncreasingPath(int[][] matrix, int x, int y, int distance, boolean[][] visited, int[][] result) {
        if (result[x][y] > 0) {
            return distance + result[x][y];
        }
        visited[x][y] = true;
        int value = 0;
        if (x - 1 >= 0 && !visited[x - 1][y] && matrix[x - 1][y] > matrix[x][y]) {
            int value1 = longestIncreasingPath(matrix, x - 1, y, 1, visited, result);
            value = Math.max(value, value1);
        }
        if (x + 1 < matrix.length && !visited[x + 1][y] && matrix[x + 1][y] > matrix[x][y]) {
            int value2 = longestIncreasingPath(matrix, x + 1, y, 1, visited, result);
            value = Math.max(value, value2);

        }
        if (y - 1 >= 0 && !visited[x][y - 1] && matrix[x][y - 1] > matrix[x][y]) {
            int value3 = longestIncreasingPath(matrix, x, y - 1, 1, visited, result);
            value = Math.max(value, value3);

        }
        if (y + 1 < matrix[0].length && !visited[x][y + 1] && matrix[x][y + 1] > matrix[x][y]) {
            int value4 = longestIncreasingPath(matrix, x, y + 1, 1, visited, result);
            value = Math.max(value, value4);

        }
        visited[x][y] = false;
        result[x][y] = value;
        return value + distance;
    }

    public int numTilePossibilities(String tiles) {
        Set<String> set = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();
        char[] array = tiles.toCharArray();
        for (char ch : array) {
            map.merge(ch, 1, Integer::sum);
        }
        numTilePossibilities(map, set, new StringBuilder());
        return set.size();
    }

    private void numTilePossibilities(Map<Character, Integer> map, Set<String> set, StringBuilder builder) {
        if (map.isEmpty()) {
            return;
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 0) {
                continue;
            }
            map.put(entry.getKey(), entry.getValue() - 1);
            builder.append(entry.getKey());
            set.add(builder.toString());
            numTilePossibilities(map, set, builder);
            builder.deleteCharAt(builder.length() - 1);
            map.put(entry.getKey(), entry.getValue() + 1);
        }
    }

    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> list = new ArrayList<>();
        int minSize = String.valueOf(low).length();
        int maxSize = String.valueOf(high).length();
        for (int j = minSize; j <= maxSize; j++) {
            loop1:
            for (int i = 1; i <= 9; i++) {
                int sum = 0;
                int value = i;
                for (int k = 0; k < j; k++) {
                    if (value >= 10) {
                        break loop1;
                    }
                    sum = sum * 10 + value;
                    value++;
                }
                if (sum > low && sum < high) {
                    list.add(sum);
                }
            }
        }
        return list;
    }

    public void sequentialDigits(List<Integer> list, int low, int high, int minSize, int maxSize) {
        for (int j = minSize; j <= maxSize; j++) {
            int value = 0;
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                for (int k = 0; k <= j; k++) {
                    if (value + 1 >= 10) {
                        break;
                    }
                    sum = sum * 10 + (value + 1);
                }
            }
            if (sum > low && sum < high) {
                list.add(sum);
            }
        }
    }

    public int getMaximumGold(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0) {
                    visited[i][j] = true;
                    getMaximumGold(grid, i, j, visited, grid[i][j]);
                    visited[i][j] = false;
                }
            }
        }
        return result;
    }

    public void getMaximumGold(int[][] grid, int x, int y, boolean[][] visited, int current) {
        if (current > result) {
            result = current;
        }
        visited[x][y] = true;
        if (x - 1 >= 0 && !visited[x - 1][y] && grid[x - 1][y] > 0) {
            getMaximumGold(grid, x - 1, y, visited, current + grid[x - 1][y]);
        }

        if (x + 1 < grid.length && !visited[x + 1][y] && grid[x + 1][y] > 0) {
            getMaximumGold(grid, x + 1, y, visited, current + grid[x + 1][y]);
        }
        if (y - 1 >= 0 && !visited[x][y - 1] && grid[x][y - 1] > 0) {
            getMaximumGold(grid, x, y - 1, visited, current + grid[x][y - 1]);
        }
        if (y + 1 < grid[0].length && !visited[x][y + 1] && grid[x][y + 1] > 0) {
            getMaximumGold(grid, x, y + 1, visited, current + grid[x][y + 1]);
        }
        visited[x][y] = false;
    }

    public int tilingRectangle(int n, int m, int[][] dp) {
        int max = Math.max(m, n);
        int min = Math.min(m, n);
        if (dp[max][min] != 0) {
            return dp[max][min];
        }
        int result = 0;
        if (max == min) {
            result = 1;
        } else if (min == 1) {
            result = max;
        } else if (max % min == 0) {
            result = max / min;
        }
        dp[n][m] = result;
        return result;

    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        subsets(nums, result, list);
        return result;
    }

    public void subsets(int[] nums, List<List<Integer>> result, List<Integer> list) {
        result.add(list);
        for (int num : nums) {
            if (!list.isEmpty()) {
                int last = list.get(list.size() - 1);
                if (num <= last) {
                    continue;
                }
            }
            list.add(num);
            subsets(nums, result, list);
            list.remove(list.size() - 1);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        generateParenthesis(n, result, builder, 0, 0);
        return result;
    }

    public void generateParenthesis(int n, List<String> result, StringBuilder builder, int left, int right) {
        if (left == n && right == n) {
            result.add(builder.toString());
            return;
        }
        if (left == right) {
            builder.append("(");
            generateParenthesis(n, result, builder, left + 1, right);
            builder.deleteCharAt(builder.length() - 1);
        } else if (left > right) {
            if (left == n) {
                builder.append(")");
                generateParenthesis(n, result, builder, left, right + 1);
                builder.deleteCharAt(builder.length() - 1);
            } else {
                builder.append("(");
                generateParenthesis(n, result, builder, left + 1, right);
                builder.deleteCharAt(builder.length() - 1);
                builder.append(")");
                generateParenthesis(n, result, builder, left, right + 1);
                builder.deleteCharAt(builder.length() - 1);
            }
        }
    }

    public int pileBox(int[][] box) {
        Arrays.sort(box, Comparator.comparingInt(o -> o[0]));
        int[] dp = new int[box.length];
        int result = 0;
        dp[0] = box[0][2];
        for (int i = 1; i < box.length; i++) {
            int sum = 0;
            for (int j = 0; j < i; j++) {
                if (box[j][0] < box[i][0] && box[j][1] < box[i][1] && box[j][2] < box[i][2]) {
                    sum = Math.max(sum, dp[j]);
                }
                dp[i] = sum + box[i][2];
                result = Math.max(result, dp[i]);
            }
        }
        return result;
    }

    public void pileBox(int[][] box, int x, int y, int z, int sum) {
        if (sum > result) {
            result = sum;
        }
        if (x == 1 || y == 1 || z == 1) {
            return;
        }

        for (int i = 0; i < box.length; i++) {
            int[] value = box[i];
            if (value[0] < x && value[1] < y && value[2] < z) {
                pileBox(box, value[0], value[1], value[2], sum + value[2]);
            }
        }
    }

    public String[] permutation(String S) {
        Set<String> result = new HashSet<>();
        StringBuilder builder = new StringBuilder();
        permutation(S, new StringBuilder(), result, new boolean[S.length()]);
        return result.toArray(new String[0]);
    }

    public void permutation(String s, StringBuilder builder, Set<String> result, boolean[] visited) {
        if (builder.length() == s.length()) {
            System.out.println(builder);
            result.add(builder.toString());
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                builder.append(s.charAt(i));
                permutation(s, builder, result, visited);
                visited[i] = false;
            }
        }
    }

    public String getPermutation(int n, int k) {
        result = k;
        getPermutation(new StringBuilder(), n, new boolean[n]);
        return value;
    }

    public void getPermutation(StringBuilder builder, int n, boolean[] visited) {
        if (result == 0) {
            return;
        }
        if (builder.length() == n) {
            result--;
            System.out.println(builder);
            if (result == 0) {
                value = builder.toString();
            }
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                builder.append(i + 1);
                getPermutation(builder, n, visited);
                builder.deleteCharAt(builder.length() - 1);
                visited[i] = false;
            }
        }
    }

    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map1 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map1.merge(ch, 1, Integer::sum);
        }
        Map<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            map2.merge(ch, 1, Integer::sum);
        }
        return map1.equals(map2);

    }

    public int firstMissingPositive(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = -1;
        for (int num : nums) {
            if (num > 0) {
                min = Math.min(min, num);
                max = Math.max(max, num);
            }
        }
        if (min > 1) {
            return min;
        } else {

        }

        return 0;
    }

    public int[] fraction(int[] cont) {
        return fraction(cont, 0, cont.length - 1);
    }

    public int[] fraction(int[] cont, int start, int end) {
        if (start == end) {
            return new int[]{1, cont[start]};
        }
        int a = cont[start];
        int[] b = fraction(cont, start + 1, end);
        int up = b[1];
        int down = a * b[1] + b[0];
        int[] result = fraction(up, down);
        if (start == 0) {
            return fraction(down, up);
        } else {
            return fraction(up, down);
        }
    }

    public int[] fraction(int up, int down) {
        int max = 1;
        for (int i = 2; i <= Math.min(up, down); i++) {
            if (up % i == 0 && down % i == 0) {
                max = i;
            }
        }
        return new int[]{up / max, down / max};
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return result;
        }
        LinkedList<List<String>> list = new LinkedList<>();
        List<String> routeList = new ArrayList<>();
        routeList.add(beginWord);
        list.offer(routeList);
        wordSet.remove(beginWord);
        boolean find = false;
        while (!list.isEmpty() && !find) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                List<String> route = list.pop();
                String string = route.get(route.size() - 1);
                if (isLike(string, endWord)) {
                    List<String> newList = new ArrayList<>();
                    newList.add(endWord);
                    result.add(newList);
                    find = true;
                } else {
                    Iterator<String> iterator = wordSet.iterator();
                    while (iterator.hasNext()) {
                        String word = iterator.next();
                        if (isLike(string, word)) {
                            List<String> newList = new ArrayList<>();
                            newList.add(word);
                            list.offer(newList);
                            iterator.remove();
                        }
                    }
                }
            }
        }
        return result;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        LinkedList<String> list = new LinkedList<>();
        list.offer(beginWord);
        wordSet.remove(beginWord);
        int length = 1;
        while (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                String string = list.pop();
                if (isLike(string, endWord)) {
                    return length + 1;
                } else {
                    Iterator<String> iterator = wordSet.iterator();
                    while (iterator.hasNext()) {
                        String word = iterator.next();
                        if (isLike(string, word)) {
                            list.offer(word);
                            iterator.remove();
                        }
                    }
                }
            }
            length++;
        }
        return 0;
    }

    public boolean isLike(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
                if (diff > 1) {
                    return false;
                }
            }
        }
        return diff == 1;
    }

    public List<String> removeInvalidParentheses(String s) {
        List<String> resultList = new ArrayList<>();

        StringBuilder builder = new StringBuilder(s);
        LinkedList<StringBuilder> linkedList = new LinkedList<>();
        linkedList.offer(builder);
        Set<String> set = new HashSet<>();
        int left, right;
        while (!linkedList.isEmpty()) {
            int size = linkedList.size();
            for (int i = 0; i < size; i++) {
                StringBuilder stringBuilder = linkedList.pop();
                left = 0;
                right = 0;
                for (int j = 0; j < stringBuilder.length(); j++) {
                    char ch = stringBuilder.charAt(j);
                    if (ch == '(') {
                        left++;
                    } else if (ch == ')') {
                        right++;
                    }
                    if (right > left) {
                        for (int k = 0; k < right; k++) {
                            StringBuilder clone = new StringBuilder(stringBuilder);

                        }


                    }
                }
            }
        }
        resultList.addAll(set);
        return resultList;
    }

    public boolean carPooling(int[][] trips, int capacity) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] trip : trips) {
            if (trip[0] > capacity) {
                return false;
            }
            Integer current = map.get(trip[1]);
            if (current == null) {
                current = 0;
            }
            current = current + trip[0];
            map.put(trip[1], current);

            Integer current2 = map.get(trip[2]);
            if (current2 == null) {
                current2 = 0;
            }
            current2 = current2 - trip[0];
            map.put(trip[2], current2);
        }
        System.out.println(map);
        int current = 0;
        for (Integer change : map.values()) {
            current = current + change;
            if (current > capacity) {
                return false;
            }
        }
        return true;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean[] courese = new boolean[numCourses];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            List<Integer> list = map.get(prerequisite[1]);
            if (list == null) {
                list = new ArrayList<>();
                list.add(prerequisite[0]);
                map.put(prerequisite[1], list);
            } else {
                list.add(prerequisite[0]);
            }
        }
        LinkedList<Integer> list = new LinkedList<>();
        for (Integer start : map.keySet()) {
            list.offer(start);
            courese[start] = true;
        }
        while (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Integer value = list.pop();
                List<Integer> list1 = map.get(value);
                if (list1 != null) {
                    for (Integer integer : list1) {
                        if (courese[integer]) {
                            return false;
                        }
                        list.offer(integer);
                        courese[integer] = true;
                    }
                }

            }
        }
        for (boolean c : courese) {
            if (!c) {
                return false;
            }
        }
        return true;
    }


    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        char[] array = word.toCharArray();
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (exist(array, 0, board, i, j, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean exist(char[] array, int start, char[][] board, int i, int j, boolean[][] visited) {
        if (array[start] != board[i][j]) {
            return false;
        }
        if (start + 1 == array.length) {
            return true;
        }
        visited[i][j] = true;
        if (i - 1 >= 0 && !visited[i - 1][j]) {
            boolean flag = exist(array, start + 1, board, i - 1, j, visited);
            if (flag) {
                return flag;
            }
        }
        if (i + 1 < board.length && !visited[i + 1][j]) {
            boolean flag = exist(array, start + 1, board, i + 1, j, visited);
            if (flag) {
                return flag;
            }
        }
        if (j - 1 >= 0 && !visited[i][j - 1]) {
            boolean flag = exist(array, start + 1, board, i, j - 1, visited);
            if (flag) {
                return flag;
            }
        }
        if (j + 1 < board[0].length && !visited[i][j + 1]) {
            boolean flag = exist(array, start + 1, board, i, j + 1, visited);
            if (flag) {
                return flag;
            }
        }
        visited[i][j] = false;
        return false;
    }

    public boolean makesquare(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum = sum + num;
        }
        if (sum % 4 != 0) {
            return false;
        }
        sum = sum / 4;
        Arrays.sort(nums);
        return dfs(0, sum, 0, 0, nums, new boolean[nums.length]);
    }

    boolean dfs(int edge, int sum, int result, int start, int[] nums, boolean[] visited) {
        if (edge == 4) {
            return true;
        }
        if (result == sum) {
            return dfs(edge + 1, sum, 0, 0, nums, visited);
        }
        for (int i = start; i < nums.length; i++) {
            if (!visited[i] && nums[i] + result <= sum) {
                visited[i] = true;
                if (dfs(edge, sum, nums[i] + result, i + 1, nums, visited)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }


    public int findTargetSumWays(int[] nums, int S) {
        result = 0;
        if (nums == null || nums.length == 0) {
            return result;
        }
        findTargetSumWays(nums, S, nums[0], 0);
        return result;
    }

    public void findTargetSumWays(int[] nums, int s, int current, int index) {
        if (index == nums.length) {
            if (current == s) {
                result++;
            }
            return;
        }
        findTargetSumWays(nums, s, current + nums[index], index + 1);
        findTargetSumWays(nums, s, current - nums[index], index + 1);
    }

    public int firstUniqChar(String s) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                map.put(ch, -1);
            } else {
                map.put(ch, i);
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= 0) {
                return entry.getKey();
            }
        }
        return 0;
    }

    public char findTheDifference(String s, String t) {
        int ch = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            ch = ch ^ s.charAt(i);
        }
        for (int i = 0; i < t.length(); i++) {
            ch = ch ^ t.charAt(i);
        }
        return (char) ch;
    }

    public int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.merge(ch, 1, Integer::sum);
        }
        int sum = 0;
        boolean hasSingle = false;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 == 0) {
                sum = sum + entry.getValue();
            } else {
                hasSingle = true;
                sum = sum + entry.getValue() - 1;
            }
        }
        if (hasSingle) {
            sum = sum + 1;
        }
        return sum;
    }

    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            char ch1 = s.charAt(start);
            if (!Character.isLetterOrDigit(ch1)) {
                start++;
                continue;
            }
            char ch2 = s.charAt(end);
            if (!Character.isLetterOrDigit(ch1)) {
                end--;
                continue;
            }
            if (Character.toLowerCase(ch1) != Character.toLowerCase(ch2)) {
                return false;
            }
        }
        return true;

    }

    public void moveZeroes(int[] nums) {
        int start = 0;
        int end = 1;
        while (end < nums.length) {
            if (nums[start] == 0) {
                if (nums[end] != 0) {
                    nums[start] = nums[end];
                    nums[end] = 0;
                    start++;
                    end++;
                } else if (nums[end] == 0) {
                    end++;
                }
            } else {
                start++;
                end++;
            }
        }

    }

    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0) {
            return s;
        }
        if (numRows <= 1) {
            return s;
        }
        List<List<Character>> resultList = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            resultList.add(new ArrayList<>());
        }

        int startRow = 0;
        boolean asc = true;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            resultList.get(startRow).add(ch);
            if (startRow == 0) {
                startRow++;
                asc = true;
            } else if (startRow == numRows - 1) {
                startRow--;
                asc = false;
            } else {
                if (asc) {
                    startRow++;
                } else {
                    startRow--;
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        for (List<Character> characters : resultList) {
            for (Character character : characters) {
                builder.append(character);
            }
        }
        return builder.toString();
    }

    public String reverseVowels(String s) {
        char[] array = s.toCharArray();
        int start = 0;
        int end = array.length - 1;
        while (start < end) {
            if (!isVowels(array[start])) {
                start++;
            } else if (!isVowels(array[end])) {
                end--;
            } else {
                char temp = array[start];
                array[start] = array[end];
                array[end] = temp;
                start++;
                end--;
            }
        }
        return new String(array);
    }

    public boolean isVowels(char ch) {
        return ch == 'a' || ch == 'o' || ch == 'e' || ch == 'i' || ch == 'u';
    }

    public String reverseStr(String s, int k) {
        char[] array = s.toCharArray();
        for (int i = 0; i < s.length() / (2 * k); i++) {
            int start = i * 2 * k;
            int end = Math.min(array.length - 1, (i + 1) * 2 * k);
            reverse(array, start, end);
        }
        return new String(array);

    }

    public void reverse(char[] array, int start, int end) {
        while (start < end) {
            char temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }
}

