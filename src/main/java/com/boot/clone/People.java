package com.boot.clone;

import lombok.Data;

import java.io.Serializable;

@Data
public class People implements Serializable {
    private static final long serialVersionUID = -9102017020286042305L;

    private String name;    // 姓名
    private int age;        // 年龄
    private Car car;        // 座驾

    public People(String name, int age, Car car) {
        this.name = name;
        this.age = age;
        this.car = car;
    }
}
