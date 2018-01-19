package com.lance.demo.springboot.service;

import org.junit.Test;

public class ReflectTest {
    @Test
    public void test() {
        invokeBy();
    }
    private void invokeBy() {
        System.out.println(getLocation(Thread.currentThread().getStackTrace()[2].getClassName()));
    }

    private String getLocation(String fullName) {
        String[] splits = fullName.split("\\.");
        if (splits.length != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String split : splits) {
                stringBuilder.append("/").append(split);
            }
            return stringBuilder.toString();
        }
        return null;
    }
}
