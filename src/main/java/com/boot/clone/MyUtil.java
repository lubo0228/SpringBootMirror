package com.boot.clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MyUtil {

    private MyUtil() {
        throw new AssertionError();
    }

    public static <T> T clone(T obj) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(obj);
        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);
        return (T) ois.readObject();
        // 说明：调用ByteArrayInputStream或ByteArrayOutputStream对象的close方法没有任何意义
        // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外部资源（如文件流）的释放
    }

}

class Test {
    public static void main(String[] args) {
        try {
            Person person1 = new Person("Hao LUO", 33, new Car("Benz", 30));
            Person person2 = (Person) person1.clone(); //浅克隆
            person2.setAge(22);
            person2.setName("OKAY");
            person2.getCar().setBrand("BYD");
            person2.getCar().setMaxSpeed(100);
            System.out.println(person1);
            System.out.println(person2);

            People p1 = new People("Hao LUO", 33, new Car("Benz", 30));
            People p2 = MyUtil.clone(p1);   // 深度克隆
            p2.setAge(22);
            p2.setName("OKAY");
            p2.getCar().setBrand("BYD");
            p2.getCar().setMaxSpeed(100);
            // 修改克隆的Person对象p2关联的汽车对象的品牌属性
            // 原来的Person对象p1关联的汽车不会受到任何影响
            // 因为在克隆Person对象时其关联的汽车对象也被克隆了
            System.out.println(p1);
            System.out.println(p2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
