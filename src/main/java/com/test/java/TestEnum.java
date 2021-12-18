package com.test.java;

public class TestEnum {
    public static void main(String[] args) {
        MyEnum myEnum = Enum.valueOf(MyEnum.class, "He");
        System.out.println(myEnum);
    }
}
