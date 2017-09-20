package com.lance.demo.springboot.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Service
public class AsyncSupport {
    @Autowired
    private RestTemplate restTemplate;

    @Async
    public Future<String> async() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/async", String.class);
        String result = response.getBody();
        return new AsyncResult<>(result);
    }
}
