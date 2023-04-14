package com.bai.controller;

import com.bai.entity.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/post")
public class GetDataController {
    @GetMapping("/")
    public String form(ModelMap map) {
        map.put("username", "入门");
        Login login = new Login();
        login.setName("loginName");
        login.setPassword("loginPS");
        map.put("userInfo", login);
        return "form";
    }

    @ResponseBody
    @GetMapping("getmethod")
    public String getMethod(@RequestParam("name") String name, @RequestParam("password") String password) {
        System.out.println(name);
        System.out.println(password);
        return "success get";
    }

    @ResponseBody
    @GetMapping("getmethod/{name}/{password}")
    public String getMethodRest(@PathVariable("name") String name, @PathVariable("password") String password) {
        System.out.println(name);
        System.out.println(password);
        return "success get restful";
    }

    @ResponseBody
    @GetMapping("getmethodmap")
    public String getMethodMap(@RequestParam Map<String, Object> map) {
        System.out.println(map);
        return "success get map";
    }

    @ResponseBody
    @GetMapping("getmethodlist")
    public String getMethodList(@RequestParam("name") List<String> list) {
        System.out.println(list);
        return "success get list";
    }

    @ResponseBody
    @GetMapping("getmethodobj")
    public String getMethodObj(Login login) {
        System.out.println(login);
        return "success get obj";
    }

    // HTTP的post请求body部分可以有以下几种类型
    // multipart/form-data 主要用于提交表单
    // application/x-www-from-urlencoded 以键值对的形式提交表单
    // 在raw请求中，application/json以json格式提交，只能用@RequestBody
    // @RequestBody接收请求体中的json数据，不加注解接收URL中的数据并组装为对象
    // https://blog.csdn.net/small_love/article/details/111806745
    // https://blog.csdn.net/damage_e/article/details/112983459

    //接收JSON类型的POST请求，用Map接收
    @ResponseBody
    @PostMapping("postjson1")
    public String postMethod1(@RequestBody Map<String, Object> params) {
        System.out.println(params);
        return "postjson1";
    }

    //接收JSON类型的POST请求，用对象接收
    @ResponseBody
    @PostMapping("postjson2")
    public String postMethod2(@RequestBody Login login) {
        System.out.println(login);
        return "postjson2";
    }

    //接收JSON类型的POST请求，用List接收
    @ResponseBody
    @PostMapping("postjson3")
    public String postMethod3(@RequestBody List<Login> login) {
        System.out.println(login);
        return "postjson3";
    }

    //接收x-www-form-urlencoded类型的POST请求，同时还可以使用Map<String, Object>来接收
    @ResponseBody
    @PostMapping("jsonpost4")
    public String postMethod4(@RequestParam("name") String name, @RequestParam("password") String password) {
        System.out.println(name);
        System.out.println(password);
        return "succ jsonpost4";
    }

    @ResponseBody
    @PostMapping("jsonpost5")
    public String postMethod5(@RequestParam Map<String, Object> map) {
        System.out.println(map);

        return "succ jsonpost5";
    }

    @ResponseBody
    @PostMapping("jsonpost6") // 提交列表时用
    public String postMethod6(@RequestParam List<String> strings) {
        System.out.println(strings);

        return "succ jsonpost6";
    }

    @ResponseBody
    @PostMapping("jsonpost7")
    public String postMethod7(Login login) {
        System.out.println(login);

        return "succ jsonpost7";
    }


}
