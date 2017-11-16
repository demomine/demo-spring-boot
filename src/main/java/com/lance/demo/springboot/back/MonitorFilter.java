package com.lance.demo.springboot.back;

import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.slf4j.MDC;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by perdonare on 2016/12/14.
 */
@Slf4j
public class MonitorFilter implements Filter {
    private static final String MONITOR_FLAG = "_monitor";
    private Set<String> reqMethod = ImmutableSet.of(HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name(), HttpMethod.PATCH.name());



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        ResponseWrapper errorResponseWrapper = new ResponseWrapper(response);
        try {
            if (reqMethod.contains(request.getMethod())) {
                String requestContent = IOUtils.toString(request.getInputStream(),request.getCharacterEncoding());
                recordLog(requestContent);
                filterChain.doFilter(servletRequest, errorResponseWrapper);
                recordLog(new String(errorResponseWrapper.getResponseData(),response.getCharacterEncoding()));
                clearMonitor();
            }else {
                filterChain.doFilter(servletRequest, errorResponseWrapper);
            }
        } finally {
            clearMonitor();
        }
    }

    private void recordLog(String content) {
        MDC.put(MONITOR_FLAG, Boolean.TRUE.toString());
        log.info(content);
        clearMonitor();
    }

    private void clearMonitor() {
        if (MDC.getCopyOfContextMap() != null) {
            MDC.getCopyOfContextMap().remove(MONITOR_FLAG);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
