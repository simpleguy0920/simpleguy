package com.test.java.sort;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TestRedisCluster {
    public static void main(String[] args) {
        Set<HostAndPort> set = new HashSet<>();
        HostAndPort hostAndPort = new HostAndPort("10.47.210.117", 7001);
        set.add(hostAndPort);
        HostAndPort hostAndPort1 = new HostAndPort("10.47.210.117", 7002);
        set.add(hostAndPort1);
        HostAndPort hostAndPort2 = new HostAndPort("10.47.210.117", 7003);
        set.add(hostAndPort2);
        HostAndPort hostAndPort3 = new HostAndPort("10.47.210.117", 7004);
        set.add(hostAndPort3);
        HostAndPort hostAndPort4 = new HostAndPort("10.47.210.117", 7005);
        set.add(hostAndPort4);
        HostAndPort hostAndPort5 = new HostAndPort("10.47.210.117", 7006);
        set.add(hostAndPort5);


        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(100);
        String password = "Sn@20210802";
        JedisCluster jedisCluster = new JedisCluster(set, 5000, 10, 10, password, config);
        jedisCluster.set("aaa", "bbb");
        System.out.println(jedisCluster.incr("dddd"));
    }
}
