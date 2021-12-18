package com.test;

import java.nio.charset.StandardCharsets;

public class Concurrent {
    public static void main(String[] args) {
        System.out.println('h' - 'A');
        System.out.println(1 / 2);
        String str = "\\xE5\\x8F\\xB0\\xE4\\xB8\\xAA";
        String stringss = hexStringToString(str.replaceAll("\\\\x", ""));
        System.out.println(stringss);

    }

    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, StandardCharsets.UTF_8);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

}
