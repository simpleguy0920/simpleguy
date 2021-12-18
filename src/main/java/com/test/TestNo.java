package com.test;

import org.apache.commons.lang.math.RandomUtils;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TestNo {
    public static void main(String[] args) {
        float sum = 0;
        long total = 10000000;
        for (long i = 0; i < total; i++) {
            if (RandomUtils.nextBoolean()) {
                sum++;
            }
        }
        System.out.println(sum * 100 / total);

    }
}
