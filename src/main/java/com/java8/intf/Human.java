package com.java8.intf;


public interface Human {

//    default String toString(){
//        return "human";
//    }

    static void jump() {
        System.out.println("Human jump");
    }

    static void main(String[] args) {
        Human human1 = new Human() {
        };
        human1.run();
        Human.jump();

        System.out.println(human1.getClass().getProtectionDomain().getCodeSource().getLocation().getFile());

    }

    default void run() {
        System.out.println("Human run");
    }
}
