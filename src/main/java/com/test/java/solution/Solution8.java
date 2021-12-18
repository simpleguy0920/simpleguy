package com.test.java.solution;

import com.google.common.collect.Lists;
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
public class Solution8 {
    public static void main(String[] args) {
        Solution8 solution8 = new Solution8();
        long start = System.currentTimeMillis();
        List<List<Integer>> list = new ArrayList<>();
        list.add(Lists.newArrayList(1));
        list.add(Lists.newArrayList(2));
        list.add(Lists.newArrayList(3));
        list.add(Lists.newArrayList());
        int[][] array = {{21, 47}, {4, 41}, {2, 41}, {36, 42}, {32, 45}, {26, 28}, {32, 44}, {5, 41}, {29, 44}, {10, 46}, {1, 6}, {7, 42}, {46, 49}, {17, 46}, {32, 35}, {11, 48}, {37, 48}, {37, 43}, {8, 41}, {16, 22}, {41, 43}, {11, 27}, {22, 44}, {22, 28}, {18, 37}, {5, 11}, {18, 46}, {22, 48}, {1, 17}, {2, 32}, {21, 37}, {7, 22}, {23, 41}, {30, 39}, {6, 41}, {10, 22}, {36, 41}, {22, 25}, {1, 12}, {2, 11}, {45, 46}, {2, 22}, {1, 38}, {47, 50}, {11, 15}, {2, 37}, {1, 43}, {30, 45}, {4, 32}, {28, 37}, {1, 21}, {23, 37}, {5, 37}, {29, 40}, {6, 42}, {3, 11}, {40, 42}, {26, 49}, {41, 50}, {13, 41}, {20, 47}, {15, 26}, {47, 49}, {5, 30}, {4, 42}, {10, 30}, {6, 29}, {20, 42}, {4, 37}, {28, 42}, {1, 16}, {8, 32}, {16, 29}, {31, 47}, {15, 47}, {1, 5}, {7, 37}, {14, 47}, {30, 48}, {1, 10}, {26, 43}, {15, 46}, {42, 45}, {18, 42}, {25, 42}, {38, 41}, {32, 39}, {6, 30}, {29, 33}, {34, 37}, {26, 38}, {3, 22}, {18, 47}, {42, 48}, {22, 49}, {26, 34}, {22, 36}, {29, 36}, {11, 25}, {41, 44}, {6, 46}, {13, 22}, {11, 16}, {10, 37}, {42, 43}, {12, 32}, {1, 48}, {26, 40}, {22, 50}, {17, 26}, {4, 22}, {11, 14}, {26, 39}, {7, 11}, {23, 26}, {1, 20}, {32, 33}, {30, 33}, {1, 25}, {2, 30}, {2, 46}, {26, 45}, {47, 48}, {5, 29}, {3, 37}, {22, 34}, {20, 22}, {9, 47}, {1, 4}, {36, 46}, {30, 49}, {1, 9}, {3, 26}, {25, 41}, {14, 29}, {1, 35}, {23, 42}, {21, 32}, {24, 46}, {3, 32}, {9, 42}, {33, 37}, {7, 30}, {29, 45}, {27, 30}, {1, 7}, {33, 42}, {17, 47}, {12, 47}, {19, 41}, {3, 42}, {24, 26}, {20, 29}, {11, 23}, {22, 40}, {9, 37}, {31, 32}, {23, 46}, {11, 38}, {27, 29}, {17, 37}, {23, 30}, {14, 42}, {28, 30}, {29, 31}, {1, 8}, {1, 36}, {42, 50}, {21, 41}, {11, 18}, {39, 41}, {32, 34}, {6, 37}, {30, 38}, {21, 46}, {16, 37}, {22, 24}, {17, 32}, {23, 29}, {3, 30}, {8, 30}, {41, 48}, {1, 39}, {8, 47}, {30, 44}, {9, 46}, {22, 45}, {7, 26}, {35, 42}, {1, 27}, {17, 30}, {20, 46}, {18, 29}, {3, 29}, {4, 30}, {3, 46}};
        String result = solution8.intToRoman(20);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(result);
    }

