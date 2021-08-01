package org.bigmoon.learning.kryo;

import com.esotericsoftware.kryo.kryo5.Kryo;
import com.esotericsoftware.kryo.kryo5.io.Input;
import com.esotericsoftware.kryo.kryo5.io.Output;
import org.bigmoon.learning.kryo.entity.SomeClass;
import org.bigmoon.learning.kryo.safeThread.ThreadLocalExample;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class HelloKryo {

    public static void main(String[] args) throws Exception {
        sampleWithoutRegister();
//        sampleDemo();
//        inputVariableObject();
    }

    public static void sampleWithoutRegister() throws Exception {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);
        SomeClass someClass = new SomeClass("Hello kryo");
        try {
//            Output output=new Output(new BufferedOutputStream(new FileOutputStream("someClass.txt")));
            Output output = new Output();
            output.setBuffer(new byte[4096], 4096);
            output.setOutputStream(new BufferedOutputStream(new FileOutputStream("someClass.txt")));
            kryo.writeClassAndObject(output, someClass);
            output.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }
        inputVariableObject();
    }

    public static void inputVariableObject() throws Exception {

        try(Input input=new Input(new FileInputStream("threadLocal.txt"))){

            SomeClass result= (SomeClass)ThreadLocalExample.kryos.get().readClassAndObject(input);
            System.out.println("result:"+result.getValue());

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public static void sampleDemo() throws IOException {
        Kryo kryo = new Kryo();
        kryo.register(SomeClass.class);
        SomeClass someClass = new SomeClass("Hello kryo");
        try {
//            Output output=new Output(new BufferedOutputStream(new FileOutputStream("someClass.txt")));
            Output output = new Output();
            output.setBuffer(new byte[1024], 4082);
            output.setOutputStream(new FileOutputStream("someClass.txt"));
            kryo.writeObject(output, someClass);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Input input = new Input(new FileInputStream("someClass.txt"));
        SomeClass result = kryo.readObject(input, SomeClass.class);
        System.out.println("result:" + result);
    }
}
