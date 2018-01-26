package com.lance.demo.springboot.hystrix.demo;

import com.lance.demo.springboot.hystrix.service.HelloFailHystrix;
import rx.Observable;
import rx.Observer;

import java.util.concurrent.ExecutionException;


public class HystrixHelloFailDemo {
    public void helloSyncDemo() {
        HelloFailHystrix HelloFailHystrix = new HelloFailHystrix("helloGroup");
        String execute = HelloFailHystrix.execute();
        System.out.println(execute);
    }

    public void helloAsyncDemo() throws ExecutionException, InterruptedException {
        HelloFailHystrix HelloFailHystrixA = new HelloFailHystrix("helloGroupA");
        HelloFailHystrix HelloFailHystrixB = new HelloFailHystrix("helloGroupB");
        String executeA = HelloFailHystrixA.queue().get();
        String executeB = HelloFailHystrixB.queue().get();
        System.out.println(executeA+ executeB);
    }

    public void helloObservableBlockingDemo() {
        HelloFailHystrix HelloFailHystrixA = new HelloFailHystrix("helloGroupA");

        Observable<String> observe = HelloFailHystrixA.observe();
        String single = observe.toBlocking().single();
        System.out.println(single);
    }

    public void helloObservableNonBlockingDemo() {
        HelloFailHystrix HelloFailHystrixA = new HelloFailHystrix("helloGroupA");
        HelloFailHystrix HelloFailHystrixB = new HelloFailHystrix("helloGroupB");

        Observable<String> observeA = HelloFailHystrixA.observe();
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


        Observable<String> observeB = HelloFailHystrixB.observe();
        observeB.subscribe(System.out::println);
    }
}
