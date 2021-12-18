package com.test.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ServiceFactory {

    @Autowired
    private ApplicationContext applicationContext;

    public <T> T getServiceByChannelType(Class<T> clazz, String a) {
        Map<String, T> map = applicationContext.getBeansOfType(clazz);
        for (Map.Entry<String, T> entry : map.entrySet()) {
            if (entry.getKey().startsWith(a)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public <T> Map<String, T> getAllService(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);

    }
}
