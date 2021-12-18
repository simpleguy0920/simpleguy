package com.test.java;

import java.time.Clock;
import java.time.Instant;

public class TestDate {
    public static void main(String[] args) {
        Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        Instant instant = Instant.now();
    }
}
