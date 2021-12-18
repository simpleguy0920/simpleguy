package com.test.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

public class TestCurator {
    @Test
    public void test1() {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("10.243.55.113:2181,10.243.55.114:2181,10.243.55.117:2181", new ExponentialBackoffRetry(3000, 3000));
        curatorFramework.start();
    }
}
