package com.test;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TheadTest {

    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong(0);
        PrintThead printThead1 = new PrintThead("t1", atomicLong, 0);
        PrintThead printThead2 = new PrintThead("t2", atomicLong, 1);
        PrintThead printThead3 = new PrintThead("t3", atomicLong, 2);
        printThead1.start();
        printThead2.start();
        printThead3.start();

    }

    public static class PrintThead extends Thread {
        public String name;

        public AtomicLong value;

        public int mod;

        public PrintThead(String name, AtomicLong value, int mod) {
            this.name = name;
            this.value = value;
            this.mod = mod;
        }

        @Override
        public void run() {
            int count = 0;
            while (true) {
                if (count == 100) {
                    break;
                }
                if (value.longValue() % 3 == mod) {
                    System.out.println(name + "-" + value);
                    value.incrementAndGet();
                    count++;
                }
            }
        }
    }
}
