package com.test.redis;

import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

public class TestSingle {
    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis("10.47.212.207");
        jedis.connect();
        String aa = jedis.set("aa11", "aa", "NX", "PX", 150);
        System.out.println(aa);
        TimeUnit.MILLISECONDS.sleep(149);
        aa = jedis.set("aa11", "aa", "NX", "PX", 150);
        System.out.println(aa);
        jedis.close();
    }
}
