package com.hzp.preparedstatement.crud;

import com.hzp.bean.Customers;
import com.hzp.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @BelongsProject: JdbcTest
 * @BelongsPackage: com.hzp.preparedstatement.crud
 * @Author: ASUS
 * @CreateTime: 2022-12-28  19:55jdbctest
 * @Description: TODO
 * @Version: 1.0
 */
public class CustomersForQuery {
    @Test
    public void TestQueryForCustomers()  {
        String sql="select id,name,email,birth from customers where id=?";
        Customers customers = QueryForCustomers(sql, "1");
        System.out.println(customers);
    }


    public Customers QueryForCustomers(String sql,Object ... args)  {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            if(rs.next()){
                //获取结果集的元数据，什么叫元数据？就是指除了数据以外的其他参数数据
                ResultSetMetaData metaData = rs.getMetaData();
                //从元数据中获取列数
                int columnCount = metaData.getColumnCount();
                Customers customers = new Customers();
                for (int i = 0; i < columnCount; i++) {
                    //获取相应列的数据
                    Object columValue = rs.getObject(i + 1);
                    //获取相应列的字段名
                    String columnName = metaData.getColumnName(i + 1);
                    //给Customers对象指定的columnName属性，赋值为columValue:通过反射
                    Field field = Customers.class.getDeclaredField(columnName);//getDeclaredFields()获得某个类的所有申明的字段
                    //一般情况下，我们并不能对类的私有字段进行操作，利用反射也不例外,但是我们使用setAccessible就可以对私有字段进行操作
                    field.setAccessible(true);
                    field.set(customers,columValue);
                }
                return customers;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn,ps,rs);
        }
        return null;
    }

    @Test
    public void testQuery1() throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        String sql="select id,name,email,birth from customers where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1,1);
        //执行，并返回结果集
        ResultSet rs = ps.executeQuery();

        if(rs.next()){//判断结果集的下一条是否有数据，如果有数据返回true,并指针下移；如果返回false,指针不会下移。
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String email = rs.getString(3);
            Date birth = rs.getDate(4);
            //将数据封装为一个对象
            Customers customers = new Customers(id,name,email,birth);
            System.out.println(customers);
        }

        JDBCUtils.closeConnection(conn,ps,rs);
    }
}
