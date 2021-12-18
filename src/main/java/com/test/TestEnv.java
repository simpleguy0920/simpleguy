package com.test;


import org.openjdk.jol.vm.VM;

import java.io.IOException;

public class TestEnv {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(VM.current().details());
        Object obj = "aa";
        System.out.println(obj + " 十六进制哈希： " + Integer.toHexString(obj.hashCode()));
        System.out.println(org.openjdk.jol.info.ClassLayout.parseInstance(obj).toPrintable());
    }
}
