package com.test.jdk8;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TestBit {
    public static void main(String[] args) {
        int a = 1;
        System.out.println(a << 3);
        System.out.println(a >> 3);
        TestBit testBit = new TestBit();
        System.out.println(testBit.f(2, 1));
    }

    public String f(int a, Integer b) {
        return "a";
    }

    public String f(Integer a, int b) {
        return "b";
    }

    public String f(Integer a, Integer b) {
        return "c";
    }

    public String f(int a, int b) {
        return "d";
    }


}
