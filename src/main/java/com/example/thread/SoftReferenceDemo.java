package com.example.thread;

import java.lang.ref.SoftReference;

/**
 * 软引用，JVM gc时内存不足回收对象
 *
 * @author xingce
 * @date 2021/12/28 19:18
 */
public class SoftReferenceDemo {

    public static void main(String[] args) {
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(softReference.get());
        System.gc();
        System.out.println(softReference.get());

        byte[] bytes = new byte[1024 * 1024 * 10];
        System.out.println(softReference.get());
    }

}
