package com.test.java.solution;

import java.util.HashMap;
import java.util.Map;

public class MaxString {
    public static void main(String[] args) {
        int length = lengthOfLongestSubstring("b");
        System.out.println(length);
    }


    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        char[] array = s.toCharArray();

        Map<Character, Integer> map = new HashMap<>();
        for (int start = 0, end = 0; end < array.length; end++) {
            if (!map.containsKey(array[end])) {
                map.put(array[end], end);
                maxLength = Math.max(maxLength, end - start + 1);
            } else {
                int index = map.get(array[end]);
                start = Math.max(start, index + 1);
                map.put(array[end], end);
                maxLength = Math.max(maxLength, end - start + 1);
            }


        }
        return maxLength;
    }


}
