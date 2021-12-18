package com.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TestFutrue {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");


        System.out.println(future.getNow("hehe"));
        System.out.println(future.getNow("hehe"));
        System.out.println(future.getNow("hehe"));
        System.out.println(future.getNow("hehe"));
        System.out.println(future.getNow("hehe"));
        System.out.println(future.getNow("hehe"));
        System.out.println(future.getNow("hehe"));
        System.out.println(future.getNow("hehe"));
        System.out.println(future.getNow("hehe"));
        System.out.println(future.getNow("hehe"));
        System.out.println(future.getNow("hehe"));
        System.out.println(future.getNow("hehe"));
        System.out.println(future.getNow("hehe"));
        System.out.println(future.getNow("hehe"));
        System.out.println(future.getNow("hehe"));
        System.out.println(future.getNow("hehe"));
        System.out.println(future.getNow("hehe"));
        System.out.println(future.getNow("hehe"));
        System.out.println(future.getNow("hehe"));
        System.out.println(future.getNow("hehe"));

    }
}
