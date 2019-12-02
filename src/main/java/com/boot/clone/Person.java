package com.boot.clone;

import lombok.Data;

@Data
public class Person implements Cloneable {

    private String name;    // 姓名
    private int age;        // 年龄
    private Car car;        // 座驾

    public Person(String name, int age, Car car) {
        this.name = name;
        this.age = age;
        this.car = car;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
