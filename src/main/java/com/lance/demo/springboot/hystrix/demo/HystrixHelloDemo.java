package com.lance.demo.springboot.hystrix.demo;

import com.lance.demo.springboot.hystrix.service.HelloHystrix;
import rx.Observable;
import rx.Observer;
import java.util.concurrent.ExecutionException;


public class HystrixHelloDemo {
    public void helloSyncDemo() {
        HelloHystrix helloHystrix = new HelloHystrix("helloGroup");
        String execute = helloHystrix.execute();
        System.out.println(execute);
    }

    public void helloAsyncDemo() throws ExecutionException, InterruptedException {
        HelloHystrix helloHystrixA = new HelloHystrix("helloGroupA");
        HelloHystrix helloHystrixB = new HelloHystrix("helloGroupB");
        String executeA = helloHystrixA.queue().get();
        String executeB = helloHystrixB.queue().get();
        System.out.println(executeA+ executeB);
    }

    public void helloObservableBlockingDemo() {
        HelloHystrix helloHystrixA = new HelloHystrix("helloGroupA");

        Observable<String> observe = helloHystrixA.observe();
        String single = observe.toBlocking().single();
        System.out.println(single);
    }

    public void helloObservableNonBlockingDemo() {
        HelloHystrix helloHystrixA = new HelloHystrix("helloGroupA");
        HelloHystrix helloHystrixB = new HelloHystrix("helloGroupB");

        Observable<String> observeA = helloHystrixA.observe();
        observeA.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });


        Observable<String> observeB = helloHystrixB.observe();
        observeB.subscribe(System.out::println);
    }
}
