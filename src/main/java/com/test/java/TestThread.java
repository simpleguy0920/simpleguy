package com.test.java;


import java.util.logging.Logger;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TestThread {
    volatile static int a = 1;
    volatile static int b = 1;
    private static final Logger log = Logger.getGlobal();

    public static void main(String[] args) {
        TestThread interesting = new TestThread();
        new Thread(() -> interesting.add()).start();
        new Thread(() -> interesting.compare()).start();
    }

    public synchronized void add() {
        log.info("add start");
        for (int i = 0; i < 1000000; i++) {
            a++;
            b++;
        }
        log.info("add done");
    }

    public synchronized void compare() {
        log.info("compare start");
        for (int i = 0; i < 1000000; i++) {
//a始终等于b吗？
            if (a < b) {
                log.info("a=" + a + " b=" + b + " " + (a > b));
//最后的a>b应该始终是false吗？
            }
        }
        log.info("compare done");
    }

}
