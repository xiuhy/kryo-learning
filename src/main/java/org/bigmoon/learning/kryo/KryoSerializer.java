package org.bigmoon.learning.kryo;

import com.esotericsoftware.kryo.kryo5.Kryo;
import com.esotericsoftware.kryo.kryo5.io.Input;
import com.esotericsoftware.kryo.kryo5.serializers.FieldSerializer;
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

    public static void main(String[] args) throws FileNotFoundException {

        serializeOrigObj();
    }

    public static void serializeOrigObj() throws FileNotFoundException {
        Kryo kryo=new Kryo();
        kryo.setRegistrationRequired(false);
//        kryo.setReferences(true);
        //默认序列化器，不支持字段变更
        kryo.setDefaultSerializer(FieldSerializer.class);

        //@seeCollectionMain中创建的cars.txt对象文件，然后变更car对象字段。重新获取一次

        try( Input input=new Input(new FileInputStream("cars.txt"))) {
            List<Car> cars=(List<Car>)kryo.readClassAndObject(input);
            System.out.println("原始数据读取完成，如下内容");
            cars.forEach(item->{
                System.out.println(item.toString());
            });
        }

    }
}
