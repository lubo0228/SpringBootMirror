package com.boot.agent.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PeopleInvocationHandler implements InvocationHandler {
    private Object people;

    PeopleInvocationHandler(Object people) {
        this.people = people;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(people, args);
        System.out.println("-------- end ---------");
        return invoke;
    }
}

class Test {
    public static void main(String[] args) {
        Chinese chinese = new Chinese();
        PeopleInvocationHandler invocationHandler = new PeopleInvocationHandler(chinese);
        People proxy = (People) Proxy.newProxyInstance(chinese.getClass().getClassLoader(), chinese.getClass().getInterfaces(), invocationHandler);
        proxy.sayHello();
    }
}
