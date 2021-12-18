package com.test.spring;


import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springconfig.xml");
        ServiceFactory serviceFactory = applicationContext.getBean(ServiceFactory.class);
        DoWork doWork = serviceFactory.getServiceByChannelType(DoWork.class, "run");
        doWork.work();
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        System.out.println(ArrayUtils.toString(beanDefinitionNames));
    }
}
