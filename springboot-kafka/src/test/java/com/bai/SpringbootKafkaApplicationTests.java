package com.bai;

import com.bai.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SpringBootTest
class SpringbootKafkaApplicationTests {
    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Test
    void contextLoads() {
        User user = new User();
        user.setName("hello kafka synchronization");
        user.setAge(20);
        user.setEmail("123456@qq.com");
        //异步发送
        kafkaTemplate.send("test", user.toString());
    }

    @Test
    void asy_get() throws ExecutionException, InterruptedException, TimeoutException {
        User user = new User();
        user.setName("hello kafka asynchronous");
        user.setAge(20);
        user.setEmail("123456@qq.com");
        //同步发送其实就是发送时强制监听结果
        ListenableFuture<SendResult<String, Object>> sendResult = kafkaTemplate.send("test", user.toString());
        //开始监听,设置一个时间，超过后放弃此处监听
        SendResult<String, Object> result = sendResult.get(3, TimeUnit.SECONDS);
        System.out.println("监听到的结果-------" + result.getProducerRecord().value());

    }

}
