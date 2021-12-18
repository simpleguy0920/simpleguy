package com.java8.intf;

public interface Student {

    static void jump() {
        System.out.println("Student jump");
    }

    void talk();

    default void run() {
        System.out.println("Student run");
    }
}
