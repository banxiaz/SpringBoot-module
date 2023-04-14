package com.bai.controller;

import com.bai.entity.User;
import com.bai.entity.query.UserQueryBean;
import com.bai.service.IUserService;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@ToString
class MyUser {
    String name;
    int age;
}

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("add")
    public int add(@RequestBody User user) {
        System.out.println(user);

        if (user.getId() == null) {
            user.setCreateTime(LocalDateTime.now());
        }
        user.setUpdateTime(LocalDateTime.now());
        int res = userService.addUser(user);
        return res;
    }

    @GetMapping("getAllUser")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("getUserById")
    public User getUserById(@RequestParam("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("edit/{userId}") //restful风格 http://localhost:8080/user/edit/1
    public User edit(@PathVariable("userId") Long userId) {
        return userService.getById(userId);
    }

    @GetMapping("edit") //使用 http://localhost:8080/user/edit?id=1
    public User edit2(@RequestParam(value = "id") Long userId) {
        return userService.getById(userId);
    }

    @GetMapping("list")
    public List<User> list(UserQueryBean userQueryBean) {
        return userService.findList(userQueryBean);
    }

    // HTTP的post请求body部分可以有以下几种类型
    // multipart/form-data 主要用于提交表单
    // application/x-www-from-urlencoded 以键值对的形式提交表单
    // 在raw请求中，application/json以json格式提交

    //接收JSON类型的POST请求，用Map接收
    @PostMapping("jsonpost1")
    public String jsonPost(@RequestBody Map<String, Object> params) {
        System.out.println(params);
        return "succ jsonpost1";
    }

    //接收JSON类型的POST请求，用对象接收
    @PostMapping("jsonpost2")
    public String jsonPost2(@RequestBody MyUser myUser) {
        System.out.println(myUser);
        return "succ jsonpost2";
    }

    //接收JSON类型的POST请求，用列表接收
    @PostMapping("jsonpost3")
    public String jsonPost3(@RequestBody List<MyUser> myUser) {
        System.out.println(myUser);
        return "succ jsonpost3";
    }

    //接收x-www-form-urlencoded类型的POST请求，同时还可以使用Map<String, Object>来接收
    @PostMapping("jsonpost4")
    public String jsonPost3(@RequestParam("name") String name, @RequestParam int age) {
        System.out.println(name);
        System.out.println(age);
        return "succ jsonpost4";
    }

    @GetMapping("test")
    public String test(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        System.out.println(httpServletRequest);
        System.out.println(httpServletResponse);
        System.out.println(httpServletRequest.getRequestURI());
        System.out.println(httpServletRequest.getQueryString());
        System.out.println(httpServletRequest.getRemoteHost());
        System.out.println(httpServletRequest.getRemotePort());
        System.out.println(httpServletRequest.getRemoteUser());
        System.out.println(httpServletRequest.getRemoteAddr());

        httpServletResponse.setStatus(200);
        // httpServletResponse.addCookie(new Cookie("cookie","2022.10.04"));
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("name", "wustwust");
        System.out.println(session.getAttribute("name"));
        System.out.println(session.getAttribute("name1"));


        return "hello";
    }

}
