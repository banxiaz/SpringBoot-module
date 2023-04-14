package com.bai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// https://www.cnblogs.com/woshimrf/p/sessionId.html sessionID如何产生的？
// https://blog.csdn.net/weixin_51405694/article/details/124991046 cookie，本地存储，会话存储的区别
// https://blog.csdn.net/xiaohui_brook/article/details/119611855 http的请求包含哪几个部分

@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/ordinary")
    // 访问它不会产生session
    public String noSession() {
        return "hello";
    }

    @GetMapping("/session")
    public String session(HttpServletRequest request) {
        // 每个会话会有一个session，在浏览器里面也可以看到，多次请求ID不变
        // 如果手动在浏览器删除了这个session，在访问会分发一个新的session
        HttpSession session = request.getSession(); //在这一句才设置，Set-Cookie: JSESSIONID=46A1C8129CACB407B00FCF1DD12ADCF1; Path=/; HttpOnly
        System.out.println(session.getId());

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                System.out.println(c.getName());
            }
        }
        return "hello2";
    }

    @GetMapping("/cookie")
    public String cookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("MyCookie", "20221020");
        cookie.setMaxAge(60);
        response.addCookie(cookie);

        return "hello3";
    }
}
