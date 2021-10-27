package com.example.rpc;

/**
 * @author xingce
 * @date 2021/3/1 20:38
 */
public class RpcServiceImpl implements RpcService{
    @Override
    public String sayHello(String message) {
        return "hello " + message;
    }
}
