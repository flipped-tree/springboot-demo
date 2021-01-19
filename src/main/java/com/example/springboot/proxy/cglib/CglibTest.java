package com.example.springboot.proxy.cglib;

/**
 * @author xingce
 * @date 2020/12/16 17:46
 */
public class CglibTest {

    public static void main(String[] args) {
        AliSmsService aliSmsService = (AliSmsService) CglibFactory.getObject(AliSmsService.class);
        aliSmsService.send("测试cglib");
    }

}
