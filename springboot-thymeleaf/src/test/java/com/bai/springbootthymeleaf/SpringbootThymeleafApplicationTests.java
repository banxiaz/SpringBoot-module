package com.bai.springbootthymeleaf;

import com.bai.SpringbootThymeleafApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class SpringbootThymeleafApplicationTests {


    @Test
    void contextLoads() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringbootThymeleafApplication.class);
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }

}
