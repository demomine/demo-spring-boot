package com.lance.demo.springboot.web.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
@Slf4j
public class AsyncDemo {
    @Autowired
    private AsyncSupport asyncSupport;

    public void async() throws ExecutionException, InterruptedException {
        log.info("begin async ");
        Future<String> future = asyncSupport.async();
        String result = future.get();
        log.info("async result result: {}",result);
    }
}
