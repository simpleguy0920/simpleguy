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
public class Solution5 {

    static int result = 0;

    public static void main(String[] args) {
        Solution5 solution5 = new Solution5();
        System.out.println(solution5.hIndex(new int[]{0, 1, 0}));
        System.out.println(Arrays.toString("1:end:5".split("\\:")));


    }

    public String reverseWords(String s) {
        List<String> list = new ArrayList<>();
        int start = 0;
        int end = 1;
        while (end <= s.length() - 1) {
            char ch1 = s.charAt(start);
            char ch2 = s.charAt(end);
            if (ch1 != ' ' && (ch2 == ' ' || end == s.length() - 1)) {
                if (end == s.length() - 1) {
                    list.add(s.substring(start, end + 1));
                } else {
                    list.add(s.substring(start, end));
                }
                start = end++;
                end = start++;
            } else if (ch1 != ' ') {
                end++;
            } else if (ch1 == ' ') {
                if (start < end) {
                    start++;
                } else {
                    end++;
                }
            }
        }
        Collections.reverse(list);
        return String.join(" ", list);

    }

    public int compareVersion(String version1, String version2) {
        String[] array1 = version1.split("\\.");
        String[] array2 = version2.split("\\.");
        for (int i = 0; i < array1.length || i < array2.length; i++) {
            int versionA = 0;
            if (i < array1.length) {
                versionA = Integer.parseInt(array1[i]);
            }
            int versionB = 0;
            if (i < array2.length) {
                versionB = Integer.parseInt(array2[i]);
            }
            int flag = Integer.compare(versionA, versionB);
            if (flag != 0) {
                return flag;
            }
        }
        return 0;
    }

    public String shortestPalindrome(String s) {
        int start = 0;
        int end = 0;
        if (s.length() % 2 == 0) {
            start = s.length() / 2 - 1;
            end = start + 1;
        } else {
            start = s.length() / 2;
            end = start;
        }
        String string = isPalindrome(s, start, end);
        if (string != null) {
            return string;
        }

        while (start >= 0 && end < s.length()) {
            String string1 = isPalindrome(s, start, start);
            String string2 = isPalindrome(s, end, end);
            String string3 = null;
            if (start > 0) {
                string3 = isPalindrome(s, start - 1, start);
            }
            String string4 = null;

            if (end + 1 < s.length()) {
                string4 = isPalindrome(s, end, end + 1);
            }
            String result = null;
            if (string1 != null) {
                result = string1;
            }
            if (result != null && string2 != null && string2.length() < result.length()) {
                result = string2;
            }
            if (result != null && string3 != null && string3.length() < result.length()) {
                result = string3;
            }
            if (result != null && string4 != null && string4.length() < result.length()) {
                result = string4;
            }
            if (result != null) {
                return result;
            }
            start--;
            end++;
        }
        return null;
    }

    public String isPalindrome(String s, int start, int end) {
        int oriStart = start;
        int oriEnd = end;
        while (start >= 0 && end < s.length()) {
            if (s.charAt(start) != s.charAt(end)) {
                return null;
            }
            start--;
            end++;
        }
        int d1 = oriStart;
        int d2 = s.length() - 1 - oriEnd;
        if (d1 == d2) {
            return s;
        } else if (d1 > d2) {
            StringBuilder builder = new StringBuilder(s.substring(0, oriStart));
            for (int i = 0; i <= oriStart; i++) {
                builder.append(s.charAt(i));
            }
            for (int i = (oriStart == oriEnd ? oriStart - 1 : oriStart); i >= 0; i++) {
                builder.append(s.charAt(i));
            }
            return builder.toString();
        } else {
            StringBuilder builder = new StringBuilder(s.substring(0, oriStart));
            for (int i = s.length() - 1; i >= oriEnd; i--) {
                builder.append(s.charAt(i));
            }
            for (int i = (oriStart == oriEnd ? oriEnd + 1 : oriEnd); i < s.length(); i++) {
                builder.append(s.charAt(i));
            }
            return builder.toString();
        }
    }

    public int[] shortestSeq(int[] big, int[] small) {
        Set<Integer> set = new HashSet<>();
        for (int i : small) {
            set.add(i);
        }
        int[] result = new int[2];
        int min = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (end < big.length) {
            int value = big[end];
            if (!set.contains(value)) {
                end++;
                continue;
            }
            Integer count = map.get(value);
            if (count == null) {
                map.put(value, 1);
            } else {
                map.put(value, count + 1);
            }
            if (map.size() == set.size()) {
                if (end - start < min) {
                    min = end - start;
                    result = new int[]{start, end};
                }
                while (map.size() == set.size()) {
                    Integer removeValue = big[start];
                    count = map.get(removeValue);
                    if (count == null) {
                        start++;
                        continue;
                    }
                    count = map.get(removeValue);
                    if (count == 1) {
                        if (end - start < min) {
                            min = end - start;
                            result = new int[]{start, end};
                        }
                        map.remove(removeValue);
                    } else {
                        map.put(removeValue, count - 1);
                    }
                    start++;
                }
            }
            end++;
        }
        if (min < Integer.MAX_VALUE) {
            return result;
        } else {
            return null;
        }
    }

