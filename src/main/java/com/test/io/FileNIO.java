package com.test.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileNIO {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("d:", "1.txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(path.toFile(), "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[]{1, 2, 3});

        fileChannel.write(byteBuffer);

        fileChannel.close();
    }
}
