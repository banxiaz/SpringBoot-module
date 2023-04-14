package com.bai;

import com.bai.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringbootRedisApplicationTests {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("myKey", "myVaule");
        System.out.println(redisTemplate.opsForValue().get("myKey"));
    }

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void testRedisUtil() {
        redisUtil.set("key", "hello");
        System.out.println(redisUtil.get("key"));
    }
}
