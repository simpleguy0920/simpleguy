package com.test.spring;

import org.springframework.stereotype.Component;

@Component
public class RunWork implements DoWork {
    @Override
    public void work() {
        System.out.println("run");
    }
}
