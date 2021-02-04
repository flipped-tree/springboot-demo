package com.example.practice.nio;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO服务端
 *
 * @author xingce
 * @date 2019/11/12 19:25
 */
public class SingleServer {
    public static void main(String[] args) {
        byte[] buffer = new byte[1024];
        try {
            ServerSocket serverSocket = new ServerSocket(8001);
            System.out.println("服务器已启动并监听8001端口");
            System.out.println();
            System.out.println("服务器正在等待连接...");
            // 阻塞1：等待连接时阻塞
            Socket socket = serverSocket.accept();
            System.out.println("服务器已接收到连接请求...");
            System.out.println();
            System.out.println("服务器正在等待数据...");
            // 阻塞2：等待数据时阻塞
            socket.getInputStream().read(buffer);
            System.out.println("服务器已经接收到数据");
            System.out.println();
            String content = new String(buffer);
            System.out.println("接收到的数据:" + content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
