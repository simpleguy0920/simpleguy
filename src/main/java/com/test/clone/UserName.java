package com.test.clone;

import java.io.Serializable;

public class UserName implements Serializable {

    private static final long serialVersionUID = 4478523557183286856L;
    private String firstName;
    private transient String lastname;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "UserName{" +
                "firstName='" + firstName + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
