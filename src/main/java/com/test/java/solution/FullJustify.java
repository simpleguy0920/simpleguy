package com.test.java.solution;

import java.util.ArrayList;
import java.util.List;

public class FullJustify {
    public static List<String> fullJustify(String[] words, int maxWidth) {
        if (words == null || words.length == 0) {
            return null;
        }
        List<String> list = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        List<String> line = new ArrayList<>();
        int lineLength = 0;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (lineLength + 1 + word.length() > maxWidth) {
                String result = convert(line, maxWidth);
                list.add(result);
                line = new ArrayList<>();
                line.add(word);
                lineLength = word.length();
            } else {
                line.add(word);
                lineLength = lineLength + 1 + word.length();
            }
            if (i == words.length - 1) {
                String result = convert(line, maxWidth);
                list.add(result);
            }
        }
        return list;
    }

    public static String convert(List<String> lines, int maxLength) {
        int totalLength = 0;
        for (String line : lines) {
            totalLength = totalLength + line.length();
        }
        StringBuffer stringBuffer = new StringBuffer();

        if (lines.size() == 1) {
            stringBuffer.append(lines.get(0));
            for (int j = 0; j < maxLength - lines.get(0).length(); j++) {
                stringBuffer.append(" ");
            }
            return stringBuffer.toString();
        }
        int sublength = maxLength - totalLength;
        int between = sublength / (lines.size() - 1);
        int add = sublength % (lines.size() - 1);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (i == 0) {
                stringBuffer.append(line);
            } else {
                char[] space = null;
                int count;
                if (i < add - 1) {
                    count = between + 1;
                } else {
                    count = between;
                }
                for (int k = 0; k < count; k++) {
                    stringBuffer.append(" ");
                }

                stringBuffer.append(line);

            }

        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        List<String> list = fullJustify(words, 16);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
