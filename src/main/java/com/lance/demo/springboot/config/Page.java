package com.lance.demo.springboot.config;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Page<T> extends Pagination {
    private static final long serialVersionUID = 1L;
    private List<T> records = Collections.emptyList();
    private Map<String, Object> condition;

    public Page() {
    }

    public Page(int current, int size) {
        super(current, size);
    }

    public Page(int current, int size, String orderByField) {
        super(current, size);
        this.setOrderByField(orderByField);
    }

    public List<T> getRecords() {
        return this.records;
    }

    public Page<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    public Map<String, Object> getCondition() {
        return this.condition;
    }

    public Page<T> setCondition(Map<String, Object> condition) {
        this.condition = condition;
        return this;
    }

    public String toString() {
        StringBuilder pg = new StringBuilder();
        pg.append(" Page:{ [").append(super.toString()).append("], ");
        if (this.records != null) {
            pg.append("records-size:").append(this.records.size());
        } else {
            pg.append("records is null");
        }

        return pg.append(" }").toString();
    }
}
