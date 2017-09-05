package com.lance.demo.springboot.service.util;

import lombok.extern.slf4j.Slf4j;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
@Slf4j
public class DBUtil {
    private static final String URL= "jdbc:mysql://192.168.1.72:3306/lance_test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "xb-12345";
    private static final String DRIVE_NAME = "com.mysql.jdbc.Driver";

    public static void extractTables(String targetDirectory,String table,String queryString) {
        try {
            String abDir = DBUtil.class.getResource(targetDirectory).getPath().replace("/target/test-classes","/src/test/resources") + "/" + table + ".xml";
            log.info("生成位置:{}",abDir);
            IDatabaseConnection connection = getConnection();
            QueryDataSet partialDataSet = new QueryDataSet(connection);
            partialDataSet.addTable(table, queryString);
            FlatXmlDataSet.write(partialDataSet, new FileOutputStream(abDir));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void extractTables(String targetDirectory,String[] tableNames)  {
        try {

            IDatabaseConnection connection = getConnection();
            for (int i = 0; i < tableNames.length; i++) {
                String tableName = tableNames[i];
                String abDir = DBUtil.class.getResource(targetDirectory).getPath().replace("/target/test-classes","/src/test/resources") + "/" + tableName + ".xml";
                log.info("生成位置:{}",abDir);
                IDataSet partialDataSet = connection.createDataSet(new String[] { tableName });
                FlatXmlDataSet.write(partialDataSet, new FileOutputStream(abDir));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static IDatabaseConnection getConnection() throws Exception {
        Class.forName(DRIVE_NAME);
        Connection jdbcConnection = DriverManager.getConnection(
                URL, USERNAME, PASSWORD);
        return new DatabaseConnection(jdbcConnection);
    }
}
