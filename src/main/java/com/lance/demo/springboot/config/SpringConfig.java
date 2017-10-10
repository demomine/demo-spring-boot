package com.lance.demo.springboot.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Created by perdonare on 2017/5/11.
 * spring config
 */
@Configuration
public class SpringConfig {
    @Bean
    public FilterRegistrationBean monitorFilterRegistrationBean() {
        FilterRegistrationBean<MonitorFilter> registrationBean = new FilterRegistrationBean<>();
        MonitorFilter monitorFilter = new MonitorFilter();
        registrationBean.setFilter(monitorFilter);
        registrationBean.setEnabled(true);
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
