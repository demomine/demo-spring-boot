package com.lance.demo.springboot.hystrix.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HelloHystrix extends HystrixCommand<String> {
    private final String groupName;
    public HelloHystrix(String groupName) {
        super(HystrixCommandGroupKey.Factory.asKey(groupName));
        this.groupName = groupName;
    }

    @Override
    protected String run(){
        return "hello world";
    }


}
