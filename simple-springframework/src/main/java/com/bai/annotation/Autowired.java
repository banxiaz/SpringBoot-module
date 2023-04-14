package com.bai.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) //修饰字段
@Retention(RetentionPolicy.RUNTIME) //生命周期运行时
public @interface Autowired {
}
