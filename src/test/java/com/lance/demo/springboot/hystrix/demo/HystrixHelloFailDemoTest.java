package com.lance.demo.springboot.hystrix.demo;

import org.junit.Test;

public class HystrixHelloFailDemoTest {
    HystrixHelloFailDemo hystrixHelloFailDemo = new HystrixHelloFailDemo();
    @Test
    public void helloSyncDemo() {
        hystrixHelloFailDemo.helloSyncDemo();
    }

    @Test
    public void helloAsyncDemo() throws Exception{
        hystrixHelloFailDemo.helloAsyncDemo();
    }

    @Test
    public void helloObservableBlockingDemo() {
        hystrixHelloFailDemo.helloObservableBlockingDemo();
    }

    @Test
    public void helloObservableNonBlockingDemo() {
        hystrixHelloFailDemo.helloObservableBlockingDemo();
    }
}