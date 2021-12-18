package com;

import java.util.concurrent.TimeUnit;

public class TestVisiable {
    static volatile Boolean isRunning = true;

    public static void main(String[] args) throws InterruptedException {
        Thread runningT = getRunningThread();

        runningT.start();

        TimeUnit.SECONDS.sleep(1);

        isRunning = false;//注意： main Thread 执行到此,预期runningThread 应该结束
        System.out.println(isRunning);
    }

    public static Thread getRunningThread() {
        return new Thread(new Runnable() {

            @Override
            public void run() {
                while (isRunning) {

                }
            }
        }, "RunningThread");
    }
}
