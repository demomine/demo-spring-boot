package com.lance.demo.springboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerDemo {
    @KafkaListener(topics = {"${kafka.topic}"})
    public void consume(String data, Acknowledgment ack) {
        log.info("receive data: {} ",data);
        ack.acknowledge();
    }
}
