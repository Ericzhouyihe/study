package com.mayiktWeb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * JDBC API 详解：Connection
 * */
public class JDBCDemo3_Connection {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.注册驱动
        //Class.forName("com.mysql.jdbc.Driver");

        //2.获取连接
        String url="jdbc:mysql:///study?userSSL=false";
        String username="root";
        String password="666666";
        Connection conn = DriverManager.getConnection(url, username, password);

        //3.定义sql
        String sql1 = "update account set money = 1000 where id=1";
        String sql2 = "update account set money = 1000 where id=2";
        Statement stmt = null;

        try {
            //开启事务
            conn.setAutoCommit(false);

            //4.获取sql的对象Statement
            stmt = conn.createStatement();

            //5.执行sql
            int count1= stmt.executeUpdate(sql1);//受影响的行数

            //6.处理结果
            System.out.println("受影响的行数:"+count1);

            //5.执行sql
            int count2= stmt.executeUpdate(sql2);//受影响的行数

            //6.处理结果
            System.out.println("受影响的行数:"+count2);

            //提交事务
            conn.commit();
        } catch (Exception e) {
            //回滚事务
            conn.rollback();

            throw new RuntimeException(e);
        }

        //7.释放资源
        stmt.close();
        conn.close();
    }
}
