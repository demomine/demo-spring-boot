package com.lance.demo.springboot.service.util;


import org.junit.Test;

public class DataGenerator {
    @Test
    public void generateData() {
        String[] tables = {"t_user","t_class"};
        String dir = "/com/lance/demo/springboot/service";
        DBUtil.extractTables(dir,tables);
    }
}
