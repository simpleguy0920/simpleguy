package com.test;

import java.lang.reflect.Field;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TestNumber {
    public static void main(String[] args) {
        try {
            Class<?> cls = Integer.class.getDeclaredClasses()[0];
            Field f = cls.getDeclaredField("cache");
            f.setAccessible(true);
            Integer[] cache = ((Integer[]) f.get(cls));
            for (int i = 0; i < cache.length; i++) {
                cache[i] = -996;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //silence
        }

        Integer a = 1;
        System.out.println(a);
        Integer b = 2;
        System.out.println(a.intValue() == b.intValue());
        System.out.println(a.equals(b));

    }
}
