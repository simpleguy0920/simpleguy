package com.test.java;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestCountFileString {
    public static void main(String[] args) throws IOException {
        File f = new File("d:/log1.xml");
        List<String> stringList = FileUtils.readLines(f);
        Multiset<String> multiset = HashMultiset.create();
        for (String str : stringList) {
            if (str.contains("RedisAspect") && !str.contains("slow")) {
                String[] array = StringUtils.split(str, " ");
                for (String s : array) {
                    if (s.contains("key=")) {
                        String value = StringUtils.removeStart(s, "key=");
                        multiset.add(value);
                    }
                }
            }
        }
        for (Multiset.Entry<String> entry : multiset.entrySet()) {
            System.out.println(entry.getElement() + " " + entry.getCount());
        }
    }
}
