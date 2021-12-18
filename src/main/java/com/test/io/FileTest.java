package com.test.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class FileTest {
    public static void main(String[] args) throws IOException {
        Path path1 = Paths.get("d:", "opt", "logs", "windq", "cnhq-simpleguyn", "shengyu-A", "windq_trace.log");
        System.out.println(path1.getRoot());
        path1.forEach(path -> System.out.println(path.toString()));
        BasicFileAttributes basicFileAttributes = Files.readAttributes(path1, BasicFileAttributes.class);
        System.out.println(basicFileAttributes.isRegularFile());
    }
}
