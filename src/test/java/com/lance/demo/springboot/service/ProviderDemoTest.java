package com.lance.demo.springboot.service;

import com.lance.demo.springboot.config.KafkaConfig;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import({KafkaConfig.class})
public class ProviderDemoTest {
    @Autowired
    private ProviderDemo providerDemo;
    @org.junit.Test
    public void produce() throws Exception {
        providerDemo.produce();
        Thread.sleep(10000L);
    }

}