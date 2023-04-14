package com.bai.test;

public class Hello {
    static {
        System.out.println("Hello 类被加载");
    }

    public void sayHello() {
        System.out.println("say hello被调用...");
    }
}
