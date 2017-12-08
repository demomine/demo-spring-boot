package com.lance.demo.springboot.config;

public class Pagination extends com.baomidou.mybatisplus.plugins.pagination.Pagination {
    public Pagination() {
        super();
    }

    public Pagination(int current, int size) {
        super(current,size);
    }

    public Pagination(int current, int size, boolean searchCount) {
        this(current, size, searchCount, true);
    }

    public Pagination(int current, int size, boolean searchCount, boolean openSort) {
        super(current, size, searchCount, openSort);
    }
}
