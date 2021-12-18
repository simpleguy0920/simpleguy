package com.test.java;

public class SwitchTest {
    final String c = "cc";

    public int doSwitch(String str) {
        switch (str) {
            case "dcc":
                return 1;
            case c:
                return 2;
            default:
                return 0;
        }
    }

}
