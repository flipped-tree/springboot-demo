package com.example.threadLocal;

import java.lang.ref.WeakReference;

/**
 * 弱引用，JVM gc时直接回收对象
 *
 * @author xingce
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object object = new Object();
        // 弱引用
        WeakReference<Object> weakReference = new WeakReference<>(object);
        // object和弱引用指向同一个对象
        System.out.println(object);
        System.out.println(weakReference.get());
        // 手动回收object，object只剩下弱引用
        object = null;
        System.out.println(weakReference.get());
        // 手动唤醒gc
        System.gc();
        System.out.println(object);
        System.out.println(weakReference.get());
    }
}
