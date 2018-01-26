package com.lance.demo.springboot.hystrix.demo;

import org.junit.Test;


public class HystrixHelloDemoTest {
    HystrixHelloDemo hystrixHelloDemo = new HystrixHelloDemo();
    @Test
    public void helloSyncDemo() {
        hystrixHelloDemo.helloSyncDemo();
    }

    @Test
    public void helloAsyncDemo() throws Exception{
        hystrixHelloDemo.helloAsyncDemo();
    }

    @Test
    public void helloObservableBlockingDemo() {
        hystrixHelloDemo.helloObservableBlockingDemo();
    }

    @Test
    public void helloObservableNonBlockingDemo() {
        hystrixHelloDemo.helloObservableNonBlockingDemo();
    }


}