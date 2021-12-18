package com.java8.intf;

public class Jack extends HighSchoolStudent implements Boy {

    public static void main(String[] args) {
        Jack jack = new Jack();
        jack.talk();
        jack.run();
        jump();

        Boy.jump();
    }

    @Override
    public void talk() {
        System.out.println("Jack talk");
    }


}
