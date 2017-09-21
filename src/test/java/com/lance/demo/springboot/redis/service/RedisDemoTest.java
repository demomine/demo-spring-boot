package com.lance.demo.springboot.redis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDemoTest {
    @Autowired
    private RedisDemo redisDemo;
    @Test
    public void caching() throws Exception {
        while (true) {
            redisDemo.caching("lance", 27);
            Thread.sleep(2000L);
        }
    }

}