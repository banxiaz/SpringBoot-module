package com.bai.mycomponent.impl;

import com.bai.annotation.Component;
import com.bai.mycomponent.Service;

@Component
public class ServiceImpl implements Service {
    @Override
    public void syaHello() {
        System.out.println("ServiceImpl syaHello...");
    }
}
