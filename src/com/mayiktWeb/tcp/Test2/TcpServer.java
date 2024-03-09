package com.mayiktWeb.tcp.Test2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

//使用tcp协议 实现登录
//并添加了多线程的写法
public class TcpServer {
    public static void main(String[] args) throws IOException {

        //创建serverSocket在端口8080进行监听
        ServerSocket serverSocket = new ServerSocket(8080);

        System.out.println("服务器启动成功...");

        //服务器端可以一直接受到客户端发送的数据。
        while(true){
            //接收客户端数据
            Socket socket = serverSocket.accept();

            //不允许直接单独new线程  后续用线程池来维护线程 --java进阶
            //TODO (一般不允许直接new线程,要采取线程池进行优化,后续java进阶再来修改)
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //接收客户端数据
                        InputStream inputStream = socket.getInputStream();
                        byte[] bytes = new byte[1024];
                        int len = inputStream.read(bytes);

                        //服务器端接收客户端数据包:username=zyh&password=111111
                        String reqText = new String(bytes, 0, len);
                        String username = reqText.split("&")[0].split("=")[1];
                        String password = reqText.split("&")[1].split("=")[1];

                        //服务器响应给客户端的数据  验证账号密码正确响应success 错误就响应error
                        OutputStream outputStream = socket.getOutputStream();

                        if ("zyh".equals(username) && "111111".equals(password)){
                            outputStream.write("success".getBytes());
                        }else {
                            outputStream.write("error".getBytes());
                        }

                        //关闭资源
                        inputStream.close();
                        outputStream.close();
                        socket.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();




        }
    }
}