package com.hzp.preparedstatement.crud;

import com.hzp.util.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * @Author:ASUS
 * @Date: 2022/12/17  13:02
 * @Version 1.0
 */
public class PreparedStatementUpdateTest {


    @Test
    /*
     * @description:插入数据操作
     * @author:  HZP
     * @date: 2022/12/26 12:21
     * @param: []
     * @return: void
     **/
    public void testInsert()  {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            Properties properties = new Properties();
//            properties.load(PreparedStatementUpdateTest.class.getClassLoader().getResourceAsStream("jdbc.properties"));
//
//            String user = properties.getProperty("user");
//            String password = properties.getProperty("password");
//            String url = properties.getProperty("url");
//            String driver = properties.getProperty("driver");
//
//            Class.forName(driver);
//            connection = DriverManager.getConnection(url, user, password);
//            //?是占位符
//            String sql="insert into customers(name,email,birth) values (?,?,?)";
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1,"黄志鹏");
//            preparedStatement.setString(2,"hzp@qq.com");
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            preparedStatement.setDate(3,new Date( simpleDateFormat.parse("2001-08-30").getTime()));
//            //执行SQL操作
//            preparedStatement.execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                //资源的关闭
//                if (preparedStatement!=null)
//                preparedStatement.close();
//                if (connection!=null)
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }

    /*
     * @description:修改数据操作
     * @author:  HZP
     * @date: 2022/12/26 12:22
     * @param:
     * @return:
     **/
    @Test
    public void testUpdate(){
        try {
            //1.连接数据库
            Connection connection = JDBCUtils.getConnection();
            //System.out.println(connection);
            //2,预编译sql语句，返回Preparedstatement的实例
            String sql="update customers set name=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //第一种方法：
            preparedStatement.setString(1,"克莱贝尔");
            preparedStatement.setInt(2,18);
            //第二种方法：
            // preparedStatement.setObject(); 这是一个通用的方法
            preparedStatement.execute();
            JDBCUtils.closeConnection(connection,preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testCommonUpdate(){
        String sql="update `order` set order_name=? where order_id =?";
        Update(sql,"CC","2");

    }

    //通用的增删改操作
    public void Update(String sql,Object ... args)  {//可变量args看成是个数组即可
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //1,获取数据库的连接
            connection = JDBCUtils.getConnection();
            //2.预编译sql语句，返回Preparedstatement的实例
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                //3.填充占位符
                preparedStatement.setObject(i+1,args[i]);
            }
            //4.执行
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.资源的关闭
            JDBCUtils.closeConnection(connection,preparedStatement);
        }
    }
}
