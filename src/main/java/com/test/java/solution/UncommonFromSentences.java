package com.test.java.solution;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UncommonFromSentences {
    public static void main(String[] args) {
        UncommonFromSentences uncommonFromSentences = new UncommonFromSentences();
        System.out.println(uncommonFromSentences.uncommonFromSentences("this apple is sweet", "this apple is sour"));
    }

    public String[] uncommonFromSentences(String A, String B) {
        String[] arrayA = A.split(" ");
        String[] arrayB = B.split(" ");
        Map<String, Integer> mapA = new LinkedHashMap<>();
        Map<String, Integer> mapB = new LinkedHashMap<>();
        for (String str : arrayA) {
            if (mapA.containsKey(str)) {
                int count = mapA.get(str);
                count++;
                mapA.put(str, count);
            } else {
                mapA.put(str, 1);
            }
        }
        for (String str : arrayB) {
            if (mapB.containsKey(str)) {
                int count = mapB.get(str);
                count++;
                mapB.put(str, count);
            } else {
                mapB.put(str, 1);
            }
        }
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : mapA.entrySet()) {
            if (entry.getValue() == 1 && !mapB.containsKey(entry.getKey())) {
                list.add(entry.getKey());
            }
        }
        for (Map.Entry<String, Integer> entry : mapB.entrySet()) {
            if (entry.getValue() == 1 && !mapA.containsKey(entry.getKey())) {
                list.add(entry.getKey());
            }
        }
        String[] result = new String[list.size()];
        return list.toArray(result);
    }
}
