package com.test.java;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestFile1 {
    public static void main(String[] args) throws IOException {
        File f1 = new File("d://4.txt");
        List<String> list1 = FileUtils.readLines(f1);
        File f2 = new File("d://5.txt");
        List<String> list2 = FileUtils.readLines(f2);
        for (String str2 : list2) {
            if (str2.contains("comment")) {
                String name = str2.substring(str2.indexOf("`") + 1, str2.lastIndexOf("`"));
                for (String str1 : list1) {
                    if (str1.contains(name)) {
                        String value = str1.substring(str1.indexOf("'") + 1, str1.lastIndexOf("'"));
                        System.out.println(StringUtils.replace(str2, "配送方式", value));
                    }
                }
            }
        }

    }
}
