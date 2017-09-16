package com.lance.demo.springboot.config;


import brave.Tracing;
import brave.context.log4j2.ThreadContextCurrentTraceContext;
import brave.http.HttpTracing;
import brave.spring.web.TracingClientHttpRequestInterceptor;
import brave.spring.webmvc.TracingHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.Sender;
import zipkin.reporter.okhttp3.OkHttpSender;
import zipkin2.Span;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by immerer on 2017/9/16.
 */
@Configuration
@Import({TracingClientHttpRequestInterceptor.class, TracingHandlerInterceptor.class,RestTemplate.class})
public class TracingConfig implements WebMvcConfigurer {
    @Autowired
    private TracingHandlerInterceptor serverInterceptor;

    @Autowired
    private TracingClientHttpRequestInterceptor clientInterceptor;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public Sender sender() {
        return OkHttpSender.json("http://192.168.63.128:9411/api/v2/spans");
    }

    @Bean
    AsyncReporter<Span> spanReporter() {
        return AsyncReporter.v2(sender());
    }

    @Bean
    Tracing tracing() {
        return Tracing.newBuilder()
                .localServiceName("brave-webmvc-example")
                .currentTraceContext(ThreadContextCurrentTraceContext.create()) // puts trace IDs into logs
                .spanReporter(spanReporter()).build();
    }

    @Bean
    HttpTracing httpTracing() {
        return HttpTracing.create(tracing());
    }

    @PostConstruct
    public void init() {
        List<ClientHttpRequestInterceptor> interceptors =
                new ArrayList<>(restTemplate.getInterceptors());
        interceptors.add(clientInterceptor);
        restTemplate.setInterceptors(interceptors);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(serverInterceptor);

    }


}
