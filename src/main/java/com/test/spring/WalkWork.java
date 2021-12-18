package com.test.spring;

import org.springframework.stereotype.Component;

@Component
public class WalkWork implements DoWork {
    @Override
    public void work() {
        System.out.println("work");
    }
}
