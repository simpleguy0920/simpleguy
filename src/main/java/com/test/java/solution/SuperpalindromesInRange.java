package com.test.java.solution;

import java.util.Date;

public class SuperpalindromesInRange {
    public static void main(String[] args) {
        Date a = new Date();
        SuperpalindromesInRange superpalindromesInRange = new SuperpalindromesInRange();
        int count = superpalindromesInRange.superpalindromesInRange("38455498359", "999999999999999999");
        System.out.println(count);
        System.out.println(a + " " + new Date());
    }

    public int superpalindromesInRange(String L, String R) {
        int count = 0;
        int start = Double.valueOf(Math.ceil(Math.sqrt(Long.valueOf(L)))).intValue();
        int end = Double.valueOf(Math.floor(Math.sqrt(Long.valueOf(R)))).intValue();
        for (int i = start; i <= end; i++) {
            if (superCheck(i)) {
                System.out.println(i);
                count++;
            }
        }
        return count;
    }

    public boolean check(long a) {
        if (a < 10) {
            return true;
        }
        char[] array = String.valueOf(a).toCharArray();
        if (a > 10 && (array[0] != '1' && array[0] != '2')) {
            return false;
        }
        int length = array.length;

        for (int i = 0; i < length && i < (length - 1 - i); i++) {
            if (array[i] != array[length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    public boolean superCheck(long a) {
        if (check(a)) {
            return check(a * a);
        } else {
            return false;
        }
    }
}
