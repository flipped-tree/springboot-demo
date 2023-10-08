package com.example.spi;

/**
 * @author xingce
 * @date 2021/9/30 11:03
 */
public class AppServiceImpl implements SpiService {
    @Override
    public void sayHello() {
        System.out.println("app spi");
    }
}
