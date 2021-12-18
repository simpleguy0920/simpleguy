package com.test.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class TestSubscribe {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("10.47.212.207");
        jedis.connect();
        jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println(channel + "|" + message);
            }

            @Override
            public void onPMessage(String pattern, String channel, String message) {
                System.out.println(pattern + "|" + channel + "|" + message);
            }
        }, "ppp");

    }
}
