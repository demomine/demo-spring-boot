package com.lance.demo.springboot.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.slf4j.MDC;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;
import java.util.HashSet;
import java.util.Set;
@Slf4j
public class MonitorFilter implements Filter {
    public static final String MONITOR_FLAG = "_monitor";
    private Set<String> reqMethod = new HashSet<>();

    public MonitorFilter() {
        reqMethod.add(HttpMethod.POST.name());
        reqMethod.add(HttpMethod.PUT.name());
        reqMethod.add(HttpMethod.PATCH.name());
        reqMethod.add(HttpMethod.DELETE.name());
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        try {
            if (reqMethod.contains(httpServletRequest.getMethod())) {
                String request = IOUtils.toString(httpServletRequest.getInputStream());
                recordLog(request);
                ErrorResponseWrapper errorResponseWrapper = new ErrorResponseWrapper(httpServletResponse);
                filterChain.doFilter(servletRequest, errorResponseWrapper);
                recordLog(new String(errorResponseWrapper.getResponseData(),httpServletResponse.getCharacterEncoding()));
                clearMonitor();
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } finally {
            clearMonitor();
        }
    }

    @Override
    public void destroy() {

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



    class ErrorResponseWrapper extends HttpServletResponseWrapper {
        private ByteArrayOutputStream buffer = null;
        private ServletOutputStream   out    = null;
        private PrintWriter writer = null;

        public ErrorResponseWrapper(HttpServletResponse resp) throws IOException {
            super(resp);
            buffer = new ByteArrayOutputStream();
            out = new WrappedOutputStream(buffer);
            writer = new PrintWriter(new OutputStreamWriter(buffer, this.getCharacterEncoding()));
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return out;
        }

        //重载父类获取writer的方法
        @Override
        public PrintWriter getWriter() throws UnsupportedEncodingException {
            return writer;
        }

        /**
         * 这是将数据输出的最后步骤
         *
         * @throws IOException
         */
        @Override
        public void flushBuffer() throws IOException {

            if (out != null) {
                out.flush();
            }
            if (writer != null) {
                writer.flush();
            }
        }

        @Override
        public void reset() {
            buffer.reset();
        }

        @Override
        public void resetBuffer() {
            buffer.reset();
        }

        public byte[] getResponseData() throws IOException {
            flushBuffer();//将out、writer中的数据强制输出到WapperedResponse的buffer里面，否则取不到数据
            return buffer.toByteArray();
        }

        //内部类，对ServletOutputStream进行包装，指定输出流的输出端
        private class WrappedOutputStream extends ServletOutputStream {
            private ByteArrayOutputStream bos = null;

            public WrappedOutputStream(ByteArrayOutputStream stream) throws IOException {
                bos = stream;
            }

            //将指定字节写入输出流bos
            @Override
            public void write(int b) throws IOException {
                bos.write(b);
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {
            }

            @Override
            public void write(byte[] b) throws IOException {
                bos.write(b);
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                bos.write(b, off, len);
            }

        }
    }









}
