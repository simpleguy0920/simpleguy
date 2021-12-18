package com;


import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class TestRead {
    public static void main(String[] args) throws IOException {
        List<WeakReference> list = new ArrayList<>();
        for (long i = 0; i < 1000000000L; i++) {
            WeakReference weakReference = new WeakReference<String>(String.valueOf(i));
        }


    }
}
