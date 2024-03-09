package com.mayiktWeb.jdbc;

import org.junit.Test;

import java.sql.*;

/*
 * 用户登录
 * SQL注入问题
 * */
public class JDBCDemo6_UserLogin {
    /*
     * 用户登录
     * @throws Exception
     * */
    @Test
    public void testResultSet() throws SQLException {
        //1.注册驱动
        //Class.forName("com.mysql.jdbc.Driver");

        //2.获取连接：如果连接的是本机MySQL并且端口是默认的3360 可以简化书写
        String url = "jdbc:mysql:///db2";
        String username = "root";
        String password = "666666";
        Connection conn = DriverManager.getConnection(url, username, password);

        //3.接收用户输入的用户名和密码
        String name="zhangsan";
        String pwd="123";

        String sql="select * from tb_user where username = '"+name+"' and password = '"+pwd+"'";

        //4.获取stmt对象
        Statement stmt = conn.createStatement();

        //5.执行SQL
        ResultSet rs = stmt.executeQuery(sql);

        //6.判断登录是否成功
        if (rs.next()) {
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }

        //7.释放资源
        rs.close();
        stmt.close();
        conn.close();
    }

    /*
     * 演示SQL注入
     * @throws Exception
     * */
    @Test
    public void testLogin_Inject() throws SQLException {
        //1.注册驱动
        //Class.forName("com.mysql.jdbc.Driver");

        //2.获取连接：如果连接的是本机MySQL并且端口是默认的3360 可以简化书写
        String url = "jdbc:mysql:///db2";
        String username = "root";
        String password = "666666";
        Connection conn = DriverManager.getConnection(url, username, password);

        //3.接收用户输入的用户名和密码
        String name="ajdkfjakldf";//用户名随便乱输入一个
        String pwd=" ' or '1' = '1";

        String sql="select * from tb_user where username = '"+name+"' and password = '"+pwd+"'";
        //System.out.println(pwd);
        //System.out.println(sql);
        //select * from tb_user where username = 'ajdkfjakldf' and password = ' ' or '1' = '1'

        //4.获取stmt对象
        Statement stmt = conn.createStatement();

        //5.执行SQL
        ResultSet rs = stmt.executeQuery(sql);

        //6.判断登录是否成功
        if (rs.next()) {
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }

        //7.释放资源
        rs.close();
        stmt.close();
        conn.close();
    }
}
