package com.lance.demo.springboot.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonAssertTest {

    @Test
    public void testJsonAssert() throws Exception{
        String actual = "{id:123, zip:\"33025\",name:\"John\"}";
        JSONAssert.assertEquals(
                "{id:123,name:\"John\"}", actual, JSONCompareMode.LENIENT);
    }
}
