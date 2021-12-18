package com.test.java;


import java.util.*;
import java.util.stream.Collectors;

class Solution {
    static int sum = 0;

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println((int) 'A');
        System.out.println((int) 'a');

        System.out.println(solution.uniquePathsIII(new int[][]{{1, 0}, {2, 0}}));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] array = s.toCharArray();
        int length = s.length();
        int max = 0;
        for1:
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                String a = s.substring(i, j + 1);
                if (a.length() > max) {
                    max = a.length();
                }
                int k = j + 1;
                if (k < length) {
                    String str = Character.toString(array[k]);
                    if (a.contains(str)) {
                        continue for1;
                    }
                }
            }
        }
        return max;
    }

    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            if (nums[0] == val) {
                return 0;
            } else {
                return 1;
            }
        }
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            while (start < end && nums[end] == val) {
                end--;
            }
            while (start < end && nums[start] != val) {
                start++;
            }
            if (start < end) {
                nums[start] = nums[end];
                nums[end] = val;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                return i - 1;
            }
        }
        return nums.length;


    }

    public static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int value = nums[mid];
            if (value == target) {
                return mid;
            } else if (value > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        int max = Math.max(start, end);
        if (max > nums.length - 1) {
            max = nums.length - 1;
        }
        int min = Math.min(start, end);
        if (min < 0) {
            min = 0;
        }
        if (target < nums[min]) {
            return min;
        } else if (target < nums[max]) {
            return max;
        } else {
            return end + 1;
        }


    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return resultList;
        }
        Arrays.sort(candidates);
        List<Integer> result = new ArrayList<>();
        conbine(result, candidates, target, resultList, 0);
        return resultList;
    }

    public void conbine(List<Integer> result, int[] candidates, int target, List<List<Integer>> resultList, int index) {
        int sum = 0;
        for (Integer integer : result) {
            sum = sum + integer;
        }
        if (sum == target) {
            resultList.add(new ArrayList<>(result));
        } else if (sum < target) {
            for (int i = index, candidatesLength = candidates.length; i < candidatesLength; i++) {
                int candidate = candidates[i];
                result.add(candidate);
                conbine(result, candidates, target, resultList, i);
                result.remove(Integer.valueOf(candidate));
            }
        }
    }

    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        StringBuilder builder = new StringBuilder();
        boolean flag = true;
        boolean hasWord = false;
        int signcount = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= '0' && ch <= '9') {
                hasWord = true;
                builder.append(ch);
            } else {
                if (hasWord) {
                    break;
                } else if (ch == '-') {
                    if (signcount == 0) {
                        signcount++;
                        flag = false;
                    } else {
                        return 0;
                    }
                } else if (ch == ' ') {
                    continue;
                } else if (ch == '+') {
                    if (signcount == 0) {
                        signcount++;
                        continue;
                    } else {
                        return 0;
                    }
                } else if (ch == '.') {
                    if (!hasWord) {
                        return 0;
                    } else {
                        break;
                    }
                } else {
                    if (!hasWord) {
                        return 0;
                    } else {
                        break;
                    }
                }
            }
        }

        if (builder.length() == 0) {
            return 0;
        }
        long value = Long.parseLong(builder.toString());
        if (!flag) {
            value = -value;
        }
        if (value >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (value <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return (int) value;
        }
    }

    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int end = -1;
        int start = 0;
        int flag = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ' && flag == 0) {
                flag++;
                end = i;
            } else if (s.charAt(i) == ' ' && flag == 1) {
                start = i + 1;
                flag++;

            }
        }
        return end - start + 1;

    }

    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        if (haystack.length() == 0 && needle.length() == 0) {
            return 0;
        }
        if (haystack.length() < needle.length()) {
            return -1;
        }
        for (int i = 0; i < haystack.length(); i++) {
            for (int j = 0; j < needle.length() && (i + j) < haystack.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                } else if (j == needle.length() - 1) {
                    return i;
                }
            }
        }
        return -1;

    }

    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }
        int plus = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            int num = digits[i];
            if (i == digits.length - 1) {
                num = num + 1;
                if (num == 10) {
                    num = 0;
                    plus = 1;
                    if (digits.length == 1) {
                        return new int[]{1, num};
                    }
                } else {
                    digits[i] = num;
                    return digits;
                }
                digits[i] = num;
            } else if (i > 0) {
                if (num < 9) {
                    digits[i] = num + 1;
                    return digits;
                } else {
                    digits[i] = 0;
                }
            } else {
                if (num < 9) {
                    digits[i] = num + 1;
                    return digits;
                } else {
                    digits[i] = 0;
                    int[] result = new int[digits.length + 1];
                    result[0] = 1;
                    for (int j = 0; j < digits.length; j++) {
                        result[j + 1] = digits[j];
                    }
                    return result;
                }
            }
        }
        return null;
    }

    public String addBinary(String a, String b) {
        int length = Math.max(a.length(), b.length());
        StringBuilder builder = new StringBuilder();
        int add = 0;
        for (int i = 0; i < length; i++) {
            int aValue = 0;
            if (i <= a.length() - 1 && a.charAt(a.length() - 1 - i) == '1') {
                aValue = 1;
            }
            int bValue = 0;
            if (i <= b.length() - 1 && a.charAt(a.length() - 1 - i) == '1') {
                bValue = 1;
            }
            int sum = add + aValue + bValue;
            if (sum == 0) {
                add = 0;
                builder.append('0');
            } else if (sum == 1) {
                add = 0;
                builder.append('1');
            } else if (sum == 2) {
                add = 1;
                builder.append('0');
            } else {
                add = 1;
                builder.append('1');
            }
        }
        if (add == 1) {
            builder.append('1');
        }
        return builder.reverse().toString();

    }

    public void setZeroes(int[][] matrix) {

        int[] width = new int[matrix.length];
        int[] height = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    width[i] = 1;
                    height[i] = 1;
                }
            }
        }
        for (int k = 0; k < width.length; k++) {
            if (width[k] == 1) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[k][j] = 1;
                }
            }
        }
        for (int k = 0; k < height.length; k++) {
            if (height[k] == 1) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[j][k] = 1;
                }
            }
        }
    }

    public void reverseString(char[] s) {
        if (s == null || s.length <= 1) {
            return;
        }
        int i = 0;
        int j = s.length - 1;
        while (i < j) {
            char ch = s[i];
            s[i] = s[j];
            s[j] = ch;
            i++;
            j--;
        }

    }

    public String reverseWords(String s) {

        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] array = s.toCharArray();
        int start = 0;
        int end = 1;
        while (end < s.length()) {
            if (array[end] == ' ') {
                reverse(array, start, end - 1);
                start = end + 1;
                end = start + 1;
            }
            end++;
        }
        reverse(array, start, s.length() - 1);
        return new String(array);

    }

    public void reverse(char[] s, int i, int j) {
        while (i < j) {
            char ch = s[i];
            s[i] = s[j];
            s[j] = ch;
            i++;
            j--;
        }
    }

    public int[] searchRange(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                int first = mid;
                int last = mid;
                for (int i = mid; i < nums.length - 1; i++) {
                    if (nums[i] == target) {
                        first = i;
                    } else {
                        break;
                    }
                }
                for (int i = mid; i >= 0; i--) {
                    if (nums[i] == target) {
                        last = i;
                    } else {
                        break;
                    }
                }
                return new int[]{first, last};
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            if (nums[0] == target) {
                return 0;
            } else {
                return -1;
            }
        }
        int end1 = -1;
        for (int i = 1; i < nums.length; i++) {
            int num1 = nums[i];
            int num2 = nums[i - 1];
            if (num1 < num2) {
                end1 = i - 1;
            }
        }
        if (end1 == -1) {
            end1 = nums.length - 1;
        }
        int start = 0;
        int end = 0;
        if (target == nums[0]) {
            return 0;
        } else if (target > nums[0]) {
            start = 0;
            end = end1;
        } else {
            start = end1 + 1;
            end = nums.length - 1;
        }
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        if (matrix[0][0] > target) {
            return false;
        } else if (matrix[matrix.length - 1][0] < target) {
            return find(matrix[matrix.length - 1], target);
        }
        int start = 0;
        int end = matrix.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] < target) {
                if (mid == matrix.length - 1) {
                    return find(matrix[mid], target);
                } else {
                    if (matrix[mid + 1][0] > target) {
                        return find(matrix[mid], target);
                    } else {
                        start = mid + 1;
                    }
                }
            } else if (matrix[mid][0] > target) {
                if (mid == 0) {
                    return false;
                } else {
                    if (matrix[mid - 1][0] < target) {
                        return find(matrix[mid - 1], target);
                    } else {
                        end = mid - 1;
                    }
                }
            }
        }
        return false;
    }

    public boolean find(int[] array, int target) {
        int start = 0;
        int end = array.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (array[mid] == target) {
                return true;
            } else if (array[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> list = new ArrayList<>();
            if (i == 1) {
                list.add(1);
            } else {
                List<Integer> lastList = resultList.get(i - 2);
                for (int j = 0; j < i; j++) {
                    if (j == 0 || j == i - 1) {
                        list.add(1);
                    } else {
                        list.add(lastList.get(j - 1) + lastList.get(j));
                    }

                }
            }
            resultList.add(list);
        }
        return resultList;

    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> lastList = new ArrayList<>();
        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> list = new ArrayList<>();
            if (i == 1) {
                list.add(1);
            } else {
                for (int j = 0; j < i; j++) {
                    if (j == 0 || j == i - 1) {
                        list.add(1);
                    } else {
                        list.add(lastList.get(j - 1) + lastList.get(j));
                    }

                }
            }
            lastList = list;
        }
        return lastList;
    }

    public int[] twoSum(int[] numbers, int target) {
        int start = 0;
        int end = numbers.length - 1;
        while (start < end) {
            int value = numbers[start] + numbers[end];
            if (value == target) {
                return new int[]{start + 1, end + 1};
            } else if (value > target) {
                end--;
            } else {
                start++;
            }
        }
        return null;

    }

    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0] >= s ? 1 : 0;
        }
        int start = 0;
        int end = 0;
        int sum = nums[0];
        int minSize = Integer.MAX_VALUE;
        int size = 1;
        while (end < nums.length) {
            if (sum >= s) {
                minSize = Math.min(minSize, size);
                if (minSize == 1) {
                    return 1;
                }
                while (start < end && sum >= s) {
                    sum = sum - nums[start];
                    start++;
                    size--;
                    if (sum >= s) {
                        minSize = Math.min(minSize, size);
                    }
                }
            } else {
                size++;
                end++;
                if (end < nums.length) {
                    sum = sum + nums[end];
                }

            }
        }
        return minSize;

    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> resultList = new ArrayList<>();
        TreeSet<Integer> set = new TreeSet<>();
        combinationSum3(set, resultList, 0, n, k);
        return resultList;
    }

    public void combinationSum3(TreeSet<Integer> set, List<List<Integer>> resultList, int current, int n, int k) {
        if (set.size() == k) {
            if (current == n) {
                resultList.add(new ArrayList<>(set));
            }
            return;
        }
        if (current > n) {
            return;
        }
        int start = 0;
        if (!set.isEmpty()) {
            start = set.last() + 1;
        }
        for (int i = start; i <= 9; i++) {
            if (!set.contains(i)) {
                set.add(i);
                combinationSum3(set, resultList, current + i, n, k);
                set.remove(i);
            }
        }
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            } else {
                set.add(num);
            }
        }
        return false;

    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            Integer integer = map.get(num);
            if (integer == null) {
                map.put(num, i);
            } else {
                if (i - integer <= k) {
                    return true;
                } else {
                    map.put(num, i);
                }
            }
        }
        return false;
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        int start = 0;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num - nums[i - 1] != 1 || i == nums.length - 1) {
                if (start == i - 1) {
                    result.add(String.valueOf(nums[start]));
                } else {
                    result.add(nums[start] + "->" + nums[i - 1]);
                }
                if (i == nums.length - 1) {
                    result.add(String.valueOf(nums[i]));
                }
                start = i;
            }

        }
        return result;

    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return resultList;
        }
        List<Integer> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        permuteUnique(resultList, result, nums, used);
        return resultList;

    }

    public void permuteUnique(List<List<Integer>> resultList, List<Integer> result, int[] nums, boolean[] used) {
        if (result.size() == nums.length) {
            resultList.add(new ArrayList<>(result));
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && used[i - 1])) {
                continue;
            }
            used[i] = true;
            result.add(nums[i]);
            permuteUnique(resultList, result, nums, used);
            result.remove(result.size() - 1);
            used[i] = false;

        }
    }

    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        long start = 0;
        long end = x;
        while (start <= end) {
            long mid = (start + end) / 2;
            long value = mid * mid;
            if (value == x) {
                return (int) mid;
            } else if (value > x) {
                long less = (mid - 1) * (mid - 1);
                if (less <= x) {
                    return (int) (mid - 1);
                } else {
                    end = mid - 1;
                }
            } else {
                long greate = (mid + 1) * (mid + 1);
                if (greate > x) {
                    return (int) mid;
                } else {
                    start = mid + 1;
                }
            }
        }
        return 0;

    }

    public int largestRectangleArea(int[] heights) {
        int area = 0;
        for (int start = 0; start < heights.length - 1; start++) {
            int maxHeight = Integer.MAX_VALUE;
            for (int end = start; end < heights.length; end++) {
                maxHeight = Math.min(maxHeight, heights[end]);
                area = Math.max(area, (end - start + 1) * maxHeight);
            }
        }
        return area;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1;
        int index2 = n - 1;
        int end = m + n - 1;
        while (index2 >= 0 && index1 >= 0) {
            if (nums2[index2] <= nums1[index1]) {
                nums1[end] = nums2[index2];
                index2--;
            } else {
                nums1[end] = nums1[index1];
                index1--;
            }
            end--;
        }
        // nums2还剩余
        if (index2 > 0) {
            if (index2 + 1 >= 0) System.arraycopy(nums2, 0, nums1, 0, index2 + 1);
        }

    }

    public List<Integer> grayCode(int n) {
        Set<String> set = new LinkedHashSet<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append('0');
        }
        set.add(builder.toString());
        backTrace(set, (int) Math.pow(2, n), builder);
        return set.stream().map(s -> Integer.parseInt(s, 2)).collect(Collectors.toList());
    }

    public boolean backTrace(Set<String> set, int n, StringBuilder builder) {
        if (set.size() == n) {
            return true;
        }
        for (int j = 0; j < builder.length(); j++) {
            char ch = builder.charAt(j);
            builder.setCharAt(j, ch == '0' ? '1' : '0');
            String string = builder.toString();
            if (!set.contains(string)) {
                set.add(string);
                boolean flag = backTrace(set, n, builder);
                if (flag) {
                    return flag;
                }
                set.remove(string);
            }
            builder.setCharAt(j, ch);
        }
        return false;
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        backTrace(resultList, result, nums, used);
        return resultList;
    }

    public void backTrace(List<List<Integer>> resultList, List<Integer> result, int[] nums, boolean[] used) {
        resultList.add(new ArrayList<>(result));
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && used[i - 1])) {
                continue;
            }
            used[i] = true;
            result.add(nums[i]);
            backTrace(resultList, result, nums, used);
            result.remove(result.size() - 1);
            used[i] = false;
        }
    }

    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() < 2) {
            return false;
        }
        for (int i = 1; i < num.length() / 2 + 1; i++) {
            for (int j = 1; j < num.length() / 2 + 1; j++) {
                long num1 = Long.parseLong(num.substring(0, i));
                long num2 = Long.parseLong(num.substring(i, i + j));
                boolean flag = isAdditiveNumber(num1, num2, num, i + j);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isAdditiveNumber(long num1, long num2, String num, int start) {
        long num3 = num1 + num2;
        String numStr = String.valueOf(num3);
        if (start + numStr.length() == num.length()) {
            String str = num.substring(start, start + numStr.length());
            return numStr.equals(str);
        } else if (start + numStr.length() > num.length()) {
            return false;
        } else {
            String str = num.substring(start, start + numStr.length());
            if (!numStr.equals(str)) {
                return false;
            } else {
                return isAdditiveNumber(num2, num3, num, start + numStr.length());
            }
        }
    }

    public List<String> letterCasePermutation(String S) {
        if (S == null || S.length() == 0) {
            return Collections.emptyList();
        } else {
            return letterCasePermutation(S, 0, S.length() - 1);
        }

    }

    public List<String> letterCasePermutation(String s, int start, int end) {
        if (start > end) {
            List<String> list = new ArrayList<>();
            list.add("");
            return list;
        }
        char ch = s.charAt(start);
        List<String> list = letterCasePermutation(s, start + 1, end);
        List<String> resultList = new ArrayList<>();
        if (Character.isLetter(ch)) {
            char upper = Character.toUpperCase(ch);
            char lower = Character.toLowerCase(ch);
            for (String str : list) {
                resultList.add(lower + str);
                resultList.add(upper + str);
            }
        } else {
            for (String str : list) {
                resultList.add(ch + str);
            }
        }
        return resultList;
    }

    public List<Integer> splitIntoFibonacci(String S) {
        if (S == null || S.length() < 2) {
            return Collections.emptyList();
        }
        for (int i = 1; i < S.length() / 2 + 1; i++) {
            if (S.charAt(0) == '0' && i > 1) {
                continue;
            }
            for (int j = 1; j < S.length() / 2 + 1; j++) {
                if (j > 1 && S.charAt(i) == '0') {
                    continue;
                }
                int num1 = Integer.parseInt(S.substring(0, i));
                int num2 = Integer.parseInt(S.substring(i, i + j));
                List<Integer> list = new ArrayList<>();
                list.add(num1);
                list.add(num2);
                boolean flag = splitIntoFibonacci(num1, num2, S, i + j, list);
                if (flag) {
                    return list;
                }
            }
        }
        return Collections.emptyList();
    }

    public boolean splitIntoFibonacci(int num1, int num2, String num, int start, List<Integer> list) {
        int num3 = num1 + num2;
        String numStr = String.valueOf(num3);
        if (start + numStr.length() == num.length()) {
            String str = num.substring(start, start + numStr.length());
            return numStr.equals(str);
        } else if (start + numStr.length() > num.length()) {
            return false;
        } else {
            String str = num.substring(start, start + numStr.length());
            list.add(num3);
            if (!numStr.equals(str)) {
                return false;
            } else {
                return splitIntoFibonacci(num2, num3, num, start + numStr.length(), list);
            }
        }
    }

    public int uniquePathsIII(int[][] grid) {

        int total = 0;
        int startX = 0;
        int startY = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    total++;
                } else if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                }
            }
        }
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        uniquePathsIII(grid, startX, startY, visited, total + 1);
        return sum;
    }


    public void uniquePathsIII(int[][] grid, int x, int y, boolean[][] visited, int current) {
        if (grid[x][y] == 2) {
            if (current == 0) {
                sum++;
            }
            return;
        }
        visited[x][y] = true;
        if (x - 1 >= 0 && !visited[x - 1][y] && (grid[x - 1][y] == 0 || grid[x - 1][y] == 2)) {
            uniquePathsIII(grid, x - 1, y, visited, current - 1);
        }
        if (x + 1 < grid.length && !visited[x + 1][y] && (grid[x + 1][y] == 0 || grid[x + 1][y] == 2)) {
            uniquePathsIII(grid, x + 1, y, visited, current - 1);
        }
        if (y - 1 >= 0 && !visited[x][y - 1] && (grid[x][y - 1] == 0 || grid[x][y - 1] == 2)) {
            uniquePathsIII(grid, x, y - 1, visited, current - 1);
        }
        if (y + 1 < grid[0].length && !visited[x][y + 1] && (grid[x][y + 1] == 0 || grid[x][y + 1] == 2)) {
            uniquePathsIII(grid, x, y + 1, visited, current - 1);
        }
        visited[x][y] = false;
    }


}
