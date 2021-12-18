package com.test.jdk8;

import com.google.common.base.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Collect {
    public static <T> List filter(List<T> list, Predicate<T> predicate) {
        if (list == null || predicate == null) {
            return list;
        }
        List<T> newList = new ArrayList<>();
        for (T t : list) {
            if (predicate.apply(t)) {
                newList.add(t);
            }
        }
        return newList;
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            TimeUnit.SECONDS.sleep(3);
            out();
        }
    }

    private static void out() {
        System.out.println("aa");
    }

}
