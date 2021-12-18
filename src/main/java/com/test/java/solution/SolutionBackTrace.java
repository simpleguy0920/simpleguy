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
public class SolutionBackTrace {

    static int result = 0;

    public static void main(String[] args) {
        SolutionBackTrace solutionBackTrace = new SolutionBackTrace();
        System.out.println(solutionBackTrace.numSquarefulPerms(new int[]{1, 8, 17}));
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> resultList = new ArrayList<>();
        LinkedHashSet<Integer> set = new LinkedHashSet<>(n);
        solveNQueens(resultList, set, n);
        return resultList;
    }

    public void solveNQueens(List<List<String>> resultList, LinkedHashSet<Integer> set, int n) {
        if (set.size() == n) {
            List<String> list = new ArrayList<>();
            for (Integer position : set) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (j == position) {
                        builder.append("Q");
                    } else {
                        builder.append(".");
                    }
                }
                list.add(builder.toString());
            }
            resultList.add(list);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!set.contains(i)) {
                int x = set.size();
                int startX = 0;
                boolean flag = true;
                for (Integer value : set) {
                    if (Math.abs(x - startX) == Math.abs(i - value)) {
                        flag = false;
                        break;
                    }
                    startX++;
                }
                if (flag) {
                    set.add(i);
                    solveNQueens(resultList, set, n);
                    set.remove(i);
                }
            }
        }
    }

    public int totalNQueens(int n) {
        result = 0;
        LinkedHashSet<Integer> set = new LinkedHashSet<>(n);
        totalNQueens(set, n);
        return result;
    }

    public void totalNQueens(LinkedHashSet<Integer> set, int n) {
        if (set.size() == n) {
            result++;
        }
        for (int i = 0; i < n; i++) {
            if (!set.contains(i)) {
                int x = set.size();
                int startX = 0;
                boolean flag = true;
                for (Integer value : set) {
                    if (Math.abs(x - startX) == Math.abs(i - value)) {
                        flag = false;
                        break;
                    }
                    startX++;
                }
                if (flag) {
                    set.add(i);
                    totalNQueens(set, n);
                    set.remove(i);
                }
            }
        }
    }

    public int countArrangement(int N) {
        Deque<Integer> list = new ArrayDeque<>(N);
        boolean[] visited = new boolean[N + 1];
        result = 0;
        countArrangement(list, visited, N);
        return result;
    }

    public void countArrangement(Deque<Integer> list, boolean[] visited, int n) {
        if (list.size() == n) {
            result++;
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                int position = list.size() + 1;
                if (i % position == 0 || position % i == 0) {
                    list.offer(i);
                    visited[i] = true;
                    countArrangement(list, visited, n);
                    list.poll();
                    visited[i] = false;
                }
            }
        }
    }

    public int numSquarefulPerms(int[] A) {
        List<Integer> list = new ArrayList<>(A.length);
        boolean[] visited = new boolean[A.length];
        numSquarefulPerms(list, A, visited);
        return result;
    }

    public void numSquarefulPerms(List<Integer> list, int[] array, boolean[] visited) {
        if (list.size() == array.length) {
            result++;
            return;
        }
        for (int j = 0; j < array.length; j++) {
            if (list.size() == 0) {
                list.add(array[j]);
                visited[j] = true;
                numSquarefulPerms(list, array, visited);
                visited[j] = false;
            } else {
                if (!visited[j]) {
                    int sum = array[j];
                    sum = sum + list.get(list.size() - 1);
                    if (isCompSqrt(sum)) {
                        list.add(array[j]);
                        visited[j] = true;
                        numSquarefulPerms(list, array, visited);
                        visited[j] = false;
                    }
                }
            }
        }
    }

    private boolean isCompSqrt(int p) {
        double fsqrt = Math.sqrt(p);
        int q = (int) fsqrt;
        return p == q * q;
    }

    public int maxLength(List<String> arr) {
        result = 0;
        Collections.sort(arr, Comparator.comparing(String::length).reversed());
        List<String> list = new ArrayList<>();
        Set<Character> visited = new HashSet<>(32);
        maxLength(arr, 0, visited, 0, 0);
        return result;

    }

    public void maxLength(List<String> arr, int size, Set<Character> visited, int length, int index) {
        if (length > result) {
            result = length;
        }
        if (size == arr.size()) {
            return;
        }
        for (int i = index; i < arr.size(); i++) {
            String str = arr.get(i);
            boolean flag = true;
            for (int j = 0; j < str.length(); j++) {
                char ch = str.charAt(j);
                if (visited.contains(ch)) {
                    flag = false;
                    for (int k = 0; k <= j - 1; k++) {
                        char ch2 = str.charAt(k);
                        visited.remove(ch2);
                    }
                    break;
                } else {
                    visited.add(ch);
                }
            }
            if (flag) {
                for (int j = 0; j < str.length(); j++) {
                    char ch = str.charAt(j);
                    visited.add(ch);
                }
                maxLength(arr, size + 1, visited, length + str.length(), i + 1);
                for (int j = 0; j < str.length(); j++) {
                    char ch = str.charAt(j);
                    visited.remove(ch);
                }
            }
        }
        return;
    }

}