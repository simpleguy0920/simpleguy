package com.test.clone;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.*;

public class TestSerialize {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        UserName userName = new UserName();
        userName.setFirstName("first");
        userName.setLastname("last");
        User user = new User();
        user.setUserName(userName);
        user.setGender("male");
        user.setAge(123);
        System.out.println(user);

        Output output = new Output(new FileOutputStream("d:\\kryo.txt"));
        Kryo kryo = new Kryo();
        kryo.writeObject(output, user);
        output.close();
        Input input = new Input(new FileInputStream("d:\\kryo.txt"));
        User user1 = kryo.readObject(input, User.class);
        input.close();
        System.out.println(user1);


        HessianOutput hessianOutput = new HessianOutput(new FileOutputStream("d:\\hessain.txt"));
        hessianOutput.writeObject(user);
        hessianOutput.close();
        HessianInput hessianInput = new HessianInput(new FileInputStream("d:\\hessain.txt"));
        Object user2 = hessianInput.readObject();
        hessianInput.close();
        System.out.println(user2);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("d:\\java.txt"));
        objectOutputStream.writeObject(user);
        objectOutputStream.close();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("d:\\java.txt"));
        Object user3 = objectInputStream.readObject();
        objectInputStream.close();
        System.out.println(user3);

    }
}

