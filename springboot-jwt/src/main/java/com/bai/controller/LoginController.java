package com.bai.controller;

import com.bai.entity.User;
import com.bai.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    static Map<Integer, User> userMap = new HashMap<>();

    static {
        log.info("进入LoginController的static代码块，用来模拟数据库");
        User user1 = new User(1, "zhangsan", "123456");
        User user2 = new User(2, "lisi", "654321");
        userMap.put(user1.getId(), user1);
        userMap.put(user2.getId(), user2);
    }

    // http://localhost:8080/login?id=1&userName=zhangsan&password=123456
    // http://localhost:8080/secure/getUserInto
    @RequestMapping("/login")
    public String login(User user) {
        log.info("获取到用户信息[{}]", user);
        for (User dbUser : userMap.values()) {
            if (dbUser.getUserName().equals(user.getUserName()) && dbUser.getPassword().equals(user.getPassword())) {
                log.info("[{}]登录成功，生成token..", user.getUserName());
                String token = JwtUtil.createToken(dbUser);
                log.info("生成的token为[{}]", token);

                return token;
            }
        }
        return "用户登录失败";
    }
}
