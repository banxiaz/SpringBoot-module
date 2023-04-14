package com.bai.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class SecureController {
    @RequestMapping("/secure/getUserInto")
    public String login(HttpServletRequest request) {
        int id = (int) request.getAttribute("id");
        String userName = (String) request.getAttribute("userName");
        String password = (String) request.getAttribute("password");
        return "当前的用户信息：" + "id " + id + "userName " + userName + "password " + password;
    }
}
