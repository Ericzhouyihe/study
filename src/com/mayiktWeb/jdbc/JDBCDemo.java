package com.mayiktWeb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
JDBC快速入门
*/
public class JDBCDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        //2.获取连接
        String url="jdbc:mysql://127.0.0.1:3306/study";
        String username="root";
        String password="666666";
        Connection conn = DriverManager.getConnection(url, username, password);

        //3.定义sql
        String sql = "update account set money = 1000 where id=1";

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
