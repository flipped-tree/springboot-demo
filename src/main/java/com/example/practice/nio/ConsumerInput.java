package com.example.practice.nio;

import java.net.Socket;
import java.util.Scanner;

/**
 * 控制台输入传输信息客户端
 *
 * @author xingce
 * @date 2019/11/12 20:27
 */
public class ConsumerInput {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8080);
            String message = null;
            Scanner sc = new Scanner(System.in);
            message = sc.next();
            socket.getOutputStream().write(message.getBytes());
            socket.close();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
