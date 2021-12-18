package com.test.java.solution;

public class LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        int count5 = 0;
        int count10 = 0;
        int length = bills.length;
        for (int i = 0; i < length; i++) {
            int value = bills[i];
            if (value == 5) {
                count5++;
            } else if (value == 10) {
                if (count5 > 0) {
                    count5--;
                    count10++;
                } else {
                    return false;
                }
            } else {
                if (count10 > 0 && count5 > 0) {
                    count10--;
                    count5--;
                } else if (count5 > 2) {
                    count5 = count5 - 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
