package com.example.springboot.strategy;

import org.springframework.stereotype.Service;

/**
 * @author xingce
 * @date 2021/3/4 12:04
 */
@Service
public class FirstStrategy implements Strategy{
    @Override
    public void doSomething() {
        System.out.println("first strategy 方法");
    }
}
