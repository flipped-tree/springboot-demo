package com.example.springboot.strategy;

import org.springframework.stereotype.Service;

/**
 * @author xingce
 * @date 2021/3/4 12:05
 */
@Service
public class DefaulStrategy implements Strategy{
    @Override
    public void doSomething() {
        System.out.println("默认方法");
    }
}
