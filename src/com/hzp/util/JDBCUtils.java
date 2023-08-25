package com.hzp.util;

import com.hzp.preparedstatement.crud.PreparedStatementUpdateTest;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @BelongsProject: JdbcTest
 * @BelongsPackage: com.hzp.util
 * @Author: ASUS
 * @CreateTime: 2022-12-26  12:05
 * @Description: TODO
 * @Version: 1.0
 */
public class JDBCUtils {
    /*
     * @description:打开数据库连接操作
     * @author:  HZP
     * @date: 2022/12/26 12:08
     * @param:void
     * @return: Connection
     **/
    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        Connection connection=null;
        try {
            //ClassLoader.getSystemClassLoader().getResourceAsStream  不需要从哪个文件开始
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    /*
     * @description:关闭数据库资源操作
     * @author:  HZP
     * @date: 2022/12/26 12:13
     * @param:
     * @return:
     **/
    public static void closeConnection(Connection connection, PreparedStatement preparedStatement){
        if(preparedStatement!=null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    /*
     * @description:关闭数据库资源操作
     * @author:  HZP
     * @date: 2022/12/28 19:57
     * @param:
     * @return:
     **/
    public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
