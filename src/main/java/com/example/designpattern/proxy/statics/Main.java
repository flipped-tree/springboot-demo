package com.example.designpattern.proxy.statics;

/**
 * @author xingce
 * @date 2021/3/1 23:01
 */
public class Main {
    public static void main(String[] args) {
        SmsService smsService = new SmsServiceImpl();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.send("java");
    }
}
