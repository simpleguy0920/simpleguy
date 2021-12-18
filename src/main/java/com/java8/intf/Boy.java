package com.java8.intf;


public interface Boy {

    static void jump() {
        System.out.println("Boy jump");
    }

    void talk();

    default void run() {
        System.out.println("Boy run");
    }

}
