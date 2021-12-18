package com.test.java;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TestPhone {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (long i = 20011110000l; i < 20011110000l + 4096; i++) {
            if (i != 20011110000l) {
                stringBuilder.append(",");
            }
            stringBuilder.append(i);
        }
        System.out.println(stringBuilder);
    }
}

