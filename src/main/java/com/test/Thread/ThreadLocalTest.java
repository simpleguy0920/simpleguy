package com.test.Thread;

public class ThreadLocalTest {
    //创建ThreadLocal 交量
    static ThreadLocal<String> localVariable = new ThreadLocal<>();

    //(l )print 函数
    static void print(String str) {
//1 1 打印当前线程本地内存中local Variable 变莹的位
        System.out.println(str + ":" + localVariable.get());
//1 . 2 清除当前线程本地内存中的localVariable 交量
        localVariable.remove();
    }

    public static void main(String[] args) {
        Thread threadOne = new Thread(() -> {
            localVariable.set(" threadOne local variable");
            print(" threadOne");
            System.out.println(" threadOne remove after" + " : " + localVariable.get());
        });
        Thread threadTwo = new Thread(() -> {
            localVariable.set(" threadTwo local variable");
            print(" threadTw。");
//4 . 3 打印本地交量值
            System.out.println(" threadTwo remove afte r " + " : " + localVariable.get());
        });
        threadOne.start();
        threadTwo.start();
    }
}