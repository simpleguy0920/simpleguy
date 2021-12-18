package com.java8.optional;

import java.util.Optional;

public class TestOptional {
    public static void main(String[] args) {
        D d = new D();
//        String value1=d.getC().getB().getA().getName();


        String value2 = "defaultName1";
        if (d != null) {
            C c = d.getC();
            if (c != null) {
                B b = c.getB();
                if (b != null) {
                    A a = b.getA();
                    if (a != null) {
                        String name = a.getName();
                        if (name != null) {
                            value2 = name.toUpperCase();
                        }
                    }
                }
            }
        }
        System.out.println(value2);

        Optional<D> dOption = Optional.ofNullable(d);
        String value3 = dOption.map(D::getC).map(C::getB).map(B::getA).map(A::getName).map(String::toUpperCase).orElse("defaultName2");
        System.out.println(value3);

    }


}
