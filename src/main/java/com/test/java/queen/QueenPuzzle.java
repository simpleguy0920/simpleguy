package com.test.java.queen;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class QueenPuzzle {
    public static void main(String[] args) {
        int size = 8;
        List<QueenBean> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            QueenBean queenBean1 = new QueenBean(i, 0);
            list.add(queenBean1);

            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    QueenBean queenBean = new QueenBean(x, y);
                    if (!queenBean.isConflictList(list)) {
                        list.add(queenBean);
                    }

                }
            }
            System.out.println(StringUtils.join(list));
            if (list.size() == size) {
                print(list);
            }
            list.clear();
        }


    }

    public static void print(List<QueenBean> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                QueenBean queenBean = new QueenBean(i, j);
                if (list.contains(queenBean)) {
                    System.out.print("■");
                } else {
                    System.out.print("□");
                }
            }
            System.out.println();
        }
    }
}