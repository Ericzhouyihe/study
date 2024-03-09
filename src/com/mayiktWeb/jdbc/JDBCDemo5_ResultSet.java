package com.mayiktWeb.jdbc;

import com.itheima.pojo.Account;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * JDBC API 详解：Statement
 * */
public class JDBCDemo5_ResultSet {
    /*
     * @throws Exception
     * */
    @Test
    public void testResultSet() throws SQLException {
        //1.注册驱动
        //Class.forName("com.mysql.jdbc.Driver");

        //2.获取连接：如果连接的是本机MySQL并且端口是默认的3360 可以简化书写
        String url = "jdbc:mysql:///study";
        String username = "root";
        String password = "666666";
        Connection conn = DriverManager.getConnection(url, username, password);

        //3.定义sql
        String sql = "select * from account";

        //4.获取sql的对象Statement
        Statement stmt = conn.createStatement();

        //5.执行sql
        ResultSet rs = stmt.executeQuery(sql);

        //6.处理结果，遍历rs中的所有数据
         //6.1  光标向下移动一行，并且判断当前是否有数据
        while (rs.next()){
            //6.2 获取数据  getXxx()
            int id = rs.getInt("id");//columnLabel
            String name = rs.getString("name");
            double money = rs.getDouble("money");
            /*int id = rs.getInt(1);//columnIndex
            String name = rs.getString(2);
            double money = rs.getDouble(3);*/

            System.out.println(id);
            System.out.println(name);
            System.out.println(money);

            System.out.println("-----------------");
        }

        //7.释放资源
        rs.close();
        stmt.close();
        conn.close();
    }

    /*
     *查询account账户表数据，封装为Account对象中，并且存储到ArrayList集合中
     * 1.定义实体类Account
     * 2.查询数据，封装到Account对象中
     * 3.将Account对象存入ArrayList集合当中
     *
     *  @throws Exception
     * */
    @Test
    public void testResultSet2() throws SQLException {
        //1.注册驱动
        //Class.forName("com.mysql.jdbc.Driver");

        //2.获取连接：如果连接的是本机MySQL并且端口是默认的3360 可以简化书写
        String url = "jdbc:mysql:///study";
        String username = "root";
        String password = "666666";
        Connection conn = DriverManager.getConnection(url, username, password);

        //3.定义sql
        String sql = "select * from account";

        //4.获取sql的对象Statement
        Statement stmt = conn.createStatement();

        //5.执行sql
        ResultSet rs = stmt.executeQuery(sql);

        //创建Account的集合
        List<Account> list=new ArrayList<>();

        //6.处理结果，遍历rs中的所有数据
        //6.1  光标向下移动一行，并且判断当前是否有数据
        while (rs.next()){
            //6.2 获取数据  getXxx()
            int id = rs.getInt("id");//columnLabel
            String name = rs.getString("name");
            double money = rs.getDouble("money");
            /*int id = rs.getInt(1);//columnIndex
            String name = rs.getString(2);
            double money = rs.getDouble(3);*/

            //封装进Account
            Account account=new Account(id,name,money);

            //存入集合
            list.add(account);

        }

        System.out.println(list);

        //7.释放资源
        rs.close();
        stmt.close();
        conn.close();
    }
}
