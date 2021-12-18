package com.test.java.solution;

public class MaxArea {
    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int maxSize = 0;
        int length = height.length;
        for (int i = 0; i < length; i++) {
            int size;
            for (int j = i + 1; j < length; j++) {
                if (height[i] > height[j]) {
                    size = (j - i) * height[j];
                } else {
                    size = (j - i) * height[i];
                }
                if (size > maxSize) {
                    maxSize = size;
                }
            }
        }
        return maxSize;

    }
}
