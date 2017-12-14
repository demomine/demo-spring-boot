package com.lance.demo.springboot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ReferenceType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.Map;

public class JsonUtil {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String getJson(T t) throws JsonProcessingException {
        return objectMapper.writeValueAsString(t);
    }

    public static <T> MultiValueMap<String, String> getMap(T t) throws IOException {
        MultiValueMap<String, String>  multiValueMap = new LinkedMultiValueMap<String, String>();
        Map<String, String> map = objectMapper.readValue(getJson(t), new TypeReference<Map<String, Object>>() {
        });
        map.forEach((key,value) -> {
            multiValueMap.add(key, value);
        });
        return multiValueMap;
    }
}
