package com.lance.demo.springboot.controller;

import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Created by immerer on 2017/11/24.
 */
@RestController
@RequestMapping("/demo")
@Timed("1")
public class PrometheusController {
    private static Random random = new Random();

    @RequestMapping("/endpoint")
    public void endpoint() {

    }

    @GetMapping("/prometheus")
    public String demo() {
        return "hello prometheus";
    }

    @GetMapping("/gauge")
    @Timed
    public String gauge() {
        return "gauge";
    }
}
