package com.lance.demo.springboot.dao.entity;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.Version;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String id;
    private Integer age;
    private String name;
    @Version
    private Integer version;
    @TableLogic
    private String deleted;
}
