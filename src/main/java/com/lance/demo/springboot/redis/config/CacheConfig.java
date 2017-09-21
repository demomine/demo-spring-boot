package com.lance.demo.springboot.redis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
@Slf4j
public class CacheConfig  extends CachingConfigurerSupport {
    @Bean
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                log.error("缓存读取 redis异常,请检查redis连接,key:{}",key);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                log.error("缓存存放 redis异常,请检查redis连接,key:{}",key);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                log.error("缓存消除 redis异常,请检查redis连接,key:{}",key);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                log.error("缓存清除 redis异常,请检查redis连接");
            }
        };
    }
}
