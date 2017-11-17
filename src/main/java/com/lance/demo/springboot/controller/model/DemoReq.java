package com.lance.demo.springboot.controller.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DemoReq {
    @NotNull
    private String name;

    private int age;
}
