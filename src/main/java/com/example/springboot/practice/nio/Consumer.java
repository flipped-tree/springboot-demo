package com.example.springboot.practice.nio;

import java.net.Socket;

/**
 * 客户端
 *
 * @author xingce
 * @date 2019/11/12 19:30
 */
public class Consumer {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8002);
            socket.getOutputStream().write("test".getBytes());
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
