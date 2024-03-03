package com.mayiktWeb.tcp.Test2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

//使用tcp协议 实现登录
public class TcpClient {
    public static void main(String[] args) throws IOException {
        //客户端可以一直发送数据给服务器端


        while(true){
            Scanner scanner = new Scanner(System.in);

            System.out.println("请输入用户名:");
            String username = scanner.nextLine();

            System.out.println("请输入密码:");
            String password = scanner.nextLine();

            //创建serverSocket对象
            Socket socket = new Socket("127.0.0.1", 8080);

            //获取OutputStream
            OutputStream outputStream = socket.getOutputStream();

            //拼接数据
            String reqText = "username=" + username +"&password=" + password;

            //写入数据给服务器端
            outputStream.write(reqText.getBytes());

            //接受服务器响应的内容
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len = inputStream.read(bytes);

            //获取服务器端返回的数据
            String resp = new String(bytes,0,len);

            if ("success".equals(resp)){
                System.out.println("登录成功!!!");
            }else {
                System.out.println("登录失败...");
            }

            //关闭资源
            outputStream.close();
            socket.close();
        }

    }
}
