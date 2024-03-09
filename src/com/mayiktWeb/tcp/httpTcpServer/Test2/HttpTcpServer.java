package com.mayiktWeb.tcp.httpTcpServer.Test2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 手写HttpServer服务器,可以直接在浏览器中访问LocalHost:80(或者127.0.0.1:80)
 * 对前面服务器进行的优化
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
                    InputStream inputStream = null;
                    OutputStream outputStream = null;
                    try {
                        /**
                         * 客户端(浏览器)访问的路径是这种格式:127.0.0.1/xxx.html
                         */
                        byte[] reqByte = new byte[1024];
                        inputStream = socket.getInputStream();
                        int reqLen = inputStream.read(reqByte);
                        String reqUrl = new String(reqByte, 0, reqLen);


                        /**
                         * 从浏览器传递过来,获取到的reqUrl是这种形式的:
                         * GET /demo2.html HTTP/1.1\r\nHost: 127.0.0.1\r\nConnection: keep-alive\r\nCache-Control: max-age=0\r\nsec-ch-ua: "Chromium";v='122",
                         *  "Not(A:Brand";v="24", "Google Chrome";v="122"\r\nsec-ch-ua-mobile: ?0\r\nsec-ch-ua-platform: "windows"\r\n......
                         * 所以后面用\r\n作为分隔符
                         */
                        String url = reqUrl.split("\r\n")[0].split(" ")[1];//获取到demo2.html

                        // 浏览器 -> 服务器端(tcpServer) 直接静态资源给客户端
                        outputStream = socket.getOutputStream();
                        //拼接要访问的本地路径
                        File file = new File("D:\\IdeaProjects\\JavaWeb\\src\\com\\mayiktWeb\\tcp\\httpTcpServer" + url);
                        FileInputStream fileInputStream = new FileInputStream(file);
                        byte[] bytes = new byte[1024];
                        //从硬盘中读取数据到内存中
                        int len = fileInputStream.read(bytes);
                        //返回数据给浏览器
                        outputStream.write(bytes, 0, len);
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            outputStream.write("500".getBytes());
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    } finally {
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
