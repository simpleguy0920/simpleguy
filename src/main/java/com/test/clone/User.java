package com.test.clone;

import java.io.Serializable;

public class User implements Serializable {


    private static final long serialVersionUID = -71655253864538245L;
    private UserName userName;

    private int age;
    private transient String gender;

    public UserName getUserName() {
        return userName;
    }

    public void setUserName(UserName userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName=" + userName +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
