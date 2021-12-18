package com.test.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.IOException;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class TestRateLimiter {
    Jedis jedis;

    private final AtomicLong longValue = new AtomicLong(1l);

    public TestRateLimiter(Jedis jedis) {
        this.jedis = jedis;
    }

    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis("10.47.212.207");
        TestRateLimiter limiter = new TestRateLimiter(jedis);
        for (int i = 0; i < 30; i++) {
            TimeUnit.MILLISECONDS.sleep(50);
            System.out.println(i + "," + new Date().toLocaleString() + limiter.isActionAllowed("laoqian", "reply", 1, 5));
        }
    }

    public boolean isActionAllowed(String userId, String actionKey, int period, int maxCount) {
        String key = String.format("hist:%s:%s", userId, actionKey);
        long nowTs = System.currentTimeMillis();
        Pipeline pipe = jedis.pipelined();
        pipe.multi();
        pipe.zadd(key, nowTs, String.valueOf(longValue.incrementAndGet()));
        pipe.zremrangeByScore(key, 0, nowTs - period * 1000);
        Response<Set<String>> set = pipe.zrange(key, 0, nowTs - period * 1000);
        Response<Long> count = pipe.zcard(key);
        pipe.expire(key, period + 1);
        pipe.exec();
        try {
            pipe.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long countValue = count.get();
        System.out.println(countValue + "|" + nowTs + "|" + set.get().toString());
        return countValue <= maxCount;
    }
}
