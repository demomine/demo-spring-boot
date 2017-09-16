package com.lance.demo.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

// notice there is no tracing code in this class
@RestController
@Slf4j
public class TracingController {
  @Autowired
  RestTemplate template;

  @RequestMapping("/a")
  public String a(HttpServletRequest request) throws InterruptedException {
    log.info("in /a"); // arbitrary log message to show integration works
    Random random = new Random();
    Thread.sleep(random.nextInt(1000));

    // make a relative request to the same process
    StringBuffer nextUrl = request.getRequestURL();
    nextUrl.deleteCharAt(nextUrl.length() - 1).append('b');
    return template.getForObject(nextUrl.toString(), String.class);
  }

  @RequestMapping("/b")
  public String b() throws InterruptedException {
    log.info("in /b"); // arbitrary log message to show integration works
    Random random = new Random();
    Thread.sleep(random.nextInt(1000));

    return "b";
  }
}
