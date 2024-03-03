package com.mayiktWeb.tcp.Test1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

//使用tcp协议 客户端可以一直发送数据给服务器端，服务器端可以一直接受到客户端发送的数据。
public class TcpServer {
    public static void main(String[] args) throws IOException {

        //创建serverSocket对象
        ServerSocket serverSocket = new ServerSocket(8080);

        System.out.println("服务器启动成功...");

        //服务器端可以一直接受到客户端发送的数据。
        while(true){
            //接收客户端数据
            Socket socket = serverSocket.accept();

            //inputStream
            InputStream inputStream = socket.getInputStream();

            byte[] bytes = new byte[1024];
            int len = inputStream.read(bytes);
            System.out.println("服务器端接客户端数据:" + new String(bytes,0,len));

            //服务器响应给客户端的数据
            OutputStream outputStream = socket.getOutputStream();


            String resp =  "我收到了" + UUID.randomUUID().toString();

            outputStream.write(resp.getBytes());

            inputStream.close();
            outputStream.close();
            socket.close();
        }
    }
}