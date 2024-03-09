package com.mayiktWeb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * JDBC API 详解：DriverManager
 * */
public class JDBCDemo2_DriverManager {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        //2.获取连接：如果连接的是本机MySQL并且端口是默认的3360 可以简化书写
        String url="jdbc:mysql:///study";
        String username="root";
        String password="666666";
        Connection conn = DriverManager.getConnection(url, username, password);

        //3.定义sql
        String sql = "update account set money = 1500 where id=1";

        //4.获取sql的对象Statement
        Statement stmt = conn.createStatement();

        //5.执行sql
        int count= stmt.executeUpdate(sql);//受影响的行数

        //6.处理结果
        System.out.println("受影响的行数:"+count);

        //7.释放资源
        stmt.close();
        conn.close();
    }
}
