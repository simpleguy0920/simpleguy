package com.test.Thread;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FutrueTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    System.out.println(Thread.currentThread() + "1 start" + LocalDateTime.now());
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("1 end" + LocalDateTime.now());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return "3";
            }
        }).thenAccept(new Consumer<String>() {
            @Override
            public void accept(String s) {
                try {
                    System.out.println(Thread.currentThread() + "2 start" + LocalDateTime.now());

                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("2 end" + LocalDateTime.now());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(Thread.currentThread() + "main 1" + LocalDateTime.now());
        TimeUnit.SECONDS.sleep(2);
        System.out.println("main 2" + LocalDateTime.now());
        TimeUnit.SECONDS.sleep(2);
        System.out.println("main 3" + LocalDateTime.now());
        TimeUnit.SECONDS.sleep(2);
        System.out.println("main 4" + LocalDateTime.now());

    }
}