package com.lance.demo.springboot.service;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertNull;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class JsonAssertTest {

    @Test
    public void testJsonAssert() throws Exception{
        String actual = "{id:123, zip:\"33025\",name:\"John\"}";
        JSONAssert.assertEquals(
                "{id:123,name:\"John\"}", actual, JSONCompareMode.LENIENT);
    }

    @Test
    public void testJsonPath() throws Exception {
        JsonPath.parse(getString()).read("$.interest_two");
        JsonPath.read(getString(),"$");
        System.out.println("asdf");
    }

    private String getString() {
        return "{\n" +
                "\t\"interest_one\":\n" +
                "\t[\n" +
                "\t\t{\n" +
                "\t\t\t\"amount\":18,\n" +
                "\t\t\t\"billId\":\"0ce29f1080d411e78b5a00163e12be70\",\n" +
                "\t\t\t\"contractNo\":\"8241392846024704\",\n" +
                "\t\t\t\"interestType\":\"INTEREST\",\n" +
                "\t\t\t\"orgCode\":\"123456\",\n" +
                "\t\t\t\"productCode\":\"ZFQPDLOAN001\",\n" +
                "\t\t\t\"stagesId\":\"0ce29f1180d411e78b5a00163e12be70\",\n" +
                "\t\t\t\"status\":\"OPEN\",\n" +
                "\t\t\t\"subProductCode\":\"ZFQPDLOAN001\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"interest_two\":\n" +
                "\t[\n" +
                "\t\t{\n" +
                "\t\t\t\"amount\":18,\n" +
                "\t\t\t\"billId\":\"0ce29f1080d411e78b5a00163e12be70\",\n" +
                "\t\t\t\"contractNo\":\"8241392846024704\",\n" +
                "\t\t\t\"interestType\":\"INTEREST\",\n" +
                "\t\t\t\"orgCode\":\"123456\",\n" +
                "\t\t\t\"productCode\":\"ZFQPDLOAN001\",\n" +
                "\t\t\t\"stagesId\":\"0ce29f1180d411e78b5a00163e12be70\",\n" +
                "\t\t\t\"status\":\"OPEN\",\n" +
                "\t\t\t\"subProductCode\":\"ZFQPDLOAN001\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"amount\":17,\n" +
                "\t\t\t\"billId\":\"0ce29f1080d411e78b5a00163e12be70\",\n" +
                "\t\t\t\"contractNo\":\"8241392846024704\",\n" +
                "\t\t\t\"interestType\":\"INTEREST\",\n" +
                "\t\t\t\"orgCode\":\"123456\",\n" +
                "\t\t\t\"productCode\":\"ZFQPDLOAN001\",\n" +
                "\t\t\t\"stagesId\":\"0ce29f1180d411e78b5a00163e12be70\",\n" +
                "\t\t\t\"status\":\"OPEN\",\n" +
                "\t\t\t\"subProductCode\":\"ZFQPDLOAN001\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}\n" +
                "\n";
    }
}
