package com.test.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.Driver;
import java.util.ServiceLoader;

public class TestLog {

    static ServiceLoader<Driver> loadedDrivers;
    private static final Logger logger = LoggerFactory.getLogger(TestLog.class);

    public static void main(String[] args) {
        AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
            loadedDrivers = ServiceLoader.load(Driver.class);
            return null;
        });
        loadedDrivers.forEach(System.out::println);

    }
}
