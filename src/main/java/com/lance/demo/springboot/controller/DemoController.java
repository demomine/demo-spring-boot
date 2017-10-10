package com.lance.demo.springboot.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/logging")
    public String demoGet() {
        return "hello world";
    }

    @PostMapping("/logging")
    public String demoPost(String hello) {
        return hello;
    }

    @PutMapping("/logging")
    public String demoPut(String hello) {
        return hello;
    }

    @PatchMapping("/logging")
    public String demoPatch(String hello) {
        return hello;
    }
}
