package com.mayiktWeb.jdbc;

import org.junit.Test;

import java.sql.*;

/*
 * 用户登录
 * SQL注入问题
 * */
public class JDBCDemo7_PreparedStatement {
    /*
     * 用户登录
     * @throws Exception
     * */
    @Test
    public void testPreparedStatement() throws SQLException {
        //1.注册驱动
        //Class.forName("com.mysql.jdbc.Driver");

        //2.获取连接：如果连接的是本机MySQL并且端口是默认的3360 可以简化书写
        String url = "jdbc:mysql:///db2";
        String username = "root";
        String password = "666666";
        Connection conn = DriverManager.getConnection(url, username, password);

        //3.接收用户输入的用户名和密码
        String name="zhangsan";
        String pwd=" ' or '1' = '1";

        //定义SQL
        String sql="select * from tb_user where username = ? and password = ?";

        //4.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //设置？的值
        pstmt.setString(1,name);
        pstmt.setString(2,pwd);
        //会将你输入密码中的引号给弄成转义的字符
        //select * from tb_user where username = 'ajdkfjakldf' and password = ' \' or \'1\' = \'1'

        //5.执行SQL
        ResultSet rs = pstmt.executeQuery();//不需要再传递SQL语句

        //6.判断登录是否成功
        if (rs.next()) {
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }

        //7.释放资源
        rs.close();
        pstmt.close();
        conn.close();
    }

}
