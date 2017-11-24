package com.lance.demo.springboot.config;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.context.annotation.Configuration;
/**
 * Created by perdonare on 2017/5/11.
 * spring config
 */
@Configuration
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
public class SpringConfig {
}
