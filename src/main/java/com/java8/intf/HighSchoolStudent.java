package com.java8.intf;

public abstract class HighSchoolStudent implements Student {

    public static void jump() {
        System.out.println("HighSchoolStudent jump");
    }

    public abstract void talk();

    public void run() {
        System.out.println("HighSchoolStudent run");
    }

}
