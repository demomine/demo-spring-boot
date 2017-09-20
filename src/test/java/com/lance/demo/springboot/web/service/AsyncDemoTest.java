package com.lance.demo.springboot.web.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
//使用DEFINED_PORT需要在配置文件中指定端口
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc()
public class AsyncDemoTest {
    @Autowired
    private AsyncDemo asyncDemo;
    @Test
    public void async() throws Exception {
        asyncDemo.async();
    }

}