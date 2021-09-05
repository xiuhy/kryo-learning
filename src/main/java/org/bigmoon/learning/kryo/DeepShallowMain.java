package org.bigmoon.learning.kryo;

import com.esotericsoftware.kryo.Kryo;
import org.bigmoon.learning.kryo.entity.Car;
import org.bigmoon.learning.kryo.entity.Engin;

/**
 * kryo 深浅拷贝
 * 拷贝：
 *     引用拷贝:两个对象以上链接一个引用地址
 *     对象拷贝：直接独立一个新的对象引用
 *         深拷贝:深拷贝是一个整个独立的对象拷贝，深拷贝会拷贝所有的属性,并拷贝属性指向的动态分配的内存
 *         浅拷贝:被复制对象的所有变量都含有与原来的对象相同的值，而所有的对其他对象的引用仍然指向原来的对象
 */
public class DeepShallowMain {

        public static void main(String[] args) {

            Engin engin=new Engin("BMW",12);
//            Car car=new Car("BMW",5.0d,1200000d,engin);
            Car car=new Car("BMW",5.0d,engin);
            Kryo kryo=new Kryo();
            kryo.setRegistrationRequired(false);
            Car carShallow=kryo.copyShallow(car);
            Car carDeep=kryo.copy(car);
            System.out.println("before-----------------------");
            System.out.println(carShallow);
            System.out.println(carDeep);
            car.setBand("名爵"); //修改主对象
            engin.setCylinder(20); //子对象修改
            System.out.println("after-----------------------");
            System.out.println(carShallow);
            System.out.println(carDeep);
        }
}
