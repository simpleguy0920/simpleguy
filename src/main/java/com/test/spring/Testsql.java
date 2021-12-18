package com.test.spring;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Testsql {
    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://10.47.211.22:3306/shengyudev");
        dataSource.setUsername("fabu");
        dataSource.setPassword("123456");
        dataSource.setMaxActive(100);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String result = jdbcTemplate.queryForObject("select  now()", String.class);
        ExecutorService executors = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            final int k = i;
            executors.execute(new Runnable() {
                @Override
                public void run() {
                    insert(jdbcTemplate, k);
                }
            });
        }
    }

    public synchronized static void insert(JdbcTemplate jdbcTemplate, int a) {
        for (int i = 0; i < 10000000; i++) {
            jdbcTemplate.update("insert into test(name) values(?)", i + "|" + a);
            if (i % 10000 == 0) {
                System.out.println(LocalTime.now().toString() + "=" + a);
            }
        }
    }

}
