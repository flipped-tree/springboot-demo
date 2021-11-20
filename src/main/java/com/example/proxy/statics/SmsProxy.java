package com.example.proxy.statics;

/**
 * @author xingce
 * @date 2021/3/1 22:59
 */
public class SmsProxy implements SmsService {

    private final SmsService smsService;

    SmsProxy(SmsService smsService) {
        this.smsService = smsService;
    }

    @Override
    public String send(String message) {
        System.out.println("before method send()");
        smsService.send(message);
        System.out.println("after method send()");
        return null;
    }
}
