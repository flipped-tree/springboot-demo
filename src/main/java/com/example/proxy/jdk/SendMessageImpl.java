package com.example.proxy.jdk;

/**
 * @author xingce
 * @date 2020/12/16 21:08
 */
public class SendMessageImpl implements SendMessage {
    @Override
    public void sendMessage(String message) {
        System.out.println("发送短信:" + message);
    }
}
