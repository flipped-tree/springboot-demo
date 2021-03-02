package com.example.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xingce
 * @date 2021/3/1 20:38
 */
public class RpcFramework {
    public static void export(Object service, int port) throws IOException {
        ServerSocket server = new ServerSocket(port);
        while (true) {
            Socket socket = server.accept();
            new Thread(() -> {
                try {
                    // 反序列化
                    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                    // 读取文件名
                    String methodName = (String) input.readObject();
                    // 参数类型
                    Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                    // 参数
                    Object[] arguments = (Object[]) input.readObject();
                    // 找到方法
                    Method method = service.getClass().getMethod(methodName, parameterTypes);
                    // 调用方法
                    Object result = method.invoke(service, arguments);
                    // 返回结果
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
