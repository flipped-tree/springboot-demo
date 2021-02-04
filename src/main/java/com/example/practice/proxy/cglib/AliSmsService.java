package com.example.practice.proxy.cglib;

/**
 * @author xingce
 * @date 2020/12/16 17:41
 */
public class AliSmsService {

    public String send(String message) {
        System.out.println("send message");
        return message;
    }

}
