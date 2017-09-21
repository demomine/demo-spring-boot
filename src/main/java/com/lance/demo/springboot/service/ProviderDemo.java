package com.lance.demo.springboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@Slf4j
public class ProviderDemo {
    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void produce() {
        //kafkaTemplate.
        String data = "hello world";
        kafkaTemplate.send(topic, data);
        log.info("send data :{} ",data);
    }

    public void produceAck() {
        //kafkaTemplate.
        String data = "hello world";
        ListenableFuture<SendResult<String, String>> listenableFuture =  kafkaTemplate.send(topic, data);
        listenableFuture.isDone();
        log.info("send data :{} ",data);
    }
}
