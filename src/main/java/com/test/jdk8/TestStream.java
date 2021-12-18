package com.test.jdk8;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Stream;

public class TestStream {
    public static void main(String[] args) {
        Stream.generate(() -> String.valueOf(Math.random())).limit(10).forEach(System.out::println);
        List list = Lists.newArrayList();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        list.parallelStream().forEach(o -> System.out.println(Thread.currentThread().getName() + "-" + o));

    }
}