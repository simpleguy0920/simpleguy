package com.test.Thread;

import java.math.BigInteger;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TestLoad {

    public static int a = 7;

    static {
        a = 9;
    }

    public TestLoad() {
        a = 8;
    }

    public static void main(String[] args) {
        TestLoad testLoad = new TestLoad();
        System.out.println(a);
        BigInteger bigInteger = BigInteger.valueOf(100);
        System.out.println(bigInteger.doubleValue());
    }
}
