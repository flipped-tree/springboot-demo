package com.example.designpattern.proxy.statics;

/**
 * @author xingce
 * @date 2021/3/1 22:58
 */
public class SmsServiceImpl implements SmsService {
    @Override
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}
