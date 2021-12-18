package com.java8.intf;

public class Tom implements Boy, Student {

    public static void main(String[] args) {
        Tom tom = new Tom();
        tom.talk();

        tom.run();

    }

    @Override
    public void talk() {
        System.out.println("Tom talk");
    }

    @Override
    public void run() {
        Boy.super.run();
        Student.super.run();
    }


}
