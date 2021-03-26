package com.example.designpattern.proxy.jdk;

/**
 * @author xingce
 * @date 2020/12/16 21:12
 */
public class JdkProxyTest {

    public static void main(String[] args) {
        SendMessage sendMessage = (SendMessage) JdkProxyFactory.getProxy(new SendMessageImpl());
        sendMessage.sendMessage("jdk动态代理");
    }

}
