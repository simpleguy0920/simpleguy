package com.test.java;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class TestRedis {
    public static void main(String[] args) throws InterruptedException {
        Set<HostAndPort> set = new HashSet<>();
        HostAndPort hostAndPort = new HostAndPort("192.168.0.93", 6379);
        set.add(hostAndPort);
        HostAndPort hostAndPort1 = new HostAndPort("192.168.0.119", 6379);
        set.add(hostAndPort1);
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(100);
        String password = "Sn@20210802";
        JedisCluster jedisCluster = new JedisCluster(set, 5000, 10, 10, password, config);
        jedisCluster.set("aaa", "bbb");
        System.out.println(jedisCluster.get("aaa"));
    }
}
