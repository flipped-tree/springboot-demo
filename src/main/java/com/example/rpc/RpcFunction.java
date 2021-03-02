package com.example.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @author xingce
 * @date 2021/3/1 21:33
 */
public class RpcFunction {
    public static <T> T refer(Class<T> interfaceClass, String host, int port) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass},
                (proxy, method, arguments) -> {
                    Socket socket = new Socket(host, port);
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    //传方法名
                    output.writeObject(method.getName());
                    //传参数类型
                    output.writeObject(method.getParameterTypes());
                    //传参数值
                    output.writeObject(arguments);
                    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                    // 读取结果
                    Object result = input.readObject();
                    return result;
                });
    }
}
