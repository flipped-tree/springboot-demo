package com.example.rpc;

import java.io.IOException;

/**
 * @author xingce
 * @date 2021/3/1 21:31
 */
public class ServerMain {
    public static void main(String[] args) throws IOException {
        RpcService service = new RpcServiceImpl();
        RpcFramework.export(service,1412);
    }
}
