package com.example.test;

import java.util.concurrent.TimeUnit;

public class ExpiringKeyValueStoreDemo {
    
    public static void main(String[] args) throws InterruptedException {
        // 创建存储系统（清理间隔10秒）
        ExpiringKeyValueStore<String, String> store = 
            new ExpiringKeyValueStore<>(10, TimeUnit.SECONDS);
        
        System.out.println("=== 测试基本功能 ===");
        
        // 1. 设置永不过期的键值对
        store.put("permanent", "永久数据");
        
        // 2. 设置5秒后过期的键值对
        store.put("temp1", "临时数据1", 5, TimeUnit.SECONDS);
        
        // 3. 设置10秒后过期的键值对
        store.put("temp2", "临时数据2", 10, TimeUnit.SECONDS);
        
        // 4. 立即获取
        System.out.println("temp1: " + store.get("temp1")); // 临时数据1
        System.out.println("temp2: " + store.get("temp2")); // 临时数据2
        System.out.println("permanent: " + store.get("permanent")); // 永久数据
        System.out.println("当前大小: " + store.size()); // 3
        System.out.println("有效大小: " + store.validSize()); // 3
        
        System.out.println("\n=== 等待6秒（temp1过期）===");
        Thread.sleep(6000);
        
        System.out.println("temp1: " + store.get("temp1")); // null（已过期）
        System.out.println("temp2: " + store.get("temp2")); // 临时数据2
        System.out.println("permanent: " + store.get("permanent")); // 永久数据
        System.out.println("当前大小: " + store.size()); // 2或3（取决于是否触发清理）
        System.out.println("有效大小: " + store.validSize()); // 2
        
        System.out.println("\n=== 等待5秒（temp2过期）===");
        Thread.sleep(5000);
        
        System.out.println("temp2: " + store.get("temp2")); // null（已过期）
        System.out.println("permanent: " + store.get("permanent")); // 永久数据
        System.out.println("有效大小: " + store.validSize()); // 1
        
        System.out.println("\n=== 测试删除 ===");
        store.remove("permanent");
        System.out.println("permanent: " + store.get("permanent")); // null
        System.out.println("有效大小: " + store.validSize()); // 0
        
        // 5. 关闭存储系统（释放资源）
        store.shutdown();
        System.out.println("\n存储系统已关闭");
    }
}