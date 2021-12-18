package com.test.java;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestList {
    public static void main(String[] args) {
        List<Integer> list = IntStream.range(1, 2).boxed().collect(Collectors.toList());
        List<List<Integer>> list1 = Lists.partition(list, 7);
        for (List<Integer> integers : list1) {
            System.out.println(ArrayUtils.toString(integers));
        }
    }
}
