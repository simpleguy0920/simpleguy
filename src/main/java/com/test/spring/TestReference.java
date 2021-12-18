package com.test.spring;

import java.lang.ref.WeakReference;

public class TestReference {
    public static void main(String[] args) {
        String a = "b";
        a = null;
        WeakReference<String> stringWeakReference = new WeakReference<>(a);
        System.out.println(stringWeakReference.get());
    }
}
