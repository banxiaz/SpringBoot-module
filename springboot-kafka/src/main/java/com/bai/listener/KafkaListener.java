package com.bai.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
public class KafkaListener {
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @PostConstruct
    private void listener() {
        kafkaTemplate.setProducerListener(new ProducerListener<String, Object>() {
            @Override
            public void onSuccess(ProducerRecord<String, Object> producerRecord, RecordMetadata recordMetadata) {
                log.info("ok, message = {}", producerRecord.value());
            }

            @Override
            public void onError(ProducerRecord<String, Object> producerRecord, RecordMetadata recordMetadata, Exception exception) {
                log.info("error, message = {}", producerRecord.value());
            }
        });
    }
}
