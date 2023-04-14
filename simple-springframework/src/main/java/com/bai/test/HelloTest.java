package com.bai.test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

public class HelloTest {
    public static void main(String[] args) {
        String path = "com.bai.test";
        path = path.replace(".", "/");
        ClassLoader classLoader = HelloTest.class.getClassLoader();
        URL resource = classLoader.getResource(path);
        System.out.println(resource);
        //file:/D:/Java_Files/Demo_pdai/springboot-module/simple-springframework/target/classes/com/bai/test
        if (resource != null) {
            File file = new File(resource.getFile());
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File file1 : files) {
                    String fileName = file1.getAbsolutePath();
                    System.out.println(fileName);
                    if (fileName.endsWith(".class")) {
                        String className = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
                        className = className.replace("\\", ".");
                        System.out.println(className);

                        try {
                            Class<?> aClass = classLoader.loadClass(className); //最终目的达到，不会触发类加载
                            System.out.println(aClass);
                            Object o = aClass.newInstance();
                            Method sayHello = aClass.getMethod("sayHello");
                            sayHello.invoke(o);
                            break;
                        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
    }
}
