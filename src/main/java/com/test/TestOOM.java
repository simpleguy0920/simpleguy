package com.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TestOOM {
    private static String str = "test";

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        while (true) {
            String str2 = str + str;
            str = str2;
            list.add(str.intern());
        }
    }

    private static void heapoom() {
        List<TestOOM> list = new ArrayList<>();
        while (true) {
            list.add(new TestOOM());
        }
    }
}
