package com.mayiktWeb.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;

public class TcpClient {
    public static void main(String[] args) throws IOException {
        //  1.创建发送端Socket对象（创建连接） --三次握手  确保服务器存在 再开始传输数据
        Socket socket = new Socket(InetAddress.getByName("mayikt.server.com"), 8090);

        //  2.获取输出流对象；
        OutputStream outputStream = socket.getOutputStream();

        //  3.发送数据；
        outputStream.write("mayikt 66".getBytes());

        //  4.释放资源；
        outputStream.close();
        socket.close();
    }

}
