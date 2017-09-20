package com.lance.demo.springboot.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DemoController {

    @RequestMapping("/async")
    public String async() throws InterruptedException {
        Thread.sleep(3000L);
        return "async";
    }
}
