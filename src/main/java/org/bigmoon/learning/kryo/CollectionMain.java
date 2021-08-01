package org.bigmoon.learning.kryo;

import com.esotericsoftware.kryo.kryo5.io.Input;
import com.esotericsoftware.kryo.kryo5.io.Output;
import org.bigmoon.learning.kryo.entity.Car;
import org.bigmoon.learning.kryo.entity.TestEnum;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.bigmoon.learning.kryo.safeThread.ThreadLocalExample.kryoPool;

public class CollectionMain {

    public static void main(String[] args) throws Exception {
//        enumTest();
        ListTest();

    }

    /**
     * list集合序列化
     *
     * @throws Exception
     */
    static void ListTest() throws Exception {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cars.add(new Car("mbw" + i, 10d, 100000d, null));
        }

        Output output = new Output(new FileOutputStream("cars.txt"));
        kryoPool.obtain().writeClassAndObject(output, cars);
        output.close();
        System.out.println("序列化完成");

        Input input = new Input(new FileInputStream("cars.txt"));
        List<Car> carsDeserialize = (List<Car>) kryoPool.obtain().readClassAndObject(input);
        Optional.ofNullable(carsDeserialize).orElseThrow(IllegalArgumentException::new).forEach(item -> System.out.println(item));
    }

    static void enumTest() throws Exception {


        Output output = new Output(new FileOutputStream("testEnum.txt"));
        kryoPool.obtain().writeClassAndObject(output, TestEnum.B);
        output.close();
        System.out.println("序列化完成");

        Input input = new Input(new FileInputStream("testEnum.txt"));
        TestEnum enumDeserialize = (TestEnum) kryoPool.obtain().readClassAndObject(input);
        System.out.println(enumDeserialize);
    }
}
