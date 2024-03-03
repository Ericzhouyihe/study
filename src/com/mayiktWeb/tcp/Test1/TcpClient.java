package com.mayiktWeb.tcp.Test1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

//使用tcp协议 客户端可以一直发送数据给服务器端，服务器端可以一直接受到客户端发送的数据。
public class TcpClient {
    public static void main(String[] args) throws IOException {
        //客户端可以一直发送数据给服务器端

        while(true){
            //创建serverSocket对象
            Socket socket = new Socket("127.0.0.1", 8080);
            System.out.println("客户端:请输入要发送的内容");
            Scanner scanner = new Scanner(System.in);
            String context = scanner.nextLine();

            //输入'666'时退出循环
            if ("666".equals(context)){
                break;
            }

            //获取getOutputStream
            OutputStream outputStream = socket.getOutputStream();
            //写入数据给服务器端
            outputStream.write(context.getBytes());
            //接受服务器响应的内容
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len = inputStream.read(bytes);
            System.out.println("服务器响应数据给客户端:"+new String(bytes,0,len));

            outputStream.close();
            socket.close();
        }

    }
}
