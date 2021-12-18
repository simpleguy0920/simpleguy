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
public class SolutionHash {
    public static void main(String[] args) {
        System.out.println(new Character((char) ('3' - 0)));
        System.out.println(new Date(1600842600l * 1000));
        char[][] array = new char[][]
                {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        SolutionHash solutionHash = new SolutionHash();
        solutionHash.solveSudoku(array);
        System.out.println(array);
    }

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (true) {
            char[] array = String.valueOf(n).toCharArray();
            int sum = 0;
            for (char c : array) {
                int value = Integer.valueOf(String.valueOf(c));
                sum = sum + value * value;
            }
            n = sum;
            if (sum == 1) {
                return true;
            } else {
                if (set.contains(sum)) {
                    return false;
                } else {
                    set.add(sum);
                }
            }
        }

    }

    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length < 9 || board[0].length < 9) {
            return false;
        }
        Set<Character> wSet = new HashSet<>();
        Set<Character> hSet = new HashSet<>();
        Set<Character> aSet = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            wSet.clear();
            hSet.clear();
            for (int j = 0; j < 9; j++) {
                Character c1 = board[i][j];
                Character c2 = board[j][i];
                if (c1 != '.') {
                    if (wSet.contains(c1)) {
                        return false;
                    } else {
                        wSet.add(c1);
                    }
                }
                if (c2 != '.') {
                    if (hSet.contains(c2)) {
                        return false;
                    } else {
                        hSet.add(c2);
                    }
                }

            }
        }
        for (int i = 0; i < 9; i = i + 3) {
            for (int j = 0; j < 9; j = j + 3) {
                aSet.clear();
                for (int m = i; m < i + 3; m++) {
                    for (int n = j; n < j + 3; n++) {
                        Character c3 = board[m][n];
                        if (c3 != '.') {
                            if (aSet.contains(c3)) {
                                return false;
                            } else {
                                aSet.add(c3);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public void solveSudoku(char[][] board) {
        List<Character> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            list.add(String.valueOf(i).charAt(0));
        }
        backStace2(board, list, 0, 0);
    }

    public boolean backStace(char[][] board, List<Character> list, int x, int y) {

        boolean successFlag = true;
        int i = 0;
        int j = 0;
        lable1:
        for (i = x; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    successFlag = false;
                    break lable1;
                }
            }
        }


        if (successFlag) {
            return true;
        } else {
            Set<Character> newSet = new HashSet<>(list);
            for (int t = 0; t < 9; t++) {
                newSet.remove(board[i][t]);
                newSet.remove(board[t][j]);
            }
            int startX = i - i % 3;
            int startY = j - j % 3;
            if (!newSet.isEmpty()) {
                for (int k = startX; k < startX + 3; k++) {
                    for (int l = startY; l < startY + 3; l++) {
                        newSet.remove(board[k][l]);
                    }
                }
            }
            if (newSet.isEmpty()) {
                return false;
            }
            for (Character character : newSet) {
                board[i][j] = character;
                boolean flag = backStace(board, list, i, j);
                if (flag) {
                    return true;
                } else {
                    board[i][j] = '.';
                }
            }
            return false;
        }
    }

    public boolean backStace2(char[][] board, List<Character> list, int x, int y) {
        boolean successFlag = true;
        int i = 0;
        int j = 0;
        lable1:
        for (i = x; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    successFlag = false;
                    break lable1;
                }
            }
        }

        if (successFlag) {
            return true;
        } else {
            boolean[] array = new boolean[9];
            for (int t = 0; t < 9; t++) {
                if (board[i][t] != '.') {
                    array[board[i][t] - '1'] = true;
                }
                if (board[t][j] != '.') {
                    array[board[t][j] - '1'] = true;
                }
            }

            int startX = i - i % 3;
            int startY = j - j % 3;
            for (int k = startX; k < startX + 3; k++) {
                for (int l = startY; l < startY + 3; l++) {
                    if (board[k][l] != '.') {
                        array[board[k][l] - '1'] = true;
                    }
                }
            }
            for (int t = 0; t < array.length; t++) {
                boolean use = array[t];
                if (!use) {
                    board[i][j] = (char) ('1' + t);
                    boolean flag = backStace2(board, list, i, j);
                    if (flag) {
                        return true;
                    } else {
                        board[i][j] = '.';
                    }
                }
            }
            return false;
        }
    }

    public String minWindow(String s, String t) {
        String result = "";
        Map<Character, Integer> map = new HashMap();
        for (char c : t.toCharArray()) {
            map.put(c, 0);
        }
        char[] array = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!map.containsKey(ch)) {
                continue;
            } else {
                Integer count = map.get(ch);
                if (count > 0) {
                    continue;
                } else {
                }
            }

        }
        return result;
    }

    public String frequencySort(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.merge(ch, 1, Integer::sum);
        }
        List<Map.Entry<Character, Integer>> list = map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toList());
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : list) {
            for (int i = 0; i < entry.getValue(); i++) {
                builder.append(entry.getKey());
            }
        }
        return builder.toString();
    }

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : A) {
            for (int j : B) {
                int sumAB = i + j;
                map.merge(sumAB, 1, Integer::sum);
            }
        }
        for (int i : C) {
            for (int j : D) {
                int sumCD = -i - j;
                Integer count = map.get(sumCD);
                if (count != null) {
                    sum = sum + count;
                }
            }
        }
        return sum;
    }

    public int islandPerimeter(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    int value = 4;
                    if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                        value--;
                    }
                    if (i + 1 < grid.length && grid[i + 1][j] == 1) {
                        value--;
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                        value--;
                    }
                    if (j + 1 < grid[i].length && grid[i][j + 1] == 1) {
                        value--;
                    }
                    sum = sum + value;
                }
            }
        }
        return sum;

    }

    public String[] findWords(String[] words) {
        String s1 = "qwertyuiop";
        String s2 = "asdfghjkl";
        String s3 = "zxcvbnm";
        Set<Character> set1 = new HashSet<>();
        for (int i = 0; i < s1.length(); i++) {
            set1.add(s1.charAt(i));
        }
        Set<Character> set2 = new HashSet<>();
        for (int i = 0; i < s2.length(); i++) {
            set2.add(s2.charAt(i));
        }
        Set<Character> set3 = new HashSet<>();
        for (int i = 0; i < s3.length(); i++) {
            set3.add(s3.charAt(i));
        }
        List<String> result = new ArrayList<>();
        for (String word : words) {
            boolean flag = true;
            Set<Character> set = null;
            for (int i = 0; i < word.length(); i++) {
                Character ch = Character.toLowerCase(word.charAt(i));
                if (i == 0) {
                    if (set1.contains(ch)) {
                        set = set1;
                    } else if (set2.contains(ch)) {
                        set = set2;
                    } else {
                        set = set3;
                    }
                } else {
                    if (!set.contains(ch)) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                result.add(word);
            }
        }
        return result.toArray(new String[0]);

    }
}

