package com.lance.demo.springboot.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisDemo {
    @Cacheable(value = "demo-cache",condition = "")
    public String caching(String name, int age) {
        log.info("=========not cached ====,name:{},age:{}",name,age);

        return name + age;
    }
}
