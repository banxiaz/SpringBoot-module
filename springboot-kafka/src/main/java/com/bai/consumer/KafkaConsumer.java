package com.bai.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    //不指定group，默认取yml里配置的
    @KafkaListener(id = "con_01", topics = {"learnkafka"})
    // @KafkaListener(topics = {"learnkafka"})
    public void listen(String msg) {
        log.info("我收到的数据是-" + msg);
    }
}
