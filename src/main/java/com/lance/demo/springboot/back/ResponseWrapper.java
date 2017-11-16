package com.lance.demo.springboot.back;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

public class ResponseWrapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream buffer = null;
    private ServletOutputStream out    = null;
    private PrintWriter writer = null;

    public ResponseWrapper(HttpServletResponse resp) throws IOException {
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
