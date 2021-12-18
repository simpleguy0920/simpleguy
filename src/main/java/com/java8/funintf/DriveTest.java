package com.java8.funintf;

public class DriveTest {
    public static void main(String[] args) {
        Drive drive1 = new Drive() {
            @Override
            public Integer drive(String value) {
                return Integer.valueOf(value);
            }
        };
        Drive drive2 = value -> Integer.valueOf(value);

        Drive drive3 = Integer::valueOf;

        Drive drive4 = Integer::new;


    }
}
