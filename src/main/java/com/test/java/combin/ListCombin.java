package com.test.java.combin;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class ListCombin {
    public static void main(String[] args) {
        int size = 3;
        List list = new ArrayList();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        List result = new ArrayList();
        combineAll(list, result, 3);
        System.out.println("---");
        combine2(list, result, 4);

    }


    public static void combine(List src, List result, int length) {
        if (result.size() == length) {
            System.out.println(ArrayUtils.toString(result));
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

    public static void combineAll(List src, List result, int length) {
        if (result.size() == length) {
            System.out.println(ArrayUtils.toString(result));
        } else {
            for (int i = 0; i < src.size(); i++) {
                if (!result.contains(src.get(i))) {
                    List newList = new ArrayList();
                    newList.add(src.get(i));
                    newList.addAll(result);
                    combineAll(src, newList, length);
                }
            }
        }
    }

    public static void combine2(List src, List result, int length) {
        if (result.size() == length) {
            System.out.println(ArrayUtils.toString(result));
        } else {
            for (int i = 0; i < src.size(); i++) {
                List newList = new ArrayList(result);
                newList.add(src.get(i));
                combine2(src, newList, length);
            }
        }
    }
}
