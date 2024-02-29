package com.mayiktWeb.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {

    static int count;
    public static void main(String[] args) throws IOException {

        //服务器端一直接收客户端发送的数据
        //创建cSocket对象进行接收数据包
        DatagramSocket ds = new DatagramSocket(8080);
        while(true){
            if (count == 10000){//存在线程安全问题
                break;
            }

            //创建接收者数据包
            byte[] bytes = new byte[1024];

            DatagramPacket dp = new DatagramPacket(bytes, bytes.length);

            ds.receive(dp);

            System.out.println("服务器端接收到客户端发送的数据:" + new String(dp.getData()));

            count++;
        }

        ds.close();
    }

}
