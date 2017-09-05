package com.lance.demo.springboot;

import lombok.Data;

@Data
public class User {
    private String id;
    private String name;
    private int age;

    public User() {
    }
    public User(String id) {
        this.id = id;
    }


}
