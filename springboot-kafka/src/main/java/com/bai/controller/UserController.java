package com.bai.controller;

import com.bai.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {
    private static final String topicName = "learnkafka";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @ResponseBody
    @GetMapping("/test/{num}")
    public User sendMessage(@PathVariable("num") String num) {
        User user = new User();
        user.setName(num);
        user.setAge(20);
        user.setEmail("123456@qq.com");

        // 默认异步发送
        kafkaTemplate.send(topicName, user.toString()).addCallback(
                success -> {
                    // 消息发送的topic
                    String topic = success.getRecordMetadata().topic();
                    // 消息发送的分区
                    int partition = success.getRecordMetadata().partition();
                    // 消息在分区内的offset
                    long offset = success.getRecordMetadata().offset();
                    log.info("异步发送消息成功" + topic + "-" + partition + "-" + offset);
                }, failure -> {
                    log.info("异步发送消息失败：" + failure.getMessage());
                });
        return user;
    }

    @ResponseBody
    @GetMapping("/test/sync/{num}")
    public User sendMessageSync(@PathVariable("num") String num) throws Exception {
        User user = new User();
        user.setName(num);
        user.setAge(20);
        user.setEmail("wustsync@qq.com");

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicName, user.toString());
        SendResult<String, Object> result = future.get();
        log.info("同步发送成功：" + result.getProducerRecord().value());
        return user;
    }
}
