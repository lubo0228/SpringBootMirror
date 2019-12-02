package com.boot.agent.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ChineseProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object invokeSuper = methodProxy.invokeSuper(o, objects);
        System.out.println("-------- end ---------");
        return invokeSuper;
    }
}

class Test {
    public static void main(String[] args) {
        ChineseProxy chineseProxy = new ChineseProxy();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Chinese.class);
        enhancer.setCallback(chineseProxy);

        Chinese chinese = (Chinese) enhancer.create();
        chinese.sayHello();
    }
}