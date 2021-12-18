package com.test.java.loganalyze;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RsfLog {
    public static void main(String[] args) throws IOException {
        File f = new File("d:/333.xml");
        List<String> list = FileUtils.readLines(f);
        for (String string : list) {
            int startindex = string.indexOf("GCSCSERVICE-displayShoppingCart&|");
            int endIndex = string.indexOf("<");
            if (endIndex < 0 || startindex < 0) {
                System.out.println();
                continue;
            }
            System.out.println(string.substring(0, 23) + " " + string.substring(startindex + 35, endIndex - 2));
        }
    }
}
