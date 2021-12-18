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
public class Solution6 {
    public static void main(String[] args) {
        System.out.println(new Date(1000l * 1605172195));
        Solution6 solution6 = new Solution6();
        System.out.println(solution6.minWindow("ADOBECODEBANC", "ABC"));
    }

    public List<List<Integer>> pairSums(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> list = new ArrayList<>();
        for (int num : nums) {
            int result = target - num;
            Integer size = map.get(result);
            if (size == null || size == 0) {
                map.put(num, 1);
            } else {
                size = size - 1;
                map.put(result, size);
                List<Integer> list1 = new ArrayList<>();
                list1.add(num);
                list1.add(result);
                list.add(list1);
            }
        }
        return list;

    }

    public int findRepeatNumber(int[] nums) {
        boolean[] array = new boolean[nums.length];
        for (int num : nums) {
            if (array[num]) {
                return num;
            } else {
                array[num] = true;
            }
        }
        return -1;
    }

    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.merge(ch, 1, Integer::sum);
        }
        int count = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public int findKthPositive(int[] arr, int k) {
        boolean[] array = new boolean[arr.length + k];
        for (int i : arr) {
            if (i < array.length) {
                array[i - 1] = true;
            }
        }
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (!array[i]) {
                count++;
                if (count == k) {
                    return i + 1;
                }
            }
        }
        return 0;
    }

    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        int sum = 0;
        for (Integer value : map.values()) {
            sum = sum + value * (value - 1) / 2;
        }
        return sum;

    }

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] result = new int[nums.length];
        int[] clone = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (i == 0) {
                map.put(num, 0);
            } else {
                if (num > nums[i - 1]) {
                    map.put(num, i + 1);
                }
            }
        }
        for (int j = 0; j < clone.length; j++) {
            int i = clone[j];
            result[j] = map.get(i);
        }
        return result;


    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Set<String> set = new HashSet<>();
        findSubsequences(nums, result, set, 0);
        List<List<Integer>> result2 = new ArrayList<>();
        for (List<Integer> list : result) {
            if (list.size() > 1) {
                result2.add(list);
            }
        }
        return result2;

    }

    public void findSubsequences(int[] nums, List<List<Integer>> result, Set<String> set, int start) {
        if (start > nums.length - 1) {
            return;
        }
        List<List<Integer>> addList = new ArrayList<>();
        for (List<Integer> list : result) {
            if (nums[start] >= list.get(list.size() - 1)) {
                List<Integer> list1 = new ArrayList<>();
                list1.addAll(list);
                list1.add(nums[start]);
                addList.add(list1);
            }
        }
        List<Integer> list1 = new ArrayList<>();
        list1.add(nums[start]);
        addList.add(list1);
        for (List<Integer> list : addList) {
            StringBuilder builder = new StringBuilder();
            for (Integer integer : list) {
                builder.append(integer);
            }
            String string = builder.toString();
            if (!set.contains(string)) {
                result.add(list);
                set.add(string);
            }

        }
        findSubsequences(nums, result, set, start + 1);
    }

    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            map.merge(ch, 1, Integer::sum);
        }
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) {
            return result;
        }
        Map<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            char ch = s.charAt(i);
            map2.merge(ch, 1, Integer::sum);
        }
        if (map.equals(map2)) {
            result.add(0);
        }
        for (int i = 0; i < s.length() - p.length(); i++) {
            char add = s.charAt(i + p.length());
            map2.merge(add, 1, Integer::sum);
            char remove = s.charAt(i);
            int count = map2.get(remove);
            if (count == 1) {
                map2.remove(remove);
            } else {
                map2.put(remove, count - 1);
            }
            if (map.equals(map2)) {
                result.add(i);
            }
        }
        return result;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        int[] array = new int[26];
        for (int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            array[ch - 'a']++;
        }
        List<Integer> list = new ArrayList<>();
        if (s.length() < p.length()) {
            return list;
        }
        int start = 0;
        int end = 0;
        int[] temp = new int[26];
        while (end < s.length()) {
            char ch = s.charAt(end);
            temp[ch - 'a']++;
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] < array[i]) {
                    end++;
                    break;
                }
            }
            if (end - start == p.length()) {
                list.add(start);
            }


        }

        return list;
    }

    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum = sum + nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        result[T.length - 1] = 0;
        for (int i = T.length - 1; i >= 0; i--) {
            if (T[i] < T[i + 1]) {
                result[i] = 1;
            } else {
                int index = i + 1;
                while (T[i] >= T[index]) {
                    if (result[index] == 0) {
                        index = i;
                        break;
                    } else {
                        index = index + result[index];
                    }
                }
                result[i] = index - i;
            }
        }
        return result;
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int start = 0;
        int end = -1;
        int result = 0;
        Set<Character> set = new HashSet<>();
        while (start < s.length()) {
            if (end + 1 < s.length() && !set.contains(s.charAt(end + 1))) {
                end++;
                set.add(s.charAt(end));
            } else {
                set.remove(s.charAt(start));
                start++;
            }
            result = Math.max(result, end - start);
        }
        return result;
    }

    public String minWindow(String s, String t) {
        if (s == null || s.length() < t.length()) {
            return "";
        }
        boolean find = false;
        int[] need = new int[256];
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            need[ch - 'A']++;
        }
        int chCount = 0;
        for (int i : need) {
            if (i > 0) {
                chCount++;
            }
        }
        int start = 0;
        int end = 0;
        //满足条件的char个数
        int validCount = 0;
        int size = Integer.MAX_VALUE;
        int startIndex = 0;
        int[] have = new int[256];
        while (end < s.length()) {
            char ch = s.charAt(end);
            end++;
            int indexEnd = ch - 'A';
            if (need[indexEnd] > 0) {
                int count = have[indexEnd];
                count++;
                have[indexEnd] = count;
                if (need[indexEnd] == count) {
                    validCount++;
                }
            }
            while (start < end && validCount == chCount) {
                find = true;
                if (end - start < size) {
                    startIndex = start;
                    size = end - start;
                }
                char startCh = s.charAt(start);
                start++;
                int indexStart = startCh - 'A';
                if (need[indexStart] > 0) {
                    int startCount = have[indexStart];

                    if (need[indexStart] == startCount) {
                        validCount--;
                    }
                    startCount--;
                    have[indexStart] = startCount;
                }
            }
        }
        return find ? s.substring(startIndex, startIndex + size) : "";
    }

    public int characterReplacement(String s, int k) {
        int result = 0;
        int start = 0;
        int end = 0;
        int[] array = new int[26];
        int lastMax = 0;
        while (end < s.length()) {
            char ch = s.charAt(end);
            int endIndex = ch - 'A';
            array[endIndex]++;
            int sum = end - start;
            lastMax = Math.max(lastMax, array[endIndex]);
            int need = sum - lastMax;
            if (k - need >= 0) {
                result = Math.max(result, end - start);
            }
            if (start < end && k - need < 0) {
                char chStart = s.charAt(start);
                array[chStart - 'A']--;
                start++;
            }
        }
        return result;
    }


}
