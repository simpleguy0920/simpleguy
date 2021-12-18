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
public class Solution2 {

    HashMap<Integer, List<String>> map = new HashMap<>();
    private boolean flag = false;
    private int minSize = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        char[][] array = {{'a', 'a', 'a', 'a'}, {'a', 'a', 'a', 'a'}, {'a', 'a', 'a', 'a'}};
//        System.out.println(solution.partition("a"));
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
    }

    public static int pivotIndex(int[] nums) {
        if (nums == null || nums.length < 3) {
            return -1;
        }
        int totalSum = 0;
        for (int i = 0; i < nums.length; i++) {
            totalSum = totalSum + nums[i];
        }
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) {
                left = left + nums[i - 1];
            }
            int right = totalSum - left - nums[i];
            if (left == right) {
                return i;
            }
        }
        return -1;
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }
        List<int[]> resultList = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval == null) {
                continue;
            }
            int start = interval[0];
            int end = interval[1];
            for (int j = i + 1; j < intervals.length; j++) {
                int[] interval1 = intervals[j];
                if (interval1 == null) {
                    continue;
                }
                if (interval1[1] < start || interval1[0] > end) {
                    continue;
                } else {
                    start = Math.min(start, interval1[0]);
                    end = Math.max(end, interval1[1]);
                    intervals[j] = null;
                }
            }
            boolean flag = true;
            if (resultList.size() != 0) {
                for (int j = 0; j < resultList.size(); j++) {
                    int[] ints = resultList.get(j);
                    if (ints[1] < start || ints[0] > end) {
                        flag = true;
                    } else {
                        start = Math.min(start, ints[0]);
                        end = Math.max(end, ints[1]);
                        resultList.set(j, new int[]{start, end});
                        flag = false;
                        continue;
                    }
                }
            }
            if (flag) {
                resultList.add(new int[]{start, end});
            }

        }
        return resultList.toArray(new int[0][]);


    }

    public List<String> letterCombinations(String digits) {
        List<String> resultList = new ArrayList<>();
        char[][] array = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        if (digits == null || digits.length() == 0) {
            return resultList;
        }
        List<char[]> param = new ArrayList<>();
        char[] num = digits.toCharArray();
        LinkedList<Character> result = new LinkedList<>();
        backTrace(resultList, result, array, num, 0);
        return resultList;
    }

    public void backTrace(List<String> resultList, LinkedList<Character> result, char[][] array, char[] num, int index) {
        if (result.size() == num.length) {
            StringBuilder builder = new StringBuilder();
            for (Character character : result) {
                builder.append(character);
            }
            resultList.add(builder.toString());
        } else {
            char[] param = array[Integer.parseInt(String.valueOf(num[index]))];
            for (char c : param) {
                result.add(c);
                backTrace(resultList, result, array, num, index + 1);
                result.removeLast();
            }
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> resultList = new ArrayList<>();
        if (n < 1) {
            return resultList;
        }
        LinkedList<Character> result = new LinkedList<>();
        backTraceParenthesis(resultList, result, n, n);
        return resultList;
    }

    public void backTraceParenthesis(List<String> resultList, LinkedList<Character> result, int left, int right) {
        if (left == 0 && right == 0) {
            StringBuilder builder = new StringBuilder();
            for (Character character : result) {
                builder.append(character);
            }
            resultList.add(builder.toString());
        } else {
            if (left > 0) {
                result.addLast(')');
                backTraceParenthesis(resultList, result, left - 1, right);
                result.removeLast();
            }
            if (right > left) {
                result.addLast('(');
                backTraceParenthesis(resultList, result, left, right - 1);
                result.removeLast();
            }
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> resultList = new ArrayList<>();
        LinkedList<Integer> result = new LinkedList<>();
        backTrace(resultList, result, n, k, 1);
        return resultList;
    }

    public void backTrace(List<List<Integer>> resultList, LinkedList<Integer> result, int n, int k, int start) {
        if (result.size() == k) {
            resultList.add(new ArrayList<>(result));
        } else {
            for (int i = start; i <= n; i++) {
                Integer value = i;
                result.addLast(value);
                backTrace(resultList, result, n, k, i + 1);
                result.removeLast();

            }
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        LinkedList<Integer> result = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            backTrace(resultList, result, nums, i, 0);
        }
        return resultList;
    }

    public void backTrace(List<List<Integer>> resultList, LinkedList<Integer> result, int[] nums, int size, int start) {
        if (result.size() == size) {
            resultList.add(new ArrayList<>(result));
        } else {
            for (int i = start; i < nums.length; i++) {
                result.addLast(nums[i]);
                backTrace(resultList, result, nums, size, i + 1);
                result.removeLast();
            }
        }
    }

    public boolean exist(char[][] board, String word) {
        char[] wordArray = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            char[] array = board[i];
            for (int j = 0; j < array.length; j++) {
                char ch = array[j];
                boolean flag = backStrace(board, wordArray, i, j, board.length, array.length, 0);
                if (flag) {
                    return flag;
                }
            }
        }
        return false;
    }

    public boolean backStrace(char[][] board, char[] wordArray, int x, int y, int maxWidth, int maxHeight, int index) {
        boolean flagX;
        char ch = board[x][y];
        if (ch == wordArray[index] && index == (wordArray.length - 1)) {
            return true;
        }
        if (ch == wordArray[index]) {
            board[x][y] = '.';
            if (x > 0) {
                flagX = backStrace(board, wordArray, x - 1, y, maxWidth, maxHeight, index + 1);
                if (flagX) {
                    return flagX;
                }
            }
            if (x < maxWidth - 1) {
                flagX = backStrace(board, wordArray, x + 1, y, maxWidth, maxHeight, index + 1);
                if (flagX) {
                    return flagX;
                }
            }
            if (y > 0) {
                flagX = backStrace(board, wordArray, x, y - 1, maxWidth, maxHeight, index + 1);
                if (flagX) {
                    return flagX;
                }
            }
            if (y < maxHeight - 1) {
                flagX = backStrace(board, wordArray, x, y + 1, maxWidth, maxHeight, index + 1);
                if (flagX) {
                    return flagX;
                }
            }
            board[x][y] = ch;
        }
        return false;
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> resultList = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        backTrace(resultList, s, list);
        return new ArrayList<>(resultList);
    }

    public void backTrace(List<String> resultList, String s, LinkedList<Integer> list) {
        if (list.size() == 3) {
            int thirdIndex = list.getLast();
            int secondIndex = list.get(1);
            String ip3 = s.substring(secondIndex, thirdIndex);
            String ip4 = s.substring(thirdIndex);
            if (isIP(ip3) && isIP(ip4)) {
                int firstIndex = list.getFirst();
                String ip = s.substring(0, firstIndex) + "." + s.substring(firstIndex, secondIndex) + "." + ip3 + "." + ip4;
                resultList.add(ip);
            }
        } else {
            int last = 0;
            if (list.size() > 0) {
                last = list.getLast();
            }
            for (int i = last + 1; i <= last + 3 && i < s.length(); i++) {
                String ip = s.substring(last, i);
                if (isIP(ip)) {
                    list.addLast(i);
                    backTrace(resultList, s, list);
                    list.removeLast();
                }
            }
        }
    }

    public boolean isIP(String str) {
        if (str == null || str.length() == 0 || str.length() > 3) {
            return false;
        }
        int value = Integer.parseInt(str);
        return value >= 0 && value <= 255;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> resultList = new ArrayList<>();
        Set<String> result = new LinkedHashSet<>();
        Set<String> param = new LinkedHashSet<>(wordList);
        if (!param.contains(endWord)) {
            return new ArrayList<>();
        }
        result.add(beginWord);
        backTrace(resultList, result, beginWord, endWord, param);
        if (flag) {
            resultList.removeIf(strings -> strings.size() > minSize + 1);
            return resultList;
        } else {
            return new ArrayList<>();
        }
    }

    public void backTrace(List<List<String>> resultList, Set<String> result, String start, String end, Set<String> param) {
        if (result.size() > minSize) {
            return;
        }
        if (isLike(start, end)) {
            if (result.size() <= minSize) {
                minSize = result.size();
                List<String> list = new ArrayList<>(result);
                list.add(end);
                resultList.add(list);
                flag = true;
            }
            return;
        }

        for (String s : param) {
            boolean flag1 = isLike(s, start);
            if (flag1 && !result.contains(s) && !s.equals(end)) {
                result.add(s);
                backTrace(resultList, result, s, end, param);
                result.remove(s);
            }
        }
    }

    public boolean isLike(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
            }
        }
        return diff == 1;
    }

    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        List<List<String>> resultList = new ArrayList<>();
        LinkedList<Integer> result = new LinkedList<>();
        backStrace(resultList, result, s);
        return resultList;

    }

    public void backStrace(List<List<String>> resultList, LinkedList<Integer> result, String s) {
        if (resultList.size() == 0) {
            if (isReverse(s)) {
                List<String> list = new ArrayList<>();
                list.add(s);
                resultList.add(list);
            }
        }
        if (result.size() > s.length() - 1) {
            return;
        }
        int last = 0;
        if (result.size() > 0) {
            last = result.getLast();
            String lastString = s.substring(last);
            int beforeLast = 0;
            if (result.size() > 1) {
                beforeLast = result.get(result.size() - 1);
            }
            String bebeforeLastString = s.substring(beforeLast, last);
            if (isReverse(lastString) && isReverse(bebeforeLastString)) {
                List<String> list = new ArrayList<>();
                if (result.size() > 0) {
                    int start = 0;
                    int end = 0;
                    for (Integer integer : result) {
                        start = end;
                        end = integer;
                        String string = s.substring(start, end);
                        list.add(string);
                    }
                }
                list.add(lastString);
                resultList.add(list);
            }
        }
        for (int i = last + 1; i < s.length(); i++) {
            int start = 0;
            if (result.size() > 0) {
                start = result.getLast();
            }
            if (isReverse(s.substring(start, i))) {
                result.addLast(i);
                backStrace(resultList, result, s);
                result.removeLast();
            }
        }

    }

    public boolean isReverse(String s) {
        if (s.length() == 1) {
            return true;
        }
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> resultList = new ArrayList<>();
        LinkedList<String> result = new LinkedList<>();
        Set<String> set = new HashSet<>(wordDict);
        Map<String, LinkedList<String>> map = new HashMap<>();
        backTrace(resultList, result, set, s, 0, map);
        return resultList;
    }

    public void backTrace(List<String> resultList, LinkedList<String> resullt, Set<String> set, String s, int last, Map<String, LinkedList<String>> map) {
        String lastString = s.substring(last);
        if (map.containsKey(lastString)) {
            List<String> list = new ArrayList<>();
            list.addAll(resullt);
            list.addAll(map.get(lastString));
            resultList.add(String.join(" ", list));
        } else if (set.contains(lastString)) {
            if (resullt.isEmpty()) {
                resultList.add(lastString);
            } else {
                resultList.add(String.join(" ", resullt) + " " + lastString);
            }
        }
        for (int i = last + 1; i < s.length(); i++) {
            String str = s.substring(last, i);
            if (set.contains(str)) {
                resullt.addLast(str);
                map.put(s.substring(0, i), new LinkedList<>(resullt));
                backTrace(resultList, resullt, set, s, i, map);
                resullt.removeLast();
            }
        }
    }

    public List<String> wordBreak(String s, Set<String> wordDict) {
        return word_Break(s, wordDict, 0);
    }

    public List<String> word_Break(String s, Set<String> wordDict, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
            res.add("");
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end))) {
                List<String> list = word_Break(s, wordDict, end);
                for (String l : list) {
                    res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
                }
            }
        }
        map.put(start, res);
        return res;
    }

    public void sortColors(int[] nums) {
        int[] count = new int[3];
        for (int num : nums) {
            count[num]++;
        }
        for (int i = 0; i < count[0]; i++) {
            nums[i] = 0;
        }
        for (int i = count[0]; i < count[0] + count[1]; i++) {
            nums[i] = 1;
        }
        for (int i = count[0] + count[1]; i < count[0] + count[1] + count[2]; i++) {
            nums[i] = 2;
        }
    }

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] array = s.toCharArray();
        int maxSize = 0;
        for (int i = 0; i < array.length; i++) {
            map.clear();
            for (int j = i; j < array.length; j++) {
                Character character = array[j];
                if (!map.containsKey(character)) {
                    map.put(character, j);
                    if (map.size() > maxSize) {
                        maxSize = map.size();
                    }
                } else {
                    i = Math.max(map.get(character) - 1, i);
                    break;
                }
            }
        }
        return maxSize;

    }

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer count = map.get(num);
            if (count == null) {
                map.put(num, 1);
            } else {
                count++;
                map.put(num, count);
            }
        }
        int size = nums.length / 2;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > size) {
                return entry.getKey();
            }
        }
        return 0;
    }

    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int i = 0;
        int j = nums.length;
        while (i <= i) {
            if (nums[i] <= nums[i + 1]) {
                i++;
            }

            if (nums[j] >= nums[j - 1] && i < j) {
                i++;
            }
        }
        return j - i + 1;

    }
}