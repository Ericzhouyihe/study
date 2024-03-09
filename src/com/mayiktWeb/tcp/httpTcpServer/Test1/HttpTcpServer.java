package com.mayiktWeb.tcp.httpTcpServer.Test1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 手写HttpServer服务器,可以直接在浏览器中访问LocalHost:80(或者127.0.0.1:80)
 */
public class HttpTcpServer {
    public static void main(String[] args) throws IOException {
        //创建监听80端口的ServerSocket  http协议底层基于tcp封装 默认端口为80 在浏览器中不需要写
        ServerSocket serverSocket = new ServerSocket(80);

        //服务器端可以一直接收 客户端传递过来的数据
        //一直监听需要一个死循环
        while (true){
            //监听到客户端(浏览器)发来的数据  如果客户端没发来请求会一直阻塞在此处
            Socket socket = serverSocket.accept();

            //提供多线程访问  这里直接new Thread
            //TODO (一般不允许直接new线程,要采取线程池进行优化,后续java进阶再来修改)
            new Thread(new Runnable() {
                @Override
                public void run() {
                    OutputStream outputStream = null;

                    try {
                        // 浏览器 -> 服务器端(tcpserver) 直接静态资源给客户端
                        outputStream = socket.getOutputStream();

                        //将本地磁盘中的静态资源读取到内存中
                        File file = new File("D:\\IdeaProjects\\JavaWeb\\src\\com\\mayiktWeb\\tcp\\httpTcpServer\\demo.html");
                        FileInputStream fileInputStream = new FileInputStream(file);
                        byte[] reqByte = new byte[1024];
                        int reqLen = fileInputStream.read(reqByte);

                        //从内存中奖该数据返回给客户端浏览器
                        outputStream.write(reqByte,0,reqLen);


                    } catch (Exception e) {
                      e.printStackTrace();
                    }finally {
                        //关闭资源
                        try {
                            if(outputStream!=null)
                                outputStream.close();
                            if (socket!=null)
                                socket.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }).start();
        }

    }
}
