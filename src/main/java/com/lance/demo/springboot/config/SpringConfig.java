package com.lance.demo.springboot.config;

import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Created by perdonare on 2017/5/11.
 * spring config
 */
@Configuration
public class SpringConfig {
    @Bean
    MeterRegistryConfigurer configurer() {
        return registry -> registry.config().commonTags("region", "us-east-1");
    }
}
