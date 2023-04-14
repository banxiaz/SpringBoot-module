package com.bai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// https://blog.csdn.net/u011066470/article/details/115379182
// static和templates分别为静态资源和动态资源的父级目录
// static可以通过URL直接访问，而templates只能通过controller访问
// 如果添加了模板引擎，则所有的视图都会被添加前缀和后缀
// 如果要在controller里面访问静态资源，需要添加redirect和forward
// 总结：不管是重定向、请求转发还是返回视图，不管是动态页面还是静态资源，只能返回URL拼串后能直接定位到的资源
// 302 临时性重定向 304 not modified使用缓存
// 重定向能防止表单重复提交（地址栏和刷新按钮）：请求转发不会改变地址栏，刷新相当于又请求了一次，而重定向可以改变地址栏

@Controller
@RequestMapping("/page")
public class PageController {

    @GetMapping("/index.html") // 200 通过了视图解析器
    public String simple() {
        return "test/dynamic";
    }

    @RequestMapping("/static")
    public String staticPage() {
        return "redirect:/jm.jpeg"; //OK! 302
    }

    @RequestMapping("/static2")
    public String staticPage2() {
        return "redirect:/static.html"; // OK! 但是static.html是一个静态页面 302 + 302
    }

    @RequestMapping("/static3")
    public String staticPage3() {
        return "forward:/static.html"; // OK! 但是static.html是一个静态页面 200
    }

    @GetMapping("/redirect") //重定向
    public String redirect() {
        return "redirect:/page/index.html";
    }

    @GetMapping("/forward") //请求转发
    public String forward() {
        return "forward:/page/index.html";
    }
}