    public String decodeString(String s) {
        return decodeString(s, 0, s.length() - 1);
    }

    public String decodeString(String s, int start, int end) {
        StringBuilder builder = new StringBuilder();
        if (start == end) {
            return "";
        }
        StringBuilder number = new StringBuilder();
        int time = 1;
        StringBuilder str = new StringBuilder();
        for (int i = start; i < end; i++) {
            char character = s.charAt(i);
            if (Character.isDigit(character)) {
                number.append(character);
            } else if (character == '[') {
                if (number.length() > 0) {
                    time = Integer.parseInt(number.toString());
                }
                number = new StringBuilder();

            } else if (character == '}') {
                for (int k = 0; k < time; k++) {
                    builder.append(str);
                }
            } else {
                str.append(character);
            }
        }
        return s.substring(start, end);
    }

    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        LinkedList<Integer> list = new LinkedList<>();
        list.offer(start);
        visited[start] = true;
        while (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                int index = list.pop();
                if (arr[index] == 0) {
                    return true;
                }
                if (index - arr[index] >= 0 && !visited[index - arr[index]]) {
                    list.offer(index - arr[index]);
                    visited[index - arr[index]] = true;
                }
                if (index + arr[index] < arr.length && !visited[index + arr[index]]) {
                    list.offer(index + arr[index]);
                    visited[index + arr[index]] = true;
                }
            }
        }
        return false;
    }

    public int minJumps(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            List<Integer> list = map.get(value);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(i);
            map.put(value, list);
        }
        int lastIndex = arr.length - 1;
        int lastValue = arr[lastIndex];
        boolean[] vivisited = new boolean[arr.length];
        LinkedList<Integer> list = new LinkedList<>();
        list.offer(0);
        vivisited[0] = true;
        int time = 0;
        while (!list.isEmpty()) {
            int size = list.size();
            boolean hasValue = false;
            for (int i = 0; i < size; i++) {
                int index = list.pop();
                if (index == arr.length - 1) {
                    return time;
                }
                List<Integer> sameList = map.get(arr[index]);
                if (sameList != null) {
                    for (Integer integer : sameList) {
                        if (!vivisited[integer]) {
                            list.offer(integer);
                            vivisited[integer] = true;
                        }
                    }
                    map.remove(arr[index]);
                }
                if (index >= 1 && !vivisited[index - 1]) {
                    list.offer(index - 1);
                    vivisited[index - 1] = true;
                }
                if (index < arr.length - 1 && !vivisited[index + 1]) {
                    list.offer(index + 1);
                    vivisited[index + 1] = true;
                }
            }
            time++;
        }
        return 0;
    }

    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == i + 1) {
                continue;
            } else {
                if (num - 1 < nums.length && num >= 1 && num != nums[num - 1]) {
                    nums[i] = nums[num - 1];
                    nums[num - 1] = num;
                    i--;
                }
            }

        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public String largestNumber(int[] nums) {
        return Arrays.stream(nums).mapToObj(String::valueOf).sorted((o1, o2) -> {
            if (o1.length() == o2.length()) {
                for (int i = 0; i < o1.length(); i++) {
                    char ch1 = o1.charAt(i);
                    char ch2 = o2.charAt(i);
                    if (ch1 != ch2) {
                        return ch2 - ch1;
                    }
                }
                return 0;
            } else {
                String str1 = o1 + o2;
                String str2 = o2 + o1;
                for (int i = 0; i < o1.length() + o2.length(); i++) {
                    char ch1 = str1.charAt(i);
                    char ch2 = str2.charAt(i);
                    if (ch1 != ch2) {
                        return ch2 - ch1;
                    }
                }
                return 0;
            }
        }).collect(Collectors.joining());

    }

    public boolean isLongPressedName(String name, String typed) {
        if (typed.length() < name.length()) {
            return false;
        }
        int current1 = 0;
        int current2 = 0;
        while (current1 < name.length() && current2 < typed.length()) {
            char ch1 = name.charAt(current1);
            char ch2 = typed.charAt(current2);
            if (ch1 == ch2) {
                current1++;
                current2++;
            } else {
                current2++;
            }
        }
        if (current1 == name.length() && current2 == typed.length()) {
            return true;
        } else if (current2 == typed.length()) {
            return false;
        } else {
            for (int i = current2; i < typed.length(); i++) {
                char ch2 = typed.charAt(i);
                if (ch2 != name.charAt(name.length() - 1)) {
                    return false;
                }
            }
            return true;
        }

    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        Set<Integer> set2 = new HashSet<>();
        for (int i : nums2) {
            if (set.contains(i)) {
                set2.add(i);
            }
        }
        int[] result = new int[set2.size()];
        int i = 0;
        for (Integer integer : set2) {
            result[i] = integer;
            i++;
        }
        return result;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int i : nums1) {
            map1.merge(i, 1, Integer::sum);
        }
        for (int i : nums2) {
            map2.merge(i, 1, Integer::sum);
        }
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map2.entrySet()) {
            if (map1.containsKey(entry.getKey())) {
                int count1 = map1.get(entry.getKey());
                int size = Math.min(count1, entry.getValue());
                for (int i = 0; i < size; i++) {
                    list.add(entry.getKey());
                }
            }
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;

    }

    public String removeDuplicateLetters(String s) {
        Deque<Character> deque = new ArrayDeque<>(s.length());
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.merge(ch, 1, Integer::sum);
        }
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.get(ch) - 1);

            if (!set.contains(ch)) {
                deque.push(ch);
                set.add(ch);
            } else {
                while (!deque.isEmpty() && deque.peek() > ch) {
                    char ch2 = deque.peek();
                    if (map.get(ch2) == 0) {
                        break;
                    }
                    deque.pop();
                    set.remove(ch2);
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        while (!deque.isEmpty()) {
            builder.append(deque.removeLast());
        }
        return builder.toString();

    }


    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() < nums2[i]) {
                stack.pop();
            }
            map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
            stack.push(nums2[i]);
        }
        int[] array = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            array[i] = map.get(nums1[i]);
        }
        return array;
    }

    public int[] nextGreaterElements(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>(2 * nums.length - 1);
        int[] array = new int[nums.length];
        for (int i = 2 * nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i % nums.length]) {
                stack.pop();
            }
            array[i % nums.length] = stack.isEmpty() ? -1 : nums[stack.peek()];
            stack.push(i % nums.length);
        }
        return array;
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] array = new int[n];
        Deque<String> deque = new ArrayDeque<>();
        int sum = 0;
        for (String log : logs) {
            if (log.contains("start")) {
                deque.push(log);
            } else {
                String[] end = log.split("\\:");
                String[] start = deque.pop().split("\\:");
                int time = Integer.parseInt(end[2]) - Integer.parseInt(start[2]);
                array[Integer.parseInt(start[0])] = time - sum;
                sum = time;
            }
        }

        return array;
    }

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, List<Integer>> map1 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            List<Integer> list = map1.get(ch);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(i);
            map1.put(ch, list);
        }

        Map<Character, List<Integer>> map2 = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            List<Integer> list2 = map2.get(ch);
            if (list2 == null) {
                list2 = new ArrayList<>();
            }
            list2.add(i);
            map2.put(ch, list2);
        }
        if (map1.size() != map2.size()) {
            return false;
        }

        Map<Integer, List<Integer>> mapA = new HashMap<>();
        for (List<Integer> value : map1.values()) {
            value.sort(Comparator.comparing(Integer::intValue));
            mapA.put(value.get(0), value);
        }
        Map<Integer, List<Integer>> mapB = new HashMap<>();
        for (List<Integer> value : map2.values()) {
            value.sort(Comparator.comparing(Integer::intValue));
            mapA.put(value.get(0), value);
        }
        return mapB.equals(mapB);
    }

    public int hIndex(int[] citations) {
        int maxSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int citation : citations) {
            map.merge(citation, 1, Integer::sum);
        }
        int sum = 0;
        List<Map.Entry<Integer, Integer>> list = map.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getKey)).collect(Collectors.toList());
        for (int i = list.size() - 1; i >= 0; i--) {
            Map.Entry<Integer, Integer> entry = list.get(i);
            sum++;
            if (entry.getKey() >= sum) {
                if (sum >= maxSum) {
                    maxSum = sum;
                }
            }
        }
        return maxSum;

    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.merge(word, 1, Integer::sum);
        }
        TreeMap<Integer, String> map2 = new TreeMap<>();
        List<Map.Entry<String, Integer>> list = map.entrySet().stream().sorted((o1, o2) -> {
            if (!o1.getValue().equals(o2.getValue())) {
                return o2.getValue().compareTo(o1.getValue());
            } else {
                return o1.getKey().compareTo(o2.getKey());
            }
        }).collect(Collectors.toList());
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            resultList.add(list.remove(list.size() - 1).getKey());
        }
        return resultList;

    }

    public int[] findErrorNums(int[] nums) {
        boolean[] array = new boolean[nums.length];
        int[] result = new int[2];
        for (int num : nums) {
            if (array[num - 1]) {
                result[0] = num;
            } else {
                array[num - 1] = true;
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (!array[i]) {
                result[1] = i + 1;
                break;
            }
        }
        return result;
    }

    public char firstUniqChar(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.merge(ch, 1, Integer::sum);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return '1';
    }

    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        } else if (nums.length == 2) {
            int temp = nums[1];
            nums[1] = nums[0];
            nums[0] = temp;
        }


    }

    public int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }
        return 0;
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        return map.entrySet().stream().sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue())).limit(k).mapToInt(Map.Entry::getKey).toArray();
    }
}