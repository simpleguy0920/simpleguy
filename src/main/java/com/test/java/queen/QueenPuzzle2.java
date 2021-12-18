package com.test.java.queen;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class QueenPuzzle2 {
    public static void main(String[] args) {
        int size = 5;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        List<Integer> result = new ArrayList<>();
        combine(list, result, size);
    }

    public static void combine(List<Integer> src, List<Integer> result, int length) {
        if (result.size() == length) {
            if (check(result)) {
                System.out.println(ArrayUtils.toString(result));
            }
        } else {
            for (int i = 0; i < src.size(); i++) {
                if (!result.contains(src.get(i))) {
                    List newList = new ArrayList(result);
                    newList.add(src.get(i));
                    combine(src, newList, length);
                }
            }
        }
    }

    public static boolean check(List<Integer> result) {
        List<QueenBean> list = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            QueenBean queenBean = new QueenBean(i, result.get(i));
            if (queenBean.isConflictList(list)) {
                return false;
            } else {
                list.add(queenBean);
            }
        }
        return true;

    }
}
