package com.test.jdk8;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TestSpeed {
    public static void main(String[] args) {
        List<String> list = Stream.generate(() -> String.valueOf(Math.random())).limit(100000).collect(Collectors.toList());
        long t1 = System.currentTimeMillis();
        process(list);
        long t2 = System.currentTimeMillis();
        process(list);

        long t3 = System.currentTimeMillis();
        System.out.println("a=" + (t3 - t2) + " b=" + (t2 - t1));
    }

    public static void process(List<String> list) {
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {

            }
        });
    }
}
