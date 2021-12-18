package com.test.java;

public enum MyEnum {

    Hehe("1", "a", "aaa"),
    Haha("2", "b", "bbb");

    private final String key;
    private final String value;
    private final String desc;

    MyEnum(String key, String value, String desc) {
        this.key = key;
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "MyEnum{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
