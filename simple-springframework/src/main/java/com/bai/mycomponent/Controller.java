package com.bai.mycomponent;

import com.bai.annotation.Autowired;
import com.bai.annotation.Component;

@Component
public class Controller {
    @Autowired
    Service service;

    public void doController() {
        System.out.println(service.getClass());
        service.syaHello();
    }
}
