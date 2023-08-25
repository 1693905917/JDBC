package com.hzp.connection;

import org.junit.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionTest {
    @Test
    public void ConnectionTest() throws SQLException {
        Driver driver=new com.mysql.jdbc.Driver();
        String URL="jdbc:mysql://localhost:3306/jdbctest?useUnicode=true&characterEncoding=utf8";
        //jdbc:mysql:协议
        //localhost:ip地址
        //3306,默认mysql的端口号
        //test:test数据库
        Properties Info=new Properties();
        //将用户名和密码封装在Properties中
        Info.setProperty("user","root");
        Info.setProperty("password","1234");
        Connection conn = driver.connect(URL,Info);
        System.out.println(conn);
    }

    @Test
    public void ConnectionTestOne() throws Exception {
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver)clazz.newInstance();
        String URL="jdbc:mysql://localhost:3306/jdbctest?useUnicode=true&characterEncoding=utf8";
        Properties Info=new Properties();
        //将用户名和密码封装在Properties中
        Info.setProperty("user","root");
        Info.setProperty("password","1234");
        Connection conn = driver.connect(URL,Info);
        System.out.println(conn);
    }

    /**
     * 方式三：使用DriverManager替换Driver
     * @throws Exception
     */
    @Test
    public void ConnectionTestTwo() throws Exception {
        String URL="jdbc:mysql://localhost:3306/jdbctest?useUnicode=true&characterEncoding=utf8";
        String user="root";
        String password="1234";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, user, password);
        System.out.println(connection);
    }
    @Test
    public void ConnectionTestThree() throws Exception {
//        Properties properties = new Properties();
//        properties.load(ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties"));
////        properties.load(ConnectionTest.class.getResourceAsStream("/jdbc.properties"));
//        String user = properties.getProperty("user");
//        String password = properties.getProperty("password");
//        String url = properties.getProperty("url");
//        String driver = properties.getProperty("driver");
//        Class.forName(driver);
//        Connection connection = DriverManager.getConnection(url, user, password);
//        System.out.println(connection);
    }
}
