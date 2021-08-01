package org.bigmoon.learning.kryo.safeThread;

import com.esotericsoftware.kryo.kryo5.Kryo;
import com.esotericsoftware.kryo.kryo5.io.Input;
import com.esotericsoftware.kryo.kryo5.io.Output;
import com.esotericsoftware.kryo.kryo5.util.Pool;
import org.bigmoon.learning.kryo.entity.SomeClass;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public final class ThreadLocalExample {

    /**
     * 这个是通过 ThreadLocal 设置的，避免了非线程安全问题，但是无限制创建Kryo对象也是一种性能浪费
     */
    public static ThreadLocal<Kryo> kryos=new ThreadLocal<Kryo>(){
        @Override
        protected Kryo initialValue() {
            Kryo kryo=new Kryo();
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
  public  static Pool<Kryo> kryoPool = new Pool<Kryo>(true, false, 8) {
        @Override
        protected Kryo create () {
            Kryo kryo = new Kryo();
            kryo.setRegistrationRequired(false);
            //使用循环引用配置，对于性能有一定影响
//            kryo.setReferences(true);
            return kryo;
        }
    };

    public static void main(String[] args) throws Exception {

        Kryo newKryo=kryoPool.obtain();
        Output output=new Output(new FileOutputStream("threadLocal.txt"));
        SomeClass someClass=new SomeClass("abc");
        newKryo.writeClassAndObject(output,someClass);
        output.close();

        try(Input input=new Input(new FileInputStream("threadLocal.txt"))){

            SomeClass result= (SomeClass)kryos.get().readClassAndObject(input);
            System.out.println("result:"+result.getValue());

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
