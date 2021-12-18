package com.test.ratelimit;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalTime;

public class TestRateLimiter {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = RateLimiter.create(2);
        for (int i = 0; i < 30; i++) {
            rateLimiter.acquire(3);
            System.out.println(LocalTime.now().toString() + "|" + i);
        }
    }
}
