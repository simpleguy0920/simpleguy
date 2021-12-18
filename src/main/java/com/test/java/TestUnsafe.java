package com.test.java;

import sun.misc.Unsafe;

public class TestUnsafe {
    public static void main(String[] args) {
        System.out.println(Unsafe.getUnsafe().getAddress(1l));
    }
}