    public boolean isSolvable(String[] words, String result) {
        Set<Character> allSet = new HashSet<>();
        Set<Character> noZeroSet = new HashSet<>();
        int maxlength = 0;
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                allSet.add(ch);
            }
            if (word.length() > 1) {
                noZeroSet.add(word.charAt(0));
            }
            maxlength = Math.max(maxlength, word.length());
        }
        if (result.length() < maxlength) {
            return false;
        }
        for (int i = 0; i < result.length(); i++) {
            char ch = result.charAt(i);
            allSet.add(ch);
        }
        if (result.length() > 1) {
            noZeroSet.add(result.charAt(0));
        }
        Map<Character, Integer> resultMap = new HashMap<>();
        boolean[] used = new boolean[10];
        Deque<Character> allList = new ArrayDeque<>();
        allList.addFirst(result.charAt(result.length() - 1));
        allSet.remove(result.charAt(result.length() - 1));
        for (String word : words) {
            allList.addFirst(word.charAt(word.length() - 1));
            allSet.remove(word.charAt(word.length() - 1));
        }
        for (Character character : allSet) {
            allList.addFirst(character);
        }
        return backTrace(used, resultMap, allList, noZeroSet, words, result);
    }

    public boolean backTrace(boolean[] used, Map<Character, Integer> map, Deque<Character> allList, Set<Character> noZeroSet, String[] words, String result) {
        if (allList.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < result.length(); i++) {
                stringBuilder.append(map.get(result.charAt(i)));
            }
            int resultNum = Integer.parseInt(stringBuilder.toString());
            int sum = 0;
            for (String word : words) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < word.length(); i++) {
                    builder.append(map.get(word.charAt(i)));
                }
                sum = sum + Integer.parseInt(builder.toString());
                if (sum > resultNum) {
                    return false;
                }
            }
            return (sum == resultNum);
        }
        for (int i = 0; i <= 9; i++) {
            if (!used[i]) {
                char ch = allList.getFirst();
                if (i == 0 && noZeroSet.contains(ch)) {
                    continue;
                }
                allList.removeFirst();
                map.put(ch, i);
                used[i] = true;
                boolean flag = backTrace(used, map, allList, noZeroSet, words, result);
                if (flag) {
                    return true;
                }
                map.remove(ch);
                used[i] = false;
                allList.addFirst(ch);
            }
        }
        return false;

    }

    public int[] numsSameConsecDiff(int n, int k) {
        Set<Integer> result = new HashSet<>();
        backTrace(result, new ArrayDeque<>(), n, k);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }


    public void backTrace(Set<Integer> result, Deque<Integer> currentList, int n, int k) {
        if (currentList.size() == n) {
            int sum = 0;
            for (Integer current : currentList) {
                sum = sum * 10 + current;
            }
            result.add(sum);
            return;
        }
        if (currentList.size() == 0) {
            for (int i = 1; i <= 9; i++) {
                currentList.addLast(i);
                backTrace(result, currentList, n, k);
                currentList.removeLast();
            }
        } else {
            int last = currentList.getLast();
            int value1 = last + k;
            if (value1 <= 9) {
                currentList.addLast(value1);
                backTrace(result, currentList, n, k);
                currentList.removeLast();
            }
            value1 = last - k;
            if (value1 >= 0) {
                currentList.addLast(value1);
                backTrace(result, currentList, n, k);
                currentList.removeLast();
            }
        }
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int count = rooms.size();
        boolean[] visited = new boolean[count];
        canVisitAllRooms(visited, rooms, 0);
        for (boolean flag : visited) {
            if (!flag) {
                return false;
            }
        }
        return true;
    }

    public void canVisitAllRooms(boolean[] visited, List<List<Integer>> rooms, int current) {
        List<Integer> keys = rooms.get(current);
        if (keys == null || keys.isEmpty()) {
            return;
        }
        for (Integer key : keys) {
            if (visited[key]) {
                continue;
            }
            canVisitAllRooms(visited, rooms, key);
        }
    }

    public boolean possibleBipartition(int N, int[][] dislikes) {
        boolean[] a = new boolean[N];
        boolean[] b = new boolean[N];
        for (int[] dislike : dislikes) {
            if (dislike[0] > dislike[1]) {
                int temp = dislike[1];
                dislike[1] = dislike[0];
                dislike[0] = temp;
            }
        }
        Arrays.sort(dislikes, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o1[1], o2[1]);
            } else {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        System.out.println(Arrays.deepToString(dislikes));
        return possibleBipartition(a, b, dislikes, 0);
    }

    public boolean possibleBipartition(boolean[] a, boolean[] b, int[][] dislikes, int current) {
        if (current == dislikes.length) {
            return true;
        }
        int dislikeA = dislikes[current][0];
        int dislikeB = dislikes[current][1];

        // A,B同在一个集合 失败
        if (a[dislikeA] && a[dislikeB]) {
            List<Integer> aList = new ArrayList<>();
            for (int i = 0; i < a.length; i++) {
                boolean aa = a[i];
                if (aa) {
                    aList.add(i);
                }
            }
            List<Integer> bList = new ArrayList<>();
            for (int i = 0; i < b.length; i++) {
                boolean bb = b[i];
                if (bb) {
                    bList.add(i);
                }
            }
            System.out.println("error :a" + StringUtils.join(aList) + " b:" + StringUtils.join(bList) + "  A;" + dislikeA + " B:" + dislikeB);
            return false;
        } else if (b[dislikeA] && b[dislikeB]) {
            List<Integer> aList = new ArrayList<>();
            for (int i = 0; i < a.length; i++) {
                boolean aa = a[i];
                if (aa) {
                    aList.add(i);
                }
            }
            List<Integer> bList = new ArrayList<>();
            for (int i = 0; i < b.length; i++) {
                boolean bb = b[i];
                if (bb) {
                    bList.add(i);
                }
            }
            System.out.println("error :a" + StringUtils.join(aList) + " b:" + StringUtils.join(bList) + "  A;" + dislikeA + " B:" + dislikeB);
            return false;
            //A 在a里
        } else if (a[dislikeA]) {
            b[dislikeB] = true;
            boolean flag = possibleBipartition(a, b, dislikes, current + 1);
            b[dislikeB] = false;
            return flag;
            //A在b里
        } else if (b[dislikeA]) {
            a[dislikeB] = true;
            boolean flag = possibleBipartition(a, b, dislikes, current + 1);
            a[dislikeB] = false;
            return flag;
            //B在a里
        } else if (a[dislikeB]) {
            b[dislikeA] = true;
            boolean flag = possibleBipartition(a, b, dislikes, current + 1);
            b[dislikeA] = false;
            return flag;
            //B在b里
        } else if (b[dislikeB]) {
            a[dislikeA] = true;
            boolean flag = possibleBipartition(a, b, dislikes, current + 1);
            a[dislikeA] = false;
            return flag;
            //A，B都不在a,b
        } else {
            boolean originA = a[dislikeA];
            boolean originB = b[dislikeB];
            // A a ,B b尝试
            a[dislikeA] = true;
            b[dislikeB] = true;
            boolean flag = possibleBipartition(a, b, dislikes, current + 1);
            a[dislikeA] = originA;
            b[dislikeB] = originB;
            if (flag) {
                return true;
            }
            if (current == 0) {
                return false;
            }

            // A b,B a尝试

            a[dislikeB] = true;
            b[dislikeA] = true;
            return possibleBipartition(a, b, dislikes, current + 1);
        }


    }

    public String intToRoman(int num) {
        int[] value = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] strArray = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder builder = new StringBuilder();
        while (num > 0) {
            for (int i = 0; i < value.length; i++) {
                if (num >= value[i]) {
                    builder.append(strArray[i]);
                    num = num - value[i];
                    break;
                }
            }
        }
        return builder.toString();
    }

    public int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("M", 1000);
        map.put("CM", 900);
        map.put("D", 500);
        map.put("CD", 400);
        map.put("C", 100);
        map.put("XC", 90);
        map.put("L", 50);
        map.put("XL", 40);
        map.put("X", 10);
        map.put("IX", 9);
        map.put("V", 5);
        map.put("IV", 4);
        map.put("I", 1);
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            String str = null;
            if (i + 1 < s.length() && map.get(s.substring(i + 1, i + 2)) > map.get(s.substring(i, i + 1))) {
                str = s.substring(i, i + 2);
                i += 2;
            } else {
                str = s.substring(i, i + 1);
                i++;
            }
            result = result + map.get(str);
        }
        return result;


    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                sum = sum + prices[i + 1] - prices[i];
            }
        }
        return sum;
    }

}
