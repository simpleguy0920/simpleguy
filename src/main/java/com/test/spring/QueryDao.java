package com.test.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class QueryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public QueryDao() {
        System.out.println("Construct QueryDao" + jdbcTemplate);
    }


    @PostConstruct
    public void postConstruct() {
        System.out.println("postConstruct" + jdbcTemplate);
    }
}
