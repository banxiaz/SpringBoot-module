package com.bai.spring;

import com.bai.annotation.Autowired;
import com.bai.annotation.Component;
import com.bai.annotation.ComponentScan;
import com.bai.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class AnnotationConfigApplicationContext {
    /**
     * 用来存放spring的bean对象
     * key：class对象，比如UserController.class
     * value：Object，比如UserController的实例信息，通过反射创建
     */
    private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();
    //用来存放获取到的class对象
    private final Set<Class<?>> classSet = new HashSet<>();
    //维护一个list集合，用来存储bean的相关信息，比如context.getBeanDefinitionNames()
    private final List<Class<?>> list = new ArrayList<>();
    private final ClassLoader classLoader;

    public AnnotationConfigApplicationContext(Class<?> classes) {
        long start = System.currentTimeMillis();
        if (!classes.isAnnotationPresent(Configuration.class)) {
            log.error("不是一个配置类，使用Configuration注解标识");
            throw new RuntimeException("not find @interface Configuration");
        }
        if (!classes.isAnnotationPresent(ComponentScan.class)) {
            log.error("没有指定包扫描路径，使用ComponentScan注解标识");
            throw new RuntimeException("not find @interface ComponentScan");
        }

        //拿到ComponentScan中指定的要扫描的包的路径
        ComponentScan componentScan = classes.getAnnotation(ComponentScan.class);
        String basePackage = componentScan.basePackage();
        log.info("开始扫描包 [{}]", basePackage);
        this.classLoader = classes.getClassLoader();
        URL resource = this.classLoader.getResource(basePackage.replace(".", "/"));
        log.info("解析basePackage路径得到 [{}]", resource);
        if (resource == null) {
            log.error("解析路径失败");
            throw new RuntimeException("解析路径失败");
        }

        File file = new File(resource.getFile());
        //递归扫描指定路径，获取所有类的全限定类名 [com.bai.xx.class]，并进行类加载
        resolveFile(file, basePackage);
        //判断这些对象，是否包含了这些注解：Controller Service Component
        for (Class<?> clazz : classSet) {
            if (clazz.isAnnotationPresent(Component.class)) {
                try {
                    list.add(clazz);
                    Object o = clazz.newInstance(); //创建对象，并交给IOC容器管理，单例
                    beanMap.put(clazz, o);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        log.info("包扫描完成...");
        log.info("一共扫描到了[{}]个需要注入IOC容器中的类", list.size());

        log.info("下面开始依赖注入...");
        //下面开始DI依赖注入，主要扫描Autowired注解
        Set<Class<?>> classes1 = getClasses();
        for (Class<?> clazz : classes1) {
            //获取当前类的所有属性
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields) {
                //如果属性标注了@Autowired注解，则需要进行依赖注入
                if (field.isAnnotationPresent(Autowired.class)) {
                    //获取当前属性的类型
                    Class<?> fieldType = field.getType();
                    //从IOC容器中找，看看是否有这个类型的bean对象
                    Object fieldBean = getBean(fieldType);
                    //如果没有，试着从IOC容器中获取这个类型的子类，然后注入
                    if (fieldBean == null) {
                        //在IOC容器中找这个类型的子类
                        Set<Class<?>> resultSet = getClassBySuperOrInterface(fieldType);
                        if (resultSet.isEmpty()) {
                            log.error("自动注入失败，找不到bean");
                            throw new RuntimeException("自动注入失败");
                        }
                        //选择第一个实现类吧，后续可以改成按照名字匹配
                        fieldBean = beanMap.get(resultSet.iterator().next());
                    } else {
                        log.error("自动注入失败，找不到bean");
                        throw new RuntimeException("自动注入失败");
                    }
                    field.setAccessible(true);
                    try {
                        field.set(getBean(clazz), fieldBean); //给属性注入值
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        log.info("自动注入完成...");
        long end = System.currentTimeMillis();
        log.info("耗时 [{}] ms", end - start);

    }

    //找一个类型的全部子类实现
    private Set<Class<?>> getClassBySuperOrInterface(Class<?> superOrInterface) {
        //获取IOC容器中的所有key
        Set<Class<?>> classes = getClasses();
        //准备返回结果
        Set<Class<?>> result = new HashSet<>();
        for (Class<?> clazz : classes) {
            //如果clazz是superOrInterface的子类或者实现类时，可以作为结果之一返回
            if (superOrInterface.isAssignableFrom(clazz) && !clazz.equals(superOrInterface)) {
                result.add(clazz);
            }
        }
        return result;
    }

    public List<Class<?>> getBeanDefinitionNames() {
        return list;
    }

    public Object getBean(Class<?> key) {
        return beanMap.get(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T getBeanDirect(Class<T> key) {
        return (T) beanMap.get(key);
    }

    public Set<Class<?>> getClasses() {
        return beanMap.keySet();
    }

    //递归写法
    private void resolveFile(File file, String basePackage) {
        File[] files = file.listFiles();

        if (files == null) {
            return;
        }
        for (File value : files) {
            File absoluteFile = value.getAbsoluteFile();
            if (absoluteFile.isDirectory()) {
                resolveFile(absoluteFile, basePackage);
            } else if (absoluteFile.toString().endsWith(".class")) {
                String temp = absoluteFile.toString().replace(File.separator, ".");
                String className = temp.substring(temp.indexOf(basePackage));
                className = className.substring(0, className.lastIndexOf("."));
                try {
                    Class<?> clazz = this.classLoader.loadClass(className); //加载类
                    classSet.add(clazz);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
