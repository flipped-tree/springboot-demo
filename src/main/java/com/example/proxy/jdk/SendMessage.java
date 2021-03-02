package com.example.proxy.jdk;

/**
 * @author xingce
 * @date 2020/12/16 21:08
 */
public interface SendMessage {

    default void send() {
        System.out.println("发送短信");
    }

    void sendMessage(String message);

}
