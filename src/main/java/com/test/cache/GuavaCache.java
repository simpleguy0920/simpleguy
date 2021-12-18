package com.test.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class GuavaCache {
    public static void main(String[] args) throws InterruptedException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS).build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) {
                System.out.println("first " + key);
                return "key" + key;
            }
        });
        Cache<String, String> cache1 = CacheBuilder.newBuilder().build();
    }
}
