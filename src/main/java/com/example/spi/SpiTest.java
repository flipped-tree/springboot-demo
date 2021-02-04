package com.example.spi;

import java.util.ServiceLoader;

/**
 * @author xingce
 * @date 2021/02/05 0:45
 */
public class SpiTest {
    public static void main(String[] args) {
        ServiceLoader<SpiService> services = ServiceLoader.load(SpiService.class);
        System.out.println("spi test");
        services.forEach(SpiService::sayHello);
    }
}
