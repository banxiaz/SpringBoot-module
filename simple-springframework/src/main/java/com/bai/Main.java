package com.bai;

import com.bai.annotation.ComponentScan;
import com.bai.annotation.Configuration;
import com.bai.mycomponent.Controller;
import com.bai.spring.AnnotationConfigApplicationContext;

import java.util.List;

@Configuration
@ComponentScan(basePackage = "com.bai.mycomponent")
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        List<Class<?>> beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(beanDefinitionNames);
        Controller beanDirect = context.getBeanDirect(Controller.class);
        beanDirect.doController();

    }
}
