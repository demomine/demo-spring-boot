package com.lance.demo.springboot;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by perdonare on 2017/5/9.
 * bootstrap
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.lance.demo.springboot.dao.mapper")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        log.info("application start success");
    }
}
