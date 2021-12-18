package com.test.clone;


import org.apache.commons.beanutils.BeanUtils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.lang.reflect.InvocationTargetException;


public class TestClone {

    public static Object deepCopy(Object fromBean) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLEncoder out = new XMLEncoder(bos);
        out.writeObject(fromBean);
        out.close();
        ByteArrayInputStream bis = new
                ByteArrayInputStream(bos.toByteArray());
        XMLDecoder in = new XMLDecoder(bis);
        Object toBean = in.readObject();
        in.close();
        return toBean;
    }


    public static void main(String[] args) throws CloneNotSupportedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, FileNotFoundException {
        UserName userName1 = new UserName();
        userName1.setFirstName("aaa");
        userName1.setLastname("bbb");
        User user1 = new User();
        user1.setUserName(userName1);
        user1.setGender("male");
        User user2 = (User) BeanUtils.cloneBean(user1);
        User user3 = (User) deepCopy(user1);
        user1.setGender("female");
        userName1.setFirstName("ccc");
        XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("d://Test.xml")));
        e.writeObject(user1);
        e.close();
        System.out.println(user1.getUserName().getFirstName());
        System.out.println(user2.getUserName().getFirstName());
        System.out.println(user3.getUserName().getFirstName());
        System.out.println(user1.getGender());
        System.out.println(user2.getGender());
        System.out.println(user3.getGender());


    }
}
