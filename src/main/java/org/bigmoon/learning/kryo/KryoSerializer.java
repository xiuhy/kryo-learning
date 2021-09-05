package org.bigmoon.learning.kryo;

import com.esotericsoftware.kryo.io.Input;
import org.bigmoon.learning.kryo.entity.Car;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.bigmoon.learning.kryo.safeThread.ThreadLocalExample.kryoPool;

/**
 * 熟悉自定义序列器。测试当发生对象变更属性时如何处理
 * @author bigmoon
 * @date 2021/8/1
 * @return
 * @see [相关类/方法]（可选）
 * @since
 */
public class KryoSerializer {

    public static void main(String[] args) throws Exception {
//        CollectionMain.ListTest();
//        serializeOrigObj();
        System.out.println(CharSequence.class.isAssignableFrom(String.class));
    }

    public static void serializeOrigObj() throws FileNotFoundException {

        //@seeCollectionMain中创建的cars.txt对象文件，然后变更car对象字段。重新获取一次

        try( Input input=new Input(new FileInputStream("cars.txt"))) {
            List<Car> cars=(List<Car>)kryoPool.obtain().readClassAndObject(input);
            System.out.println("原始数据读取完成，如下内容");
            cars.forEach(item->{
                System.out.println(item.toString());
            });
        }

    }
}
