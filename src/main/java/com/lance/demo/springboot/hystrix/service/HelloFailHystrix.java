package com.lance.demo.springboot.hystrix.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HelloFailHystrix extends HystrixCommand<String> {
    private final String groupName;
    public HelloFailHystrix(String groupName) {
        super(HystrixCommandGroupKey.Factory.asKey(groupName));
        this.groupName = groupName;
    }

    @Override
    protected String run(){
        throw new RuntimeException("always fail");
    }

    @Override
    protected String getFallback() {
        return groupName+" fail then fallback";
    }
}
