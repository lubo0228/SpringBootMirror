package com.boot.agent.proxy;

public class Chinese implements People {
    @Override
    public void sayHello() {
        System.out.println("proxy Chinese say hello.");
    }
}
