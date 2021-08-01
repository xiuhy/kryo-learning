package org.bigmoon.learning.kryo;

import org.bigmoon.learning.kryo.entity.SomeClass;
import org.bigmoon.learning.kryo.entity.SomeClassForJDK;

import java.io.*;

public class JDKSerializerMain {
    public static void main(String[] args)throws Exception {

        objectRead();
    }

    public static void objectSaveTxt()throws Exception{

        BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(new FileOutputStream("jdkSerialize.txt"));
        SomeClassForJDK someClass=new SomeClassForJDK("jdkSerializer");
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream("jdkSerialize.txt"));
        objectOutputStream.writeObject(someClass);
        objectOutputStream.close();
    }

    public static void objectRead()throws Exception{

        ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream("jdkSerialize.txt"));
        SomeClassForJDK someClassForJDK=(SomeClassForJDK)objectInputStream.readObject();
        System.out.println(someClassForJDK);

    }
}
