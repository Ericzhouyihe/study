package com.mayiktWeb.dns;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DNSTest {
    public static void main(String[] args) throws UnknownHostException {
        //获取www.baidu.com.com的IP地址
        getHostData("www.baidu.com");
        getHostData("www.jingdong.com");
        getHostData("mayikt.server.com");
    }

    private static void getHostData(String domainName) throws UnknownHostException {
        //根据域名获取主机地址
        InetAddress inetAddress = InetAddress.getByName(domainName);
        //获取IP地址的主机名
        String hostName = inetAddress.getHostName();
        System.out.println("hostName : " + hostName);

        //获取字符串格式的原始IP地址
        String hostAddress = inetAddress.getHostAddress();
        System.out.println("hostAddress : " + hostAddress);
    }
}
