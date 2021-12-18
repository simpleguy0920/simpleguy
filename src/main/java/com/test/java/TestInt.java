package com.test.java;

public class TestInt {
    public static void main(String[] args) {
//        System.out.println(findTableNo("7105834347"));
        System.out.println(7105834347l % 16);
    }


    public static String getFromCustomerNo(String customerNo) {
        long customerNum = Long.parseLong(customerNo);
        int groupNum;
        if (customerNum >= 0L && customerNum < 9999999999L) {
            groupNum = 1;
        } else if (customerNum >= 9999999999L && customerNum < 99999999999L) {
            groupNum = 2;
        } else {
            groupNum = 3;
        }
        return "GROUP" + groupNum + "_CELL" + (customerNum % 32 + 1);
    }

    public static String findTableNo(String paramValue) {
        if (paramValue.length() < 2) {
            paramValue = "0" + paramValue;
        }
        int memberCode = Integer.parseInt(paramValue.substring(paramValue.length() - 2, paramValue.length() - 1));
        long dbNo = (long) memberCode + 1;
        return (dbNo < 10) ? "0" + dbNo : Long.toString(dbNo);
    }
}
