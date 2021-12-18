package com.test.io;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestIp {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(inetAddress.getHostName());
        System.out.println(inetAddress.getHostAddress());
        System.out.println(inetAddress.getCanonicalHostName());


    }
}
