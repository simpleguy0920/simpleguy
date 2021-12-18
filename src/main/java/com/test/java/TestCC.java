package com.test.java;

import org.apache.commons.collections.Bag;
import org.apache.commons.collections.bag.HashBag;

import java.util.Set;

public class TestCC {

    public static void main(String[] args) {
//        TestCC testCC = new TestCC();
//        testCC.process(15);
        Bag bag = new HashBag();
        for (int i = 0; i < 100000; i++) {
            int a = i % 32;
            int b = i % 16;
            String f = a + "*" + b;
            bag.add(f);
        }
        Set set = bag.uniqueSet();

        for (Object o : set) {
            int count = bag.getCount(o);
            System.out.println(o + " " + count);
        }

    }

    public void process(int a) {
        if (a <= 2) {
            return;
        }
        int b = a / 2;
        for (int i = 1; i < b + 1; i++) {
            int avg = a / i;
            for (int j = 1; j <= avg + 1; j++) {
                int sum = calsumByCount(j, i);
                if (sum == a) {
                    print(j, i);
                }
            }
        }
    }

    public void print(int start, int length) {
        for (int i = start; i <= start + length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public int calsum(int a, int b) {
        int sum = (a + b) * (b - a + 1) / 2;
        return sum;
    }

    public int calsumByCount(int start, int length) {
        return calsum(start, start + length);
    }
}
