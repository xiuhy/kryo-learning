package org.bigmoon.learning.kryo.safeThread;

import com.esotericsoftware.kryo.kryo5.Kryo;
import com.esotericsoftware.kryo.kryo5.SerializerFactory;
import com.esotericsoftware.kryo.kryo5.io.Input;
import com.esotericsoftware.kryo.kryo5.io.Output;
import com.esotericsoftware.kryo.kryo5.serializers.CompatibleFieldSerializer;
import com.esotericsoftware.kryo.kryo5.util.Pool;
import org.bigmoon.learning.kryo.entity.SomeClass;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 默认序列化器可以参考官网地址:https://github.com/EsotericSoftware/kryo#serializers
 * <p>
 * FieldSerializer kryo默认序列化器,非public 也可以实现读写，可能速度上不够快。
 * 不支持新增，删除或者变更字段类型操作。在不改变字母排序顺序的情况下，可以重命名字段
 * <p>
 * VersionFieldSerializer
 * 支持新增字段，但是不支持删除变更，重命名或者变更字段类型
 * <p>
 * CompatibleFieldSerializer
 * 支持新增和删除操作，不支持重命名字段和变更字段类型。无需注解
 * chunkedEncoding 和readUnknowFiedlData两个配置非常重要，chunkedEncoding直接影响到性能
 * TaggedFieldSerializer
 * 支持新增，删除，修改名称。不支持修改类型。只序列化@Tag注解字段，且@Tag字段值必须唯一，包括父类
 * <p>
 * BeanSerializer
 * 和FieldSerializer相似，但是只通过getter或者sette获取，这样更加安全。不支持兼容
 */
public final class ThreadLocalExample {

    /**
     * 这个是通过 ThreadLocal 设置的，避免了非线程安全问题，但是无限制创建Kryo对象也是一种性能浪费
     */
    public static ThreadLocal<Kryo> kryos = new ThreadLocal<Kryo>() {
        @Override
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            //不使用类注册器
            kryo.setRegistrationRequired(false);
            //使用循环引用配置，对于性能有一定影响
            kryo.setReferences(true);
            return kryo;
        }
    };

    // Pool constructor arguments: thread safe:创建和使用都是同步锁
    // , soft references：true时可以允许在jvm内存达到高点的时候可以删除,
    // maximum capacity：线程池最大可容纳对象数量
    //通过Pool创建kryo对象线程池
    public static Pool<Kryo> kryoPool = new Pool<Kryo>(true, false, 8) {
        @Override
        protected Kryo create() {
            Kryo kryo = new Kryo();
            kryo.setRegistrationRequired(false);
            //使用循环引用配置，对于性能有一定影响
//            kryo.setReferences(true);

            //CompatibleFieldSerializer解析器可以兼容新增字段和删除字段
            CompatibleFieldSerializer.CompatibleFieldSerializerConfig config = new CompatibleFieldSerializer.CompatibleFieldSerializerConfig();
            config.setChunkedEncoding(true);
            SerializerFactory.CompatibleFieldSerializerFactory factory = new SerializerFactory.CompatibleFieldSerializerFactory(config);
            kryo.setDefaultSerializer(factory);
            return kryo;
        }
    };

    public static void main(String[] args) throws Exception {

        Kryo newKryo = kryoPool.obtain();
        Output output = new Output(new FileOutputStream("threadLocal.txt"));
        SomeClass someClass = new SomeClass("abc");
        newKryo.writeClassAndObject(output, someClass);
        output.close();

        try (Input input = new Input(new FileInputStream("threadLocal.txt"))) {

            SomeClass result = (SomeClass) kryos.get().readClassAndObject(input);
            System.out.println("result:" + result.getValue());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
