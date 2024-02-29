package com.mayiktWeb.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {
    public static void main(String[] args) throws IOException {
        // 客户端发送数据给服务器端
        //创建Socket发送者
        DatagramSocket ds = new DatagramSocket();
        while (true){
            System.out.println("客户端:请输入发送数据");
            Scanner sc = new Scanner(System.in);
            String context = sc.nextLine();
            if ("666".equals(context)){
                System.out.println("发送者退出...");
                break;
            }
            byte[] bytes = context.getBytes();

            //封装发送的数据包
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length,
                    InetAddress.getByName("mayikt.server.com"), 8080);
            //开始发送数据包
            ds.send(dp);
            System.out.println("数据发送成功...");
        }

        //关闭资源
        ds.close();
    }

}
